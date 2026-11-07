package com.mik.common.documents.excel.service;

import com.mik.common.documents.excel.stage.ExcelWorkbookStage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentExcelGeneratorService {

    private final DocumentExcelAnnotationService documentExcelAnnotationService;

    private final ExcelWorkbookStage stage;

    /*

    public FileExport exportDocument(String fileName, InputStream pathFileTemplate, ExcelExportContext properties) {
        documentExcelAnnotationService.processTableAnnotations(properties);
        documentExcelAnnotationService.processColumnAnnotations(properties);
        documentExcelAnnotationService.processColumnGroupAnnotations(properties);
        try (Workbook workbook = WorkbookFactory.create(pathFileTemplate)) {
            workbook.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            stage.process(workbook, properties);
            workbook.setForceFormulaRecalculation(true);
            return buildFileExport(fileName,workbook);
        } catch (IOException ex) {
            throw new DocumentRuntimeException(ex);
        }
    }


    private FileExport buildFileExport(String exportFileName, Workbook workbook) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            return FileExport.builder()
                             .name(exportFileName)
                             .resource(byteArrayOutputStream.toByteArray())
                             .build();
        }
    }

     */


}
