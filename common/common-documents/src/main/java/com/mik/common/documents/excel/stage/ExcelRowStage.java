package com.mik.common.documents.excel.stage;

import com.mik.common.documents.excel.model.context.ExcelExportContext;
import com.mik.common.documents.excel.util.ExcelCellUtils;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcelRowStage {

    private final ExcelCellStage stage;

    public void processRow(Row row, ExcelExportContext context) {
        ExcelCellUtils.forEach(row, cell -> {
            stage.processCell(cell, context);
        });
    }

}

