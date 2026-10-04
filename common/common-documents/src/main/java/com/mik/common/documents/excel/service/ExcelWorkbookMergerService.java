package com.mik.common.documents.excel.service;

import com.mik.common.documents.exceptions.runtime.DocumentRuntimeException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelWorkbookMergerService {

    public record NamedSheet(String sheetName, byte[] workbookBytes) {}

    public byte[] merge(List<NamedSheet> sheets) {
        try (XSSFWorkbook target = new XSSFWorkbook()) {
            Set<String> usedNames = new HashSet<>();

            for (NamedSheet named : sheets) {
                try (XSSFWorkbook source = (XSSFWorkbook) WorkbookFactory.create(new ByteArrayInputStream(named.workbookBytes()))) {
                    Sheet sourceSheet = source.getSheetAt(0);
                    String targetName = uniqueSheetName(named.sheetName(), usedNames);
                    copySheet(sourceSheet, target, targetName);
                }
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                target.write(out);
                return out.toByteArray();
            }
        } catch (IOException e) {
            throw new DocumentRuntimeException(e);
        }
    }

    private void copySheet(Sheet source, XSSFWorkbook target, String targetSheetName) {
        Sheet targetSheet = target.createSheet(targetSheetName);
        Map<Integer, CellStyle> styleCache = new HashMap<>();

        copyColumnWidths(source, targetSheet);
        copyMergedRegions(source, targetSheet);

        for (int r = 0; r <= source.getLastRowNum(); r++) {
            Row sourceRow = source.getRow(r);
            if (sourceRow == null) continue;

            Row targetRow = targetSheet.createRow(r);
            targetRow.setHeight(sourceRow.getHeight());

            for (int c = 0; c < sourceRow.getLastCellNum(); c++) {
                Cell sourceCell = sourceRow.getCell(c);
                if (sourceCell == null) continue;

                Cell targetCell = targetRow.createCell(c);
                copyCellValue(sourceCell, targetCell);
                targetCell.setCellStyle(getOrCloneStyle(sourceCell.getCellStyle(), target, styleCache));
            }
        }
    }

    private void copyColumnWidths(Sheet source, Sheet target) {
        int maxCol = 0;
        for (Row row : source) {
            maxCol = Math.max(maxCol, row.getLastCellNum());
        }
        for (int c = 0; c < maxCol; c++) {
            target.setColumnWidth(c, source.getColumnWidth(c));
        }
    }

    private void copyMergedRegions(Sheet source, Sheet target) {
        for (CellRangeAddress region : source.getMergedRegions()) {
            target.addMergedRegion(new CellRangeAddress(
                    region.getFirstRow(), region.getLastRow(),
                    region.getFirstColumn(), region.getLastColumn()
            ));
        }
    }

    private void copyCellValue(Cell source, Cell target) {
        switch (source.getCellType()) {
            case STRING -> target.setCellValue(source.getStringCellValue());
            case NUMERIC -> target.setCellValue(source.getNumericCellValue());
            case BOOLEAN -> target.setCellValue(source.getBooleanCellValue());
            case FORMULA -> target.setCellFormula(source.getCellFormula());
            case BLANK -> target.setBlank();
            default -> {  }
        }
    }

    private CellStyle getOrCloneStyle(CellStyle sourceStyle, XSSFWorkbook target, Map<Integer, CellStyle> cache) {
        int sourceIndex = sourceStyle.getIndex();
        return cache.computeIfAbsent((int) sourceIndex, k -> {
            XSSFCellStyle newStyle = target.createCellStyle();
            newStyle.cloneStyleFrom(sourceStyle);
            return newStyle;
        });
    }

    private String uniqueSheetName(String rawName, Set<String> used) {
        String cleaned = rawName.replaceAll("[\\\\/?*\\[\\]:]", " ").trim();
        if (cleaned.isEmpty()) cleaned = "Лист";
        if (cleaned.length() > 31) cleaned = cleaned.substring(0, 31);

        String candidate = cleaned;
        int suffix = 1;
        while (!used.add(candidate)) {
            String suffixStr = " (" + suffix++ + ")";
            candidate = cleaned.substring(0, Math.min(cleaned.length(), 31 - suffixStr.length())) + suffixStr;
        }
        return candidate;
    }
}