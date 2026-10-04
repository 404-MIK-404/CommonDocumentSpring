package com.mik.common.documents.shared.mediatype;

import org.springframework.http.MediaType;

public final class DocumentMediaTypes {

    private DocumentMediaTypes() {}

    public static final MediaType APPLICATION_XLSX = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    public static final MediaType APPLICATION_DOCX = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    public static final MediaType APPLICATION_XLS = MediaType.parseMediaType("application/vnd.ms-excel");

    public static final MediaType APPLICATION_PDF = MediaType.parseMediaType("application/pdf");

}
