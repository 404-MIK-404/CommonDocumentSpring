package com.mik.common.documents.excel.processor.column;


import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ProcessorContext;

import java.lang.annotation.Annotation;
import java.util.List;

public interface ExcelAnnotationProcessor<C extends ProcessorContext> {

    void process(List<ExcelColumnDefinition> columns, C context);

    Class<? extends Annotation> getAnnotationClass();

}
