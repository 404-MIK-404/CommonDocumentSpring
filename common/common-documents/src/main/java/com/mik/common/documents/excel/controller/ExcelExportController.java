package com.mik.common.documents.excel.controller;

import com.mik.common.documents.template.AbstractDocumentExportController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Данный класс помечен @Deprecated
 * Необходимо перейти на DocumentExcelController.class
 */
@Deprecated(since = "Перейдите на DocumentExcelController.class")
@Component
@RequiredArgsConstructor
public class ExcelExportController extends AbstractDocumentExportController {

    //private final DocumentExcelGeneratorService documentExcelGeneratorService;

    //private final ClassLoader classLoader = this.getClass().getClassLoader();

    //private ExcelExportContext excelExportContext = ExcelExportContext.builder().build();

    /*
    @Deprecated
    public ResponseEntity<byte[]> prepareExport(String fileName,String pathFileTemplate, Map<String,Object> properties, Map<Class<?>, List<?>> tableMap) {
        this.excelExportContext = ExcelExportContext.builder()
                                                    .properties(properties)
                                                    .data(tableMap)
                                                    .build();
        try {
            FileExport fileExport = export(fileName, pathFileTemplate);
            return toResponse(fileExport);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    @Deprecated
    protected FileExport export(String fileName, String pathFileTemplate) throws DocumentException {
        try (InputStream templateStream = classLoader.getResourceAsStream(pathFileTemplate)) {
            if (templateStream == null) {
                throw new DocumentTemplateNotFoundException("Файл шаблона не найден: " + pathFileTemplate);
            }
            Path tempFile = Files.createTempFile("template-", ".xlsx");
            Files.copy(templateStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            try (InputStream tempFileStream = Files.newInputStream(tempFile)) {
                return documentExcelGeneratorService.exportDocument(fileName, tempFileStream, excelExportContext);
            } finally {
                Files.deleteIfExists(tempFile);
            }
        } catch (IOException e) {
            throw new DocumentParsingException("Ошибка при экспорте документа", e);
        }
    }

    @Override
    @Deprecated
    protected ResponseEntity<byte[]> toResponse(FileExport fileExport) {
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                          "attachment; filename=\"report.xlsx\"; filename*=UTF-8''" + URLEncoder.encode(fileExport.name(), StandardCharsets.UTF_8))
                             .contentType(DocumentMediaTypes.APPLICATION_XLSX)
                             .contentLength(fileExport.resource().length)
                             .body(fileExport.resource());
    }

     */

}
