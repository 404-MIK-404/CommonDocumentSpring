package com.mik.common.documents.excel.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

@UtilityClass
public class ExcelCellUtils {


    public void merge(CellStyle style, Sheet sheet, int r1, int r2, int c1, int c2, Set<String> merged) {
        if (r1 == r2 && c1 == c2) {
            return;
        }
        String key = r1 + ":" + r2 + ":" + c1 + ":" + c2;
        if (merged != null && !merged.add(key)) return;

        for (int r = r1; r <= r2; r++) {
            Row row = ExcelRowUtils.createRowByIndex(sheet, r);
            for (int c = c1; c <= c2; c++) {
                Cell ccc = ExcelCellUtils.createCellByRow(row, c);
                ccc.setCellStyle(style);
            }
        }
        CellRangeAddress range = new CellRangeAddress(r1, r2, c1, c2);
        sheet.addMergedRegion(range);
    }


    public void mergeCell(CellStyle style, Sheet sheet, int indexRowFrom, int indexRowTo, int indexColFrom, int indexColTo) {
        if (indexRowFrom == indexRowTo && indexColFrom == indexColTo) return;

        //String key = indexRowFrom + ":" + indexRowTo + ":" + indexColFrom + ":" + indexColTo;
        //if (merged != null && !merged.add(key)) return;

        for (int r = indexRowFrom; r <= indexRowTo; r++) {
            Row row = ExcelRowUtils.createRowByIndex(sheet, r);
            for (int c = indexColFrom; c <= indexColFrom; c++) {
                Cell ccc = ExcelCellUtils.createCellByRow(row, c);
                ccc.setCellStyle(style);
            }
        }
        CellRangeAddress range = new CellRangeAddress(indexRowFrom, indexRowTo, indexColFrom, indexColTo);
        sheet.addMergedRegion(range);
    }

    public Cell createCellByRow(Row row,int indexCell) {
        Cell cell = row.getCell(indexCell);
        if (cell == null) {
            cell = row.createCell(indexCell);
        }
        return cell;
    }


    public int getVerticalMergeSize(Sheet sheet, int row, int col) {
        for (CellRangeAddress region : sheet.getMergedRegions()) {
            if (region.isInRange(row, col)) {
                int firstRow = region.getFirstRow();
                int lastRow = region.getLastRow();
                return lastRow - firstRow + 1;
            }
        }
        return 1;
    }


    public int getHorizontalMergeSize(Sheet sheet, int row, int col) {
        for (CellRangeAddress region : sheet.getMergedRegions()) {
            if (region.isInRange(row, col)) {
                int firstCol = region.getFirstColumn();
                int lastCol  = region.getLastColumn();
                return lastCol - firstCol + 1;
            }
        }
        return 1;
    }


    public int getNextHorizontalIndexWithMerge(Cell cell,int currentIndex) {
        int horizCount = ExcelCellUtils.getHorizontalMergeSize(cell.getSheet(), cell.getRowIndex(), cell.getColumnIndex());
        if (horizCount > 1) {
            return currentIndex + horizCount;
        } else {
            return currentIndex + 1;
        }
    }

    public int getNextVerticalIndexWithMerge(Cell cell,int currentIndex) {
        int verticCount = ExcelCellUtils.getVerticalMergeSize(cell.getSheet(), cell.getRowIndex(), cell.getColumnIndex());
        if (verticCount > 1) {
            return currentIndex + verticCount;
        } else {
            return currentIndex + 1;
        }
    }

    // Добавлен try-catch для избежания проблем с поломкой верстки XML
    public String cellValueToString(Cell cell) {
        if (cell == null) return "";

        try {
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            return "";
        }
    }

    public void forEachReverse(Row row, Consumer<Cell> consumer) {
        short lastCellNum = row.getLastCellNum();
        if (lastCellNum < 0) return;
        for (int j = lastCellNum - 1; j >= 0; j--) {
            Cell cell = row.getCell(j);
            if (cell != null) {
                consumer.accept(cell);
            }
        }
    }

    public void forEach(Row row, Consumer<Cell> consumer) {
        short lastCellNum = row.getLastCellNum();
        if (lastCellNum < 0) return;
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                consumer.accept(cell);
            }
        }
    }

    public boolean isCellAndValueIsNull(Cell cell, Object value) {
        return Objects.isNull(cell) && Objects.isNull(value);
    }

}
