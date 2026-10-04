package com.mik.common.documents.exceptions.exception;

public class DocumentWriteException extends DocumentException {

    public DocumentWriteException(String message) {
        super(message);
    }

    public DocumentWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentWriteException(Throwable cause) {
        super(cause);
    }

    public DocumentWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
