package com.mik.common.documents.pdf.controller;

import com.mik.common.documents.pdf.facade.DocumentPdfFacade;
import com.mik.common.documents.pdf.factory.DocumentPdfResponseFactory;
import com.mik.common.documents.shared.controller.DocumentAbstractController;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentPdfController extends DocumentAbstractController {

    private final DocumentPdfFacade facade;

    private final DocumentPdfResponseFactory factory;

    @Override
    public ResponseEntity<byte[]> exportDocument(DocumentConfiguration configuration) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> importDocument() {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> loadDocument(DocumentConfiguration configuration) {
        return null;
    }


}
