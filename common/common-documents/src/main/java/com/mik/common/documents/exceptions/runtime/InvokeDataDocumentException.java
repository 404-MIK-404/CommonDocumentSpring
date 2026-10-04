package com.mik.common.documents.exceptions.runtime;

public class InvokeDataDocumentException extends DocumentRuntimeException {

    public InvokeDataDocumentException(String message) {
        super(message);
    }

    public InvokeDataDocumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvokeDataDocumentException(Throwable cause) {
        super(cause);
    }

    public InvokeDataDocumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
