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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Будет в будущем удалён, замените type на Number.class
 */
@Component
@AllArgsConstructor
public class BigDecimalExcelCellMapper implements ExcelExportCellMapper<BigDecimal> {

    @Override
    public void writeToCell(Cell cell, BigDecimal value, ExcelColumnDefinition column) {
        /*
        if (Objects.isNull(cell) || Objects.isNull(column) || !DocumentReflectionUtils.isNumberClass(value.getClass())) return;
        Workbook workbook = cell.getSheet().getWorkbook();
        boolean isDecimal = column.isDecimal();
        if (DocumentReflectionUtils.isIntegralNumberClass(column.getType())) {
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
    public void writeToCell(Cell cell, BigDecimal value, ExcelColumnDefinition column, CellStyle baseStyle) {
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
    public void writeToCell(Cell cell, BigDecimal value) {
        /*
        if (ExcelCellUtils.isCellAndValueIsNull(cell, value)) return;
        cell.setCellValue(value.toString());

         */
    }

    @Override
    public BigDecimal readFromCell(Cell cell) {
        return new BigDecimal(cell.getStringCellValue());
    }

    @Override
    public Class<BigDecimal> getTypeCellMapper() {
        return BigDecimal.class;
    }

}
