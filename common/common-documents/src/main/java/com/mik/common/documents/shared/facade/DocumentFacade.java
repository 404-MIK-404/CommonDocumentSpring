package com.mik.common.documents.shared.facade;


import com.mik.common.documents.exceptions.exception.DocumentException;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;

import java.io.InputStream;

public interface DocumentFacade<T extends DocumentConfiguration> {

    FileExport loadTo(InputStream file, DocumentConfiguration configuration) throws DocumentException;

    FileExport exportTo(InputStream file, DocumentConfiguration configuration) throws DocumentException;

    FileExport toExport(InputStream file, T configuration) throws DocumentException;

}
