package com.mik.common.documents.exceptions.exception;

public class DocumentParsingException extends DocumentException {

    public DocumentParsingException(String message) {
        super(message);
    }

    public DocumentParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentParsingException(Throwable cause) {
        super(cause);
    }

    public DocumentParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
