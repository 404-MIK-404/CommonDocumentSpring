package com.mik.common.documents.excel.processor.impl;

import com.mik.common.documents.excel.annotation.column.style.ExcelColumnStyle;
import com.mik.common.documents.excel.annotation.column.style.ExcelDataStyle;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.TableContext;
import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@AllArgsConstructor
public class ExcelDataStyleAnnotationProcessor implements ExcelAnnotationProcessor<ExcelColumnDefinition, TableContext> {

    @Override
    public ExcelColumnDefinition process(TableContext context) {
        return null;
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ExcelDataStyle.class;
    }
}
