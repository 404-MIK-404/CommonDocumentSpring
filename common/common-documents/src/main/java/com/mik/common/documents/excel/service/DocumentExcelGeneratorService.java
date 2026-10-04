package com.mik.common.documents.excel.service;

import com.mik.common.documents.excel.model.context.ExcelExportContext;
import com.mik.common.documents.excel.stage.ExcelWorkbookStage;
import com.mik.common.documents.exceptions.runtime.DocumentRuntimeException;
import com.mik.common.documents.shared.model.FileExport;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class DocumentExcelGeneratorService {

    private final DocumentExcelAnnotationService documentExcelAnnotationService;

    private final ExcelWorkbookStage stage;

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


}
