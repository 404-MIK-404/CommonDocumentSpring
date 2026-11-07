package com.mik.common.documents.excel.processor.column;


import com.mik.common.documents.excel.model.context.ProcessorContext;

import java.lang.annotation.Annotation;

public interface ExcelAnnotationProcessor<T, C extends ProcessorContext> {

    T process(C context);

    Class<? extends Annotation> getAnnotationClass();

}
