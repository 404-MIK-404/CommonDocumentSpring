package com.mik.common.documents.docx.controller;

import com.mik.common.documents.docx.facade.WordFacade;
import com.mik.common.documents.docx.factory.WordResponseFactory;
import com.mik.common.documents.shared.controller.DocumentAbstractController;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class DocumentWordController extends DocumentAbstractController {

    private final WordFacade facade;

    private final WordResponseFactory factory;

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
        String pathFileTemplate = configuration.getTemplate();
        try (InputStream inputStream = findTemplateDocument(pathFileTemplate)) {
            FileExport file = facade.loadTo(inputStream, configuration);
            return factory.createResponseEntity(file);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при выгрузке документа", e);
        }
    }

}
