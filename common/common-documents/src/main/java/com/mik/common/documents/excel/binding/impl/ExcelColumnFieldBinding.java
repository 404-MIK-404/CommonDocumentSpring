package com.mik.common.documents.excel.binding.impl;

import com.mik.common.documents.excel.model.enums.ExcelTypeColumn;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class ExcelColumnFieldBinding implements ExcelAnnotationBinding {

    @Override
    public List<ExcelColumnDefinition> apply(ExcelAnnotationContext context) {
        /*
        Field field = context.getField();
        ExcelMarkerTableColumn annotation =
                (ExcelMarkerTableColumn) context.getAnnotation();

        if (!DocumentReflectionUtils.isSimpleClass(field.getType())) {
            return Collections.emptyList();
        }

        String path = buildPath(context.getParentPath(), field);

        ExcelColumnDefinition column = ExcelColumnDefinition.builder()
                                                            .name(annotation.name())
                                                            .type(annotation.cell().type())
                                                            .defaultValue(annotation.cell().defaultValue())
                                                            .span(annotation.span())
                                                            .pathField(path)
                                                            .styleColumn(annotation.columnStyle())
                                                            .styleData(annotation.dataStyle())
                                                            .decimal(annotation.decimal())
                                                            .children(new ArrayList<>())
                                                            .totalBindingType(annotation.total())
                                                            .build();

        return List.of(column);

         */
        return null;
    }

    @Override
    public ExcelTypeColumn getBindingType() {
        return ExcelTypeColumn.FIELD;
    }

    /*
    private String buildPath(String parentPath, Field field) {
        return (parentPath == null || parentPath.isEmpty())
               ? field.getName()
               : parentPath + "." + field.getName();
    }

     */
}
