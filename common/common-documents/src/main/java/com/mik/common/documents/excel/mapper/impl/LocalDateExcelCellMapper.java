package com.mik.common.documents.excel.mapper.impl;

import com.mik.common.documents.excel.mapper.ExcelExportCellMapper;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.util.ExcelCellStyleUtils;
import com.mik.common.documents.excel.util.ExcelCellUtils;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
@AllArgsConstructor
public class LocalDateExcelCellMapper implements ExcelExportCellMapper<LocalDate> {

    @Override
    public void writeToCell(Cell cell, LocalDate value, ExcelColumnDefinition column) {
        if (Objects.isNull(cell) || Objects.isNull(column)) return;
        cell.setCellValue(value);
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle style = cell.getCellStyle();
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(style);
        try {
            ExcelCellStyleUtils.applyConfigStyle(workbook,cell,newStyle, column.getStyleColumn());
            ExcelCellStyleUtils.applyConfigStyle(workbook,cell,newStyle,column.getStyleData());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //applyCellStyle(workbook,cell,newStyle,column.getStyleColumn());
        //applyCellStyle(workbook,cell,newStyle,column.getStyleData());
        cell.setCellStyle(newStyle);
    }

    @Override
    public void writeToCell(Cell cell, LocalDate value, ExcelColumnDefinition column, CellStyle baseStyle) {
        if (ExcelCellUtils.isCellAndValueIsNull(cell, value)) return;

        cell.setCellValue(value);
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(baseStyle);

        if (column != null) {
            try {
                if (!Objects.isNull(column.getStyleColumn())) {
                    ExcelCellStyleUtils.applyConfigStyle(workbook,cell,newStyle, column.getStyleColumn());
                }
                if (!Objects.isNull(column.getStyleData())) {
                    ExcelCellStyleUtils.applyConfigStyle(workbook,cell,newStyle,column.getStyleData());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        cell.setCellStyle(newStyle);
    }

    @Override
    public void writeToCell(Cell cell, LocalDate value) {
        cell.setCellValue(value);
    }

    /*
    private void applyCellStyle(Workbook workbook, Cell cell, CellStyle style, ExcelMarkerTableStyleColumn styleColumn) {
        if (styleColumn == null) return;
        Row row = cell.getRow();
        Sheet sheet = cell.getSheet();
        int indexColumn = cell.getColumnIndex();
        try {
            ExcelCellStyleUtils.setHorizontalAlignment(style, styleColumn.style().horizontal());
            ExcelCellStyleUtils.setVerticalAlignment(style,styleColumn.style().vertical());
            ExcelCellStyleUtils.setWrapText(style,styleColumn.style().wrap());
            ExcelCellStyleUtils.setBoldText(workbook,style,styleColumn.style().bold());
            ExcelCellStyleUtils.setHeight(row,styleColumn.style().height());
            ExcelCellStyleUtils.setWidth(sheet,indexColumn,styleColumn.style().width());
            if (!Objects.isNull(styleColumn.color())) ExcelCellStyleUtils.setBackgroundColor(workbook,styleColumn.color().color(),style);
        } catch (DecoderException ex) {
            throw new RuntimeException(ex);
        }
    }

     */

    @Override
    public LocalDate readFromCell(Cell cell) {
        return cell.getLocalDateTimeCellValue()
                   .toLocalDate();
    }

    @Override
    public Class<LocalDate> getTypeCellMapper() {
        return LocalDate.class;
    }
}
