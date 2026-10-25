package com.mik.common.documents.docx.facade;

import com.mik.common.documents.exceptions.exception.DocumentException;
import com.mik.common.documents.shared.facade.DocumentFacade;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class WordFacade implements DocumentFacade {

    @Override
    public FileExport loadTo(InputStream file, DocumentConfiguration configuration) {
        try {
            String fileName = configuration.getFileName();
            byte[] fileBytes = IOUtils.toByteArray(file);
            return new FileExport(fileName, fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileExport exportTo(InputStream file, DocumentConfiguration configuration) {
        return null;
    }

    @Override
    public FileExport toExport(InputStream file, DocumentConfiguration configuration) throws DocumentException {
        return null;
    }
}
