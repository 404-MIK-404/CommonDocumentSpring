package com.mik.common.documents.excel.stage;

import com.mik.common.documents.excel.model.context.ExcelExportContext;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcelWorkbookStage {

    private final ExcelSheetStage stage;

    public void process(Workbook workbook, ExcelExportContext properties) {
        int numOfSheet = workbook.getNumberOfSheets();
        for (int i = 0; i < numOfSheet; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            stage.process(sheet, properties);
        }
    }


}
