package com.mik.common.documents.exceptions.exception;


import com.mik.common.documents.exceptions.runtime.DocumentAnalyzeAnnotationException;

public class DocumentTemplateNotFoundException extends DocumentAnalyzeAnnotationException {

    public DocumentTemplateNotFoundException(String message) {
        super(message);
    }

    public DocumentTemplateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentTemplateNotFoundException(Throwable cause) {
        super(cause);
    }

    public DocumentTemplateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
