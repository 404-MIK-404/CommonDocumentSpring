package com.mik.common.documents.excel.processor.table;


import java.lang.annotation.Annotation;

public interface ExcelOneCreateAnnotationProcessor<T, C> {

    T process(C context);

    Class<? extends Annotation> annotation();

}
