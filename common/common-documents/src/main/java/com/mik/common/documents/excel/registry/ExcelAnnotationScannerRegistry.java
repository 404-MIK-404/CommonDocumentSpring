package com.mik.common.documents.excel.registry;

import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelAnnotationScannerRegistry {

    private final Map<Class<? extends Annotation>, ExcelAnnotationProcessor> analyzers;

    //@SuppressWarnings("unchecked")
    /*
    public <A extends Annotation,T> ExcelAnnotationProcessor<T,C> get(final Class<A> annotationType) {
        return analyzers.get(annotationType);
    }

     */

}
