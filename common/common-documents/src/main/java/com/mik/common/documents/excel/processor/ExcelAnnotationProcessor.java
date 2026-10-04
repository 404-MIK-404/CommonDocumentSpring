package com.mik.common.documents.excel.processor;


import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;

import java.lang.annotation.Annotation;

public interface ExcelAnnotationProcessor<T> {


    T process(ExcelAnnotationContext context);

    Class<? extends Annotation> getAnnotationClass();

}
