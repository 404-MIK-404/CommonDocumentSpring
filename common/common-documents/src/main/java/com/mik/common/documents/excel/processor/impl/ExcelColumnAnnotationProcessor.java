package com.mik.common.documents.excel.processor.impl;

import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ColumnContext;
import com.mik.common.documents.excel.model.enums.ExcelTypeColumn;
import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import com.mik.common.documents.excel.registry.ExcelColumnBindingRegistry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelColumnAnnotationProcessor implements ExcelAnnotationProcessor<ExcelColumnDefinition, ColumnContext> {

    private final ExcelColumnBindingRegistry registry;

    @Override
    public ExcelColumnDefinition process(ColumnContext context) {
        ExcelColumn annotation = (ExcelColumn) context.annotation();
        ExcelTypeColumn columnType = annotation.type();
        ExcelAnnotationBinding binding = registry.get(columnType);
        if (binding == null) return null;
        return binding.binding(context);
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ExcelColumn.class;
    }
}
