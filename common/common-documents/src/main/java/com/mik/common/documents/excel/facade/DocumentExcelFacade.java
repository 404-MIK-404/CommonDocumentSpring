package com.mik.common.documents.excel.facade;

import com.mik.common.documents.excel.factory.DocumentExcelContextFactory;
import com.mik.common.documents.excel.model.configuration.DocumentExcelConfiguration;
import com.mik.common.documents.excel.model.context.ExcelExportContext;
import com.mik.common.documents.excel.service.DocumentExcelAnnotationService;
import com.mik.common.documents.excel.stage.ExcelWorkbookStage;
import com.mik.common.documents.excel.util.ExcelSheetUtils;
import com.mik.common.documents.exceptions.runtime.DocumentRuntimeException;
import com.mik.common.documents.shared.facade.DocumentFacade;
import com.mik.common.documents.shared.model.DocumentConfiguration;
import com.mik.common.documents.shared.model.FileExport;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class DocumentExcelFacade implements DocumentFacade<DocumentExcelConfiguration> {

    private final DocumentExcelAnnotationService documentExcelAnnotationService;

    private final DocumentExcelContextFactory factory;

    private final ExcelWorkbookStage workbookStage;

    @Override
    public FileExport loadTo(InputStream file, DocumentConfiguration configuration) {
        return null;
    }

    @Override
    public FileExport exportTo(InputStream file, DocumentConfiguration configuration) {
        ExcelExportContext context = factory.createExportContext(configuration);
        try (Workbook workbook = WorkbookFactory.create(file)) {
            workbook.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            buildMetadata(context);
            workbookStage.process(workbook, context);
            workbook.setForceFormulaRecalculation(true);
            addSheetsNames(configuration,null, workbook);
            return buildFileExport(configuration.getFileName(),workbook);
        } catch (IOException ex) {
            throw new DocumentRuntimeException(ex);
        }
    }

    @Override
    public FileExport toExport(InputStream file, DocumentExcelConfiguration configuration) throws DocumentException {
        ExcelExportContext context = factory.createExportContext(configuration);
        try (Workbook workbook = WorkbookFactory.create(file)) {
            workbook.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            buildMetadata(context);
            workbookStage.process(workbook, context);
            workbook.setForceFormulaRecalculation(true);
            addSheetsNames(configuration, null,workbook);
            return buildFileExport(configuration.getFileName(),workbook);
        } catch (IOException ex) {
            throw new DocumentRuntimeException(ex);
        }
    }

    private void buildMetadata(ExcelExportContext context) {
        documentExcelAnnotationService.processTableAnnotations(context);
        documentExcelAnnotationService.processColumnAnnotations(context);
        documentExcelAnnotationService.processColumnGroupAnnotations(context);
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

    public void mergeDynamicReport(InputStream file, Workbook mainWorkbook, DocumentConfiguration configuration) {
        ExcelExportContext context = factory.createExportContext(configuration);
        try (Workbook workbook = WorkbookFactory.create(file)) {
            workbook.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            buildMetadata(context);
            workbookStage.process(workbook, context);
            workbook.setForceFormulaRecalculation(true);
            addSheetsNames(configuration,workbook,mainWorkbook);
            buildFileExport(configuration.getFileName(), workbook);
        } catch (IOException ex) {
            throw new DocumentRuntimeException(ex);
        }
    }


    private void addSheetsNames(DocumentConfiguration configuration,Workbook workbookSource ,Workbook workbookTarget) {
        if (Objects.isNull(workbookSource) || Objects.isNull(workbookTarget)) return;
        List<String> workbookNames = configuration.getWorkbookNames();
        int minIndex = Math.min(workbookNames.size(),workbookSource.getNumberOfSheets());
        for (int i = 0; i < minIndex; i++) {
            Sheet sheet = workbookSource.getSheetAt(i);
            String sheetName = workbookNames.get(i);
            ExcelSheetUtils.copySheet(sheet,workbookTarget,sheetName);
        }
    }

}
