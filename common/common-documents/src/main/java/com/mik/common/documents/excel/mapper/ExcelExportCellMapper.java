package com.mik.common.documents.excel.mapper;

import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

public interface ExcelExportCellMapper<T> {

    void writeToCell(Cell cell, T value, ExcelColumnDefinition column);

    void writeToCell(Cell cell, T value, ExcelColumnDefinition column, CellStyle baseStyle);

    void writeToCell(Cell cell, T value);

    T readFromCell(Cell cell);

    Class<T> getTypeCellMapper();

}
