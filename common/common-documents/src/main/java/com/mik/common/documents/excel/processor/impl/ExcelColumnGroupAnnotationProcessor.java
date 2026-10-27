package com.mik.common.documents.excel.processor.impl;

import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelColumnGroupAnnotationProcessor implements ExcelAnnotationProcessor<List<ExcelColumnDefinition>> {

    @Override
    public List<ExcelColumnDefinition> process(ExcelAnnotationContext context) {
        /*
        ExcelMarkerTableColumnGrouping annotation = (ExcelMarkerTableColumnGrouping) context.getAnnotation();
        List<ExcelColumnDefinition> columns = context.getColumns();
        Field field = context.getField();
        Class<?> declaringClass = field.getType();
        String parentPath = context.getParentPath();
        String path = (parentPath == null || parentPath.isEmpty())
                      ? field.getName()
                      : parentPath + "." + field.getName();
        ExcelColumnDefinition column = ExcelColumnDefinition.builder()
                                                            .groupTitle(annotation.name())
                                                            .pathField(path)
                                                            .type(declaringClass)
                                                            .build();
        columns.add(column);
        return columns;

         */
        return null;
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return null;
    }
}
