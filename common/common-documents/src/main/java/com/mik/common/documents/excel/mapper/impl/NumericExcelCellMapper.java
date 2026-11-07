package com.mik.common.documents.excel.mapper.impl;

import com.mik.common.documents.excel.mapper.ExcelExportCellMapper;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.util.ExcelCellStyleUtils;
import com.mik.common.documents.excel.util.ExcelCellUtils;
import com.mik.common.documents.util.DocumentReflectionUtils;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class NumericExcelCellMapper implements ExcelExportCellMapper<Number> {

    @Override
    public void writeToCell(Cell cell, Number value, ExcelColumnDefinition column) {
        /*
        if (Objects.isNull(cell) || Objects.isNull(column) || !DocumentReflectionUtils.isNumberClass(value.getClass())) return;
        Workbook workbook = cell.getSheet().getWorkbook();
        boolean isDecimal = column.isDecimal();
        if (DocumentReflectionUtils.isIntegralNumberClass(value.getClass())) {
            cell.setCellValue(value.longValue());
        } else {
            var res = isDecimal ? value.doubleValue() : value.longValue();
            cell.setCellValue(res);
        }
        CellStyle style = cell.getCellStyle();
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(style);
        ExcelCellStyleUtils.setDecimalStyle(newStyle,cell,isDecimal);
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

        cell.setCellStyle(newStyle);

         */
    }

    @Override
    public void writeToCell(Cell cell, Number value, ExcelColumnDefinition column, CellStyle baseStyle) {
        /*
        if (Objects.isNull(cell) || Objects.isNull(column) || !DocumentReflectionUtils.isNumberClass(value.getClass())) return;
        Workbook workbook = cell.getSheet().getWorkbook();
        boolean isDecimal = column.isDecimal();
        if (DocumentReflectionUtils.isIntegralNumberClass(value.getClass())) {
            cell.setCellValue(value.longValue());
        } else {
            var res = isDecimal ? value.doubleValue() : value.longValue();
            cell.setCellValue(res);
        }
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(baseStyle);
        ExcelCellStyleUtils.setDecimalStyle(newStyle,cell,isDecimal);
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
        cell.setCellStyle(newStyle);

         */
    }

    @Override
    public void writeToCell(Cell cell, Number value) {
        //if (ExcelCellUtils.isCellAndValueIsNull(cell, value)) return;
        //cell.setCellValue(value.toString());
    }

    @Override
    public Number readFromCell(Cell cell) {
        return cell.getNumericCellValue();
    }

    @Override
    public Class<Number> getTypeCellMapper() {
        return Number.class;
    }
}
