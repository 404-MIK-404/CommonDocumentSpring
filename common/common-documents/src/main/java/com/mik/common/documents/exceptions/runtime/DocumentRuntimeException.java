package com.mik.common.documents.exceptions.runtime;

public class DocumentRuntimeException extends RuntimeException {

    public DocumentRuntimeException(String message) {
        super(message);
    }

    public DocumentRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentRuntimeException(Throwable cause) {
        super(cause);
    }

    public DocumentRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
