package com.mik.common.documents.excel.render.impl;

import com.mik.common.documents.excel.model.context.ExcelRenderContext;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.registry.ExcelCellMapperRegistry;
import com.mik.common.documents.excel.render.ExcelRender;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExcelTableTotalRender implements ExcelRender {

    private final ExcelCellMapperRegistry cellMapperRegistry;

    @Override
    public void render(ExcelRenderContext context, Cell cell) {
        /*
        int totalColspan = context.getTable().getTotalColspan();
        Row row = cell.getRow();
        RenderContext renderContext = new RenderContext(
                context.getTable(),
                cell.getColumnIndex() + totalColspan,
                row.getRowNum(),
                cell.getSheet(),
                cell,
                row
        );
        CellStyle cellStyle = ExcelCellStyleUtils.createCellStyle(cell);

        renderTitle(renderContext, cellStyle);
        renderValues(renderContext, cellStyle);

         */
    }

    @Override
    public ExcelSectionRender getType() {
        return ExcelSectionRender.TOTAL;
    }

    /*
    private void renderTitle(RenderContext context, CellStyle cellStyle) {
        Cell cell = context.cell();
        int totalColspan = context.table().getTotalColspan();
        ExcelExportCellMapper<String> titleMapper = cellMapperRegistry.get(String.class);
        titleMapper.writeToCell(cell,"ИТОГИ", null);
        if (totalColspan != 0) {
            int indexRow = context.indexRow();
            ExcelCellUtils.merge(cellStyle,context.sheet(),indexRow,indexRow,cell.getColumnIndex(),context.indexColumn() - 1,null);
        }
    }

    private void renderValues(RenderContext context, CellStyle cellStyle) {
        List<JsonNode> data = context.table().getData();
        List<ExcelColumnDefinition> columns = ExcelColumnDefinitionUtils.getColumnDefinitions(context.table().getColumns());
        int indexColumn = context.indexColumn();
        for (ExcelColumnDefinition column : columns) {
            ExcelTotalBindingType totalBindingType = column.getTotalBindingType();
            if (totalBindingType != ExcelTotalBindingType.NONE) {
                renderValue(context,data,cellStyle,column,indexColumn,totalBindingType);
                indexColumn++;
            }
        }
    }

    private void renderValue(RenderContext context,List<JsonNode> data, CellStyle cellStyle, ExcelColumnDefinition column,int indexColumn, ExcelTotalBindingType totalBindingType) {
        Object value = convertDataToValue(column,data,totalBindingType);
        Cell cellTotal = ExcelCellUtils.createCellByRow(context.row(), indexColumn);
        ExcelExportCellMapper mapper = cellMapperRegistry.get(totalBindingType.getClazzType());
        mapper.writeToCell(cellTotal,value,column);
        ExcelCellStyleUtils.addCellStyle(cellTotal,cellStyle);
    }

    private Object convertDataToValue( ExcelColumnDefinition column,List<JsonNode> data,ExcelTotalBindingType totalBindingType) {
        Class<?> typeValue = column.getType();
        return data.stream().map(vl-> DocumentReflectionDataExtractorUtils.<BigDecimal>invokeData(vl, typeValue, column.getPathField()))
                   .collect(totalBindingType.getCollector());
    }

     */


}
