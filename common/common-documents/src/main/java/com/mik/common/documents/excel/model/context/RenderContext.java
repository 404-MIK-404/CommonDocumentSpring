package com.mik.common.documents.excel.model.context;

import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public record RenderContext(
        ExcelTableDefinition table,
        int indexColumn,
        int indexRow,
        Sheet sheet,
        Cell cell,
        Row row
) {
}
