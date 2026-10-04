package com.mik.common.documents.pdf.facade;

import com.mik.common.documents.shared.facade.DocumentFacade;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@AllArgsConstructor
public class DocumentPdfFacade implements DocumentFacade {

    @Override
    public FileExport loadTo(InputStream file, DocumentConfiguration configuration) throws DocumentException {
        return null;
    }

    @Override
    public FileExport exportTo(InputStream file, DocumentConfiguration configuration) throws DocumentException {
        return null;
    }

    @Override
    public FileExport toExport(InputStream file, DocumentConfiguration configuration) throws DocumentException {
        return null;
    }

}
