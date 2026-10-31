package com.mik.common.documents.excel.stage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcelRowStage {

    private final ExcelCellStage stage;

    /*
    public void processRow(Row row, ExcelExportContext context) {
        ExcelCellUtils.forEach(row, cell -> {
            stage.processCell(cell, context);
        });
    }

     */

}

