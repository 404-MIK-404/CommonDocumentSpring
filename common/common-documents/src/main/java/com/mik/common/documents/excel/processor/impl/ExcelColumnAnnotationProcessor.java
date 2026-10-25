package com.mik.common.documents.excel.processor.impl;

import com.mik.common.documents.excel.annotation.column.ExcelMarkerTableColumn;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import com.mik.common.documents.excel.registry.ExcelColumnBindingRegistry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelColumnAnnotationProcessor implements ExcelAnnotationProcessor<List<ExcelColumnDefinition>> {

    private final ExcelColumnBindingRegistry bindingRegistry;

    @Override
    public List<ExcelColumnDefinition> process(ExcelAnnotationContext context) {
        /*
        ExcelMarkerTableColumn annotation = (ExcelMarkerTableColumn) context.getAnnotation();

        ExcelAnnotationBinding strategy = bindingRegistry.get(annotation.type());
        if (strategy == null) return new ArrayList<>();
        List<ExcelColumnDefinition> columns = strategy.apply(context);
        if (columns == null || columns.isEmpty()) return new ArrayList<>();
        return columns;

         */
        return null;
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ExcelMarkerTableColumn.class;
    }
}
