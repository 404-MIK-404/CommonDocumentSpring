package com.mik.common.documents.excel.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ExcelSheetUtils {


    public Sheet copySheet(Sheet sourceSheet, Workbook targetWorkbook, String targetSheetName) {
        Sheet targetSheet = targetWorkbook.createSheet(targetSheetName);

        Map<Short, CellStyle> styleCache = new HashMap<>();

        for (Row sourceRow : sourceSheet) {

            Row targetRow = targetSheet.createRow(sourceRow.getRowNum());
            targetRow.setHeight(sourceRow.getHeight());

            for (Cell sourceCell : sourceRow) {

                Cell targetCell = targetRow.createCell(
                        sourceCell.getColumnIndex(),
                        sourceCell.getCellType()
                );

                copyCellValue(sourceCell, targetCell);

                short styleIndex = sourceCell.getCellStyle().getIndex();

                CellStyle targetStyle = styleCache.get(styleIndex);

                if (targetStyle == null) {
                    targetStyle = targetWorkbook.createCellStyle();
                    targetStyle.cloneStyleFrom(sourceCell.getCellStyle());

                    styleCache.put(styleIndex, targetStyle);
                }

                targetCell.setCellStyle(targetStyle);
            }
        }

        int maxColumn = sourceSheet.getRow(0).getLastCellNum();

        for (int columnIndex = 0; columnIndex < maxColumn; columnIndex++) {
            targetSheet.setColumnWidth(
                    columnIndex,
                    sourceSheet.getColumnWidth(columnIndex)
            );

            targetSheet.setColumnHidden(
                    columnIndex,
                    sourceSheet.isColumnHidden(columnIndex)
            );
        }

        // merged regions
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            targetSheet.addMergedRegion(
                    sourceSheet.getMergedRegion(i).copy()
            );
        }

        return targetSheet;
    }

    private void copyCellValue(Cell source, Cell target) {
        switch (source.getCellType()) {
            case STRING -> target.setCellValue(source.getStringCellValue());
            case NUMERIC -> target.setCellValue(source.getNumericCellValue());
            case BOOLEAN -> target.setCellValue(source.getBooleanCellValue());
            case FORMULA -> target.setCellFormula(source.getCellFormula());
            case ERROR -> target.setCellErrorValue(source.getErrorCellValue());
            case BLANK -> {
            }
        }
    }

}
