package com.mik.common.documents.exceptions.runtime;

public class DocumentAnalyzeAnnotationException extends DocumentRuntimeException {

    public DocumentAnalyzeAnnotationException(String message) {
        super(message);
    }

    public DocumentAnalyzeAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentAnalyzeAnnotationException(Throwable cause) {
        super(cause);
    }

    public DocumentAnalyzeAnnotationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
