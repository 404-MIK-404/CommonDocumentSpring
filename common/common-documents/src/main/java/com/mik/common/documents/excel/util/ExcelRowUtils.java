package com.mik.common.documents.excel.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import java.util.function.Consumer;

@UtilityClass
public class ExcelRowUtils {


    public void shiftRowsDown(Sheet sheet, int startRow, int n) {
        int endRow = sheet.getLastRowNum();
        if (endRow >= startRow) {
            sheet.shiftRows(startRow, endRow, n, true, false);
        }
    }

    public void copyRow(Row srcRow, Row targetRow, int indexTargetRow) {
        CellCopyPolicy policy = new CellCopyPolicy.Builder()
                                        .cellStyle(true)
                                        .cellValue(true)
                                        .cellFormula(true)
                                        .build();

        Sheet sheet = srcRow.getSheet();
        if (targetRow == null) {
            targetRow = sheet.createRow(indexTargetRow);
        }

        for (Cell srcCell : srcRow) {
            Cell targetCell = targetRow.createCell(srcCell.getColumnIndex());
            CellUtil.copyCell(srcCell, targetCell, policy, null);
        }
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            if (mergedRegion.getFirstRow() == srcRow.getRowNum()) {
                CellRangeAddress newMergedRegion = new CellRangeAddress(
                        targetRow.getRowNum(),
                        targetRow.getRowNum(),
                        mergedRegion.getFirstColumn(),
                        mergedRegion.getLastColumn()
                );

                // Добавил проверку на дубль объединенной ячейки
                boolean overlaps = false;
                for (int j = 0; j < sheet.getNumMergedRegions(); j++) {
                    CellRangeAddress existing = sheet.getMergedRegion(j);
                    if (existing.intersects(newMergedRegion)) {
                        overlaps = true;
                        break;
                    }
                }
                if (!overlaps) {
                    sheet.addMergedRegion(newMergedRegion);
                }
            }
        }
        targetRow.setHeight(srcRow.getHeight());
    }

    public Row createRowByIndex(Sheet sheet, int index) {
        Row row = sheet.getRow(index);
        if (row == null) {
            row = sheet.createRow(index);
        }
        return row;
    }

    public void forEachRowReverse(Sheet sheet,Consumer<Row> consumer) {
        for (int i = sheet.getLastRowNum(); i >= 0; i--) {
            Row row = sheet.getRow(i);
            if (row == null || row.getLastCellNum() < 0) {
                continue;
            }
            consumer.accept(row);
        }
    }

}
