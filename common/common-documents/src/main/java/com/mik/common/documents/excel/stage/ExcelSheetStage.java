package com.mik.common.documents.excel.stage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcelSheetStage {

    private final ExcelRowStage stage;

    /*
    public void process(Sheet sheet, ExcelExportContext properties) {
        if (sheet.getPhysicalNumberOfRows() > 0) {
            ExcelRowUtils.forEachRowReverse(sheet, row -> {
                stage.processRow(row, properties);
            });
        }
    }

     */
}
