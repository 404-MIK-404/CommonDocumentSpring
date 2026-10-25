package com.mik.common.documents.excel.controller;

import com.mik.common.documents.excel.facade.DocumentExcelFacade;
import com.mik.common.documents.excel.factory.DocumentExcelResponseResponseFactory;
import com.mik.common.documents.excel.model.configuration.DocumentExcelConfiguration;
import com.mik.common.documents.shared.controller.DocumentAbstractController;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
        return null;
    }

    public ResponseEntity<byte[]> mergeDocument(
            List<DocumentConfiguration> configurations,
            DocumentConfiguration configurationMain
    ) {
        String pathFileTemplate = configurationMain.getTemplate();
        try (InputStream fileMain = findTemplateDocument(pathFileTemplate);
             Workbook workbook = WorkbookFactory.create(fileMain) ) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            for (DocumentConfiguration configuration : configurations) {
                String pathFileDynamicReport = configuration.getTemplate();
                try (InputStream dynamicReportStream = findTemplateDocument(pathFileDynamicReport)) {
                    facade.mergeDynamicReport(dynamicReportStream,workbook,configuration);
                }
            }
            workbook.write(output);
            try (InputStream mergedInput = new ByteArrayInputStream(output.toByteArray())) {
                FileExport fileRes = facade.exportTo(mergedInput, configurationMain);
                return factory.createResponseEntity(fileRes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при экспорте документа", e);
        }
    }

}
