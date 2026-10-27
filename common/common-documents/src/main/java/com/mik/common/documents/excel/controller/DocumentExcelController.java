package com.mik.common.documents.excel.controller;

import com.mik.common.documents.excel.facade.DocumentExcelFacade;
import com.mik.common.documents.excel.factory.DocumentExcelResponseResponseFactory;
import com.mik.common.documents.excel.model.configuration.DocumentExcelConfiguration;
import com.mik.common.documents.shared.controller.DocumentAbstractController;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
@RequiredArgsConstructor
public class DocumentExcelController extends DocumentAbstractController<DocumentExcelConfiguration> {

    private final DocumentExcelResponseResponseFactory factory;

    private final DocumentExcelFacade facade;

    @Override
    public ResponseEntity<byte[]> exportDocument(DocumentConfiguration configuration) {
        String pathFileTemplate = configuration.getTemplate();
        try (InputStream templateStream = findTemplateDocument(pathFileTemplate)) {
            Path tempFile = Files.createTempFile("template-", ".xlsx");
            Files.copy(templateStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            try (InputStream tempFileStream = Files.newInputStream(tempFile)) {
                FileExport file = facade.exportTo(tempFileStream,configuration);
                return factory.createResponseEntity(file);
            } finally {
                Files.deleteIfExists(tempFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при экспорте документа", e);
        }
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
