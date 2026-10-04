package com.mik.common.documents.excel.render.impl;

import com.mik.common.documents.excel.mapper.impl.StringExcelCellMapper;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelRenderContext;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.render.ExcelRender;
import com.mik.common.documents.excel.util.ExcelCellStyleUtils;
import com.mik.common.documents.excel.util.ExcelColumnDefinitionUtils;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ExcelTableIndexColumnRender implements ExcelRender {

    private final StringExcelCellMapper mapper;

    @Override
    public void render(ExcelRenderContext context, Cell cell) {
        ExcelTableDefinition table = context.getTable();
        Row row = cell.getRow();
        String command = context.getCommand();
        String formula = mapper.readFromCell(cell).replace(command, "").trim();
        cell.setCellFormula(formula);
        CellStyle style = ExcelCellStyleUtils.createCellStyle(cell);
        int indexBegin = cell.getColumnIndex();
        List<ExcelColumnDefinition> columns = ExcelColumnDefinitionUtils.getColumnDefinitions(table.getColumns());
        for (int i = 0; i < columns.size(); i++) {
            Cell cellIndex = CellUtil.getCell(row, indexBegin + i);
            cellIndex.setCellFormula(formula);
            cellIndex.setCellStyle(style);
        }
    }

    @Override
    public ExcelSectionRender getType() {
        return ExcelSectionRender.INDEX;
    }
}
