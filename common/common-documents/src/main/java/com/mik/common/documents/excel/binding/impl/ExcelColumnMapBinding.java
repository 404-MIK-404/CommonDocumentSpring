package com.mik.common.documents.excel.binding.impl;

import com.mik.common.documents.excel.annotation.ExcelColumnBindingType;
import com.mik.common.documents.excel.annotation.column.ExcelMarkerTableColumn;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import com.mik.common.documents.util.DocumentReflectionDataExtractorUtils;
import com.mik.common.documents.util.DocumentReflectionUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.*;


@Component
@AllArgsConstructor
public class ExcelColumnMapBinding implements ExcelAnnotationBinding {

    @Override
    public List<ExcelColumnDefinition> apply(ExcelAnnotationContext context) {
        Field field = context.getField();

        if (!isMapField(field)) {
            return Collections.emptyList();
        }

        String pathValue = buildPath(context.getParentPath(), field);

        return new ArrayList<>(buildDynamicColumns(context, pathValue).values());
    }

    @Override
    public ExcelColumnBindingType getBindingType() {
        return ExcelColumnBindingType.MAP;
    }

    private boolean isMapField(Field field) {
        return ClassUtils.isAssignable(Map.class, field.getType());
    }

    private String buildPath(String parentPath, Field field) {
        return (parentPath == null || parentPath.isEmpty())
               ? field.getName()
               : parentPath + "." + field.getName();
    }

    private Map<String, ExcelColumnDefinition> buildDynamicColumns(
            ExcelAnnotationContext context,
            String pathValue
    ) {
        Field field = context.getField();
        List<?> data = context.getData();
        ExcelMarkerTableColumn annotation =
                (ExcelMarkerTableColumn) context.getAnnotation();

        Class<?> valueType = resolveValueType(field);

        if (valueType == null) return Collections.emptyMap();

        Map<String, ExcelColumnDefinition> result = new HashMap<>();

        for (Object item : data) {
            processItem(item, pathValue, valueType, annotation, result);
        }

        return result;
    }

    private Class<?> resolveValueType(Field field) {
        ResolvableType type = ResolvableType.forField(field);

        Class<?> key = type.getGeneric(0).resolve();
        Class<?> value = type.getGeneric(1).resolve();

        if (key != String.class || !DocumentReflectionUtils.isSimpleClass(value)) {
            return null;
        }

        return value;
    }

    private void processItem(
            Object item,
            String pathValue,
            Class<?> valueType,
            ExcelMarkerTableColumn annotation,
            Map<String, ExcelColumnDefinition> result
    ) {
        Map<?, ?> map =
                DocumentReflectionDataExtractorUtils.invokeMap(item, pathValue);

        if (map == null) return;

        map.forEach((k, v) ->
                            addDynamicColumnIfAbsent(k, pathValue, valueType, annotation, result)
        );
    }

    private void addDynamicColumnIfAbsent(
            Object key,
            String pathValue,
            Class<?> valueType,
            ExcelMarkerTableColumn annotation,
            Map<String, ExcelColumnDefinition> result
    ) {
        String columnName = key.toString();

        result.computeIfAbsent(columnName, name ->
                                                   ExcelColumnDefinition.builder()
                                                                        .defaultValue("")
                                                                        .totalBindingType(annotation.total())
                                                                        .type(valueType)
                                                                        .name(name)
                                                                        .span(annotation.span())
                                                                        .decimal(annotation.decimal())
                                                                        .styleData(annotation.dataStyle())
                                                                        .styleData(annotation.columnStyle())
                                                                        .pathField(pathValue + "." + name)
                                                                        .children(new ArrayList<>())
                                                                        .totalBindingType(annotation.total())
                                                                        .build()
        );
    }

}
