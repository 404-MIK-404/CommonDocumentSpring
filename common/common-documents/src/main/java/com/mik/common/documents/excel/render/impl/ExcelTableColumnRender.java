package com.mik.common.documents.excel.render.impl;

import com.mik.common.documents.excel.mapper.ExcelExportCellMapper;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelRenderContext;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.registry.ExcelCellMapperRegistry;
import com.mik.common.documents.excel.render.ExcelRender;
import com.mik.common.documents.excel.util.ExcelCellStyleUtils;
import com.mik.common.documents.excel.util.ExcelCellUtils;
import com.mik.common.documents.excel.util.ExcelColumnDefinitionUtils;
import com.mik.common.documents.excel.util.ExcelRowUtils;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class ExcelTableColumnRender implements ExcelRender {

    private final ExcelCellMapperRegistry cellMapperRegistry;

    @Override
    public void render(ExcelRenderContext context, Cell cell) {
        /*
        Set<String> merged = new HashSet<>();
        ExcelTableDefinition table = context.getTable();
        Row row = cell.getRow();
        Sheet sheet = cell.getSheet();
        CellStyle style = ExcelCellStyleUtils.createCellStyle(cell);
        int indexRow = row.getRowNum();
        int indexColumn = cell.getColumnIndex();
        int maxDepth = ExcelColumnDefinitionUtils.depthColumn(table);
        if (maxDepth >= 1) {
            ExcelRowUtils.shiftRowsDown(sheet, indexRow + 1, maxDepth);
        }
        for (ExcelColumnDefinition column : table.getColumns()) {
            NodeColumnCursor cursor = new NodeColumnCursor(column, indexColumn, indexRow, 1, indexRow + maxDepth);
            renderTableColumn(style,cursor,sheet,merged,maxDepth);
            int span = column.getSpan();
            indexColumn += width(column) + span;
        }

         */
    }

    @Override
    public ExcelSectionRender getType() {
        return ExcelSectionRender.COLUMNS;
    }

    /*
    private void renderTableColumn(CellStyle style, NodeColumnCursor cursor, Sheet sheet, Set<String> merged , int maxDepth) {
        ExcelExportCellMapper<String> mapper = cellMapperRegistry.get(String.class);
        ExcelColumnDefinition column = cursor.column();
        int indexMrgVertical = cursor.mrgVertical;
        int indexRow = cursor.rowIndex;
        int indexColumn = cursor.columnIndex;
        int depth = cursor.depth;
        Row r = ExcelRowUtils.createRowByIndex(sheet, indexRow);
        Cell cell = ExcelCellUtils.createCellByRow(r, indexColumn);
        boolean isLeaf = column.getChildren().isEmpty();

        String value = isLeaf ? column.getName() : column.getGroupTitle();
        mapper.writeToCell(cell, value, column, style);

        int rowSpan = maxDepth - depth + 1;
        int width = width(column);

        if (!isLeaf) {
            merge(cell.getCellStyle() ,sheet, indexRow, indexRow, indexColumn, indexColumn + width - 1, merged);
        } else {
            if (rowSpan >= 1) {
                merge(cell.getCellStyle(),sheet, indexRow, indexRow + rowSpan, indexColumn, indexColumn, merged);
            }
        }
        //cell.setCellStyle(style);

        int nextColumnIndex = indexColumn;
        for (ExcelColumnDefinition child : column.getChildren()) {
            NodeColumnCursor childCursor = new NodeColumnCursor(child, nextColumnIndex, indexRow + 1, depth + 1, indexMrgVertical );
            renderTableColumn(style,childCursor,sheet,merged,maxDepth);
            nextColumnIndex += width(child);
        }

    }

    private void merge(CellStyle style, Sheet sheet, int r1, int r2, int c1, int c2, Set<String> merged) {
        if (r1 == r2 && c1 == c2) {
            return;
        }
        String key = r1 + ":" + r2 + ":" + c1 + ":" + c2;
        if (!merged.add(key)) return;

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

    private int width(ExcelColumnDefinition node) {
        if (node.getChildren().isEmpty()) return 1;
        int sum = 0;
        for (ExcelColumnDefinition child : node.getChildren()) {
            sum += width(child);
        }
        return sum;
    }

    private record NodeColumnCursor(
            ExcelColumnDefinition column,
            int columnIndex,
            int rowIndex,
            int depth,
            int mrgVertical
    ) {}

     */

}
