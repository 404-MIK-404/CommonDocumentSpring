package com.mik.common.documents.excel.processor.column.impl;

import com.mik.common.documents.excel.annotation.column.ExcelGroupColumn;
import com.mik.common.documents.excel.annotation.column.style.ExcelColumnStyle;
import com.mik.common.documents.excel.annotation.column.style.ExcelDataStyle;
import com.mik.common.documents.excel.factory.DocumentExcelDefinitionFactory;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.column.ExcelGroupColumnDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelColumnStyleDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelDataStyleDefinition;
import com.mik.common.documents.excel.model.context.ColumnContext;
import com.mik.common.documents.excel.processor.column.ExcelAnnotationProcessor;
import com.mik.common.documents.excel.util.DocumentExcelAnnotationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ExcelColumnGroupAnnotationProcessor implements ExcelAnnotationProcessor<List<ExcelColumnDefinition>, ColumnContext> {

    private final DocumentExcelDefinitionFactory factory;

    @Override
    public List<ExcelColumnDefinition> process(ColumnContext context) {
        List<ExcelColumnDefinition> columns = context.columns();
        ExcelGroupColumn annotation = (ExcelGroupColumn) context.annotation();
        ExcelColumnDefinition column = context.column();
        Field field = context.field();
        Class<?> declaringClass = field.getType();
        String path = DocumentExcelAnnotationUtil.buildPathField(column, field);
        ExcelColumnStyle columnStyle = field.getAnnotation(ExcelColumnStyle.class);
        ExcelDataStyle dataStyle = field.getAnnotation(ExcelDataStyle.class);
        ExcelGroupColumnDefinition group = ExcelGroupColumnDefinition.builder()
                .title(annotation.name())
                .build();
        if (Objects.isNull(column)) {
            ExcelColumnStyleDefinition styleColumnDefinition = factory.onCreateColumnStyle(columnStyle);
            ExcelDataStyleDefinition styleDataStyleDefinition = factory.onCreateDataStyle(dataStyle);
            ExcelColumnDefinition columnDefinition = factory.onCreateColumnDefinition(null,path, styleColumnDefinition, styleDataStyleDefinition);
            columnDefinition.setClazz(declaringClass);
            columnDefinition.setGroups(group);
            columns.add(columnDefinition);
        } else {
            column.setGroups(group);
            //columns.add(column);
        }
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
        return ExcelGroupColumn.class;
    }
}
