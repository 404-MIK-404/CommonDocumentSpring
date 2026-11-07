package com.mik.common.documents.excel.binding.impl;

import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.annotation.column.style.ExcelColumnStyle;
import com.mik.common.documents.excel.annotation.column.style.ExcelDataStyle;
import com.mik.common.documents.excel.annotation.style.ExcelColorCell;
import com.mik.common.documents.excel.annotation.style.ExcelStyleCell;
import com.mik.common.documents.excel.factory.DocumentExcelDefinitionFactory;
import com.mik.common.documents.excel.model.column.style.ExcelColumnStyleDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelDataStyleDefinition;
import com.mik.common.documents.excel.model.context.ColumnContext;
import com.mik.common.documents.excel.model.enums.ExcelTypeColumn;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import com.mik.common.documents.excel.model.style.ExcelCellColorDefinition;
import com.mik.common.documents.excel.model.style.ExcelCellStyleDefinition;
import com.mik.common.documents.excel.util.DocumentExcelAnnotationUtil;
import com.mik.common.documents.util.DocumentReflectionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;


@Component
@AllArgsConstructor
public class ExcelColumnFieldBinding implements ExcelAnnotationBinding {


    private final DocumentExcelDefinitionFactory factory;

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
    public ExcelColumnDefinition binding(ColumnContext context) {
        Field field = context.field();
        ExcelColumn columnAnnotation = (ExcelColumn) context.annotation();
        ExcelColumnStyle columnStyle = field.getAnnotation(ExcelColumnStyle.class);
        ExcelDataStyle dataStyle = field.getAnnotation(ExcelDataStyle.class);
        if (!DocumentReflectionUtils.isSimpleClass(field.getType())) return null;
        ExcelColumnDefinition column = context.column();
        String path = DocumentExcelAnnotationUtil.buildPathField(column, field);
        ExcelColumnStyleDefinition columnStyleDefinition = factory.onCreateColumnStyle(columnStyle);
        ExcelDataStyleDefinition dataStyleDefinition = factory.onCreateDataStyle(dataStyle);

        return factory.onCreateColumnDefinition(
                columnAnnotation,path, columnStyleDefinition,dataStyleDefinition
        );
    }


    @Override
    public ExcelTypeColumn getBindingType() {
        return ExcelTypeColumn.FIELD;
    }

}
