package com.mik.common.documents.excel.stage;

import com.mik.common.documents.excel.model.context.ExcelExportContext;
import com.mik.common.documents.excel.util.ExcelRowUtils;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcelSheetStage {

    private final ExcelRowStage stage;

    public void process(Sheet sheet, ExcelExportContext properties) {
        if (sheet.getPhysicalNumberOfRows() > 0) {
            ExcelRowUtils.forEachRowReverse(sheet, row -> {
                stage.processRow(row, properties);
            });
        }
    }
}
