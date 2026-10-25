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


import java.util.Objects;

@Component
@AllArgsConstructor
public class BooleanExcelCellMapper implements ExcelExportCellMapper<Boolean> {

    @Override
    public void writeToCell(Cell cell, Boolean value, ExcelColumnDefinition column) {
        /*
        if (Objects.isNull(cell)) return;
        cell.setCellValue(value);
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle style = cell.getCellStyle();
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(style);
        if (!Objects.isNull(column)) {
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

         */
    }

    @Override
    public void writeToCell(Cell cell, Boolean value, ExcelColumnDefinition column, CellStyle baseStyle) {
        /*
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

         */
    }

    @Override
    public void writeToCell(Cell cell, Boolean value) {
        //cell.setCellValue(value);
    }

    @Override
    public Boolean readFromCell(Cell cell) {
        return cell.getBooleanCellValue();
    }

    @Override
    public Class<Boolean> getTypeCellMapper() {
        return Boolean.class;
    }

}
