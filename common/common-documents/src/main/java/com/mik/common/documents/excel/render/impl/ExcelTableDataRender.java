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
public class ExcelTableDataRender implements ExcelRender {

    private final ExcelCellMapperRegistry cellMapperRegistry;

    /*
    * RenderDataContext renderContext = new RenderDataContext(
                table,
                cell.getColumnIndex(),
                cell.getRow().getRowNum(),
                cell.getSheet(),
                cell,
                cell.getRow()
        );
    *
    * */

    @Override
    public void render(ExcelRenderContext context, Cell cell) {
        /*
        Row row = cell.getRow();
        Sheet sheet = cell.getSheet();
        RenderContext renderContext = new RenderContext(
                context.getTable(),
                cell.getColumnIndex(),
                row.getRowNum(),
                sheet,
                cell,
                row
        );

        ExcelTableDefinition table = context.getTable();
        List<JsonNode> data = table.getData();
        DynamicGroupDataTable dynamicGroupDataTable = buildTreeTable(data, table.getGroupTotals(), 0);
        int indexData = dynamicGroupDataTable == null ? data.size() : index(dynamicGroupDataTable,data);
        if (data.size() > 1) {
            ExcelRowUtils.shiftRowsDown(sheet, row.getRowNum() + 1, indexData - 1);
        }
        if (dynamicGroupDataTable != null) {
            renderTreeTable(table,dynamicGroupDataTable,sheet,row,cell);
        } else {
            renderTable(renderContext, table.getData());
        }

         */
    }

    @Override
    public ExcelSectionRender getType() {
        return ExcelSectionRender.DATA;
    }

    /*
    private void renderTreeTable(ExcelTableDefinition table, DynamicGroupDataTable group, Sheet sheet, Row row, Cell cell) {
        int indexRow = row.getRowNum();
        int indexColumn = cell.getColumnIndex();
        CellStyle cellStyle = ExcelCellStyleUtils.createCellStyle(cell);
        List<ExcelColumnDefinition> columns = ExcelColumnDefinitionUtils.getColumnDefinitions(table.getColumns());
        ArrayDeque<DynamicGroupDataTable> nodeGroupTotals = new ArrayDeque<>(group.children);
        while (!nodeGroupTotals.isEmpty()) {
            //Заполняю итоговые значения
            DynamicGroupDataTable node = nodeGroupTotals.pop();
            int currentIndexColumn = indexColumn;
            Row rowSrc = ExcelRowUtils.createRowByIndex(sheet,indexRow);


            renderTreeTableTitle(node,currentIndexColumn,rowSrc);
            List<ExcelRuleTotalDefinition> rules = node.getRules();
            ExcelCellUtils.merge(cellStyle,sheet,indexRow,indexRow,currentIndexColumn,currentIndexColumn + table.getTotalColspan(),null);


            //Заполняю итоговые значения на основе итогов
            for (ExcelColumnDefinition column : columns) {
                renderTreeTableRules(rules, column, rowSrc, currentIndexColumn, node.data,cellStyle);
                currentIndexColumn++;
                currentIndexColumn += column.getSpan();
            }

            indexRow++;
            //Заполняю данные если дочерний элемент пустой
            if (node.getChildren().isEmpty()) {
                for (JsonNode value: node.getData()) {
                    currentIndexColumn = indexColumn;
                    rowSrc = ExcelRowUtils.createRowByIndex(sheet,indexRow);
                    renderTreeTableValues(columns,rowSrc,currentIndexColumn,value, cellStyle);
                    indexRow++;
                }
            } else {
                nodeGroupTotals.addAll(node.getChildren());
            }
        }
    }

    private void renderTreeTableTitle(DynamicGroupDataTable node,int currentIndexColumn,Row rowSrc) {
        ExcelExportCellMapper<String> titleGroupTotalMapper = cellMapperRegistry.get(String.class);
        Cell cellGroupTitle = ExcelCellUtils.createCellByRow(rowSrc, currentIndexColumn);
        String nodeGroupTitle = node.getDataKey();
        titleGroupTotalMapper.writeToCell(cellGroupTitle,"ИТОГИ ПО " + nodeGroupTitle, null);
    }

    private void renderTreeTableRules(List<ExcelRuleTotalDefinition> rules, ExcelColumnDefinition column, Row rowSrc, int currentIndexColumn, List<JsonNode> data,CellStyle cellStyle) {
        ExcelRuleTotalDefinition rule = rules.stream().filter(rl-> rl.getField().equalsIgnoreCase(column.getPathField())).findFirst().orElse(null);
        Class<?> typeValue = column.getType();
        if (rule != null) {
            Cell valueTotal = ExcelCellUtils.createCellByRow(rowSrc,currentIndexColumn);
            ExcelTotalBindingType typeTotal = rule.getTypeTotal();
            if (typeTotal != ExcelTotalBindingType.NONE) {
                Class<?> clazzResultTotal = typeTotal.getClazzType();
                Object value = data.stream().map(
                                           vl -> DocumentReflectionDataExtractorUtils.<BigDecimal>invokeData(vl, typeValue, column.getPathField()))
                                   .collect(typeTotal.getCollector());
                value = clazzResultTotal.cast(value);
                ExcelExportCellMapper mapper = cellMapperRegistry.get(clazzResultTotal);
                mapper.writeToCell(valueTotal,value);
                ExcelCellStyleUtils.addCellStyle(valueTotal,cellStyle);
            }
        }
    }

    private void renderTreeTableValues(List<ExcelColumnDefinition> columns, Row rowSrc, int currentIndexColumn, JsonNode value,CellStyle cellStyle) {
        for (ExcelColumnDefinition column : columns) {
            Class<?> typeValue = column.getType();
            Cell cellValue = ExcelCellUtils.createCellByRow(rowSrc,currentIndexColumn);
            ExcelExportCellMapper mapper = cellMapperRegistry.get(typeValue);
            Object res = DocumentReflectionDataExtractorUtils.invokeData(value,typeValue,column.getPathField());
            mapper.writeToCell(cellValue,res);
            ExcelCellStyleUtils.addCellStyle(cellValue,cellStyle);
            currentIndexColumn++;
            currentIndexColumn += column.getSpan();
        }
    }


    private void renderTable(RenderContext context, List<JsonNode> data) {
        int indexRow = context.indexRow();
        int indexColumn = context.indexColumn();
        CellStyle cellStyle = ExcelCellStyleUtils.createCellStyle(context.cell());
        List<ExcelColumnDefinition> columns = ExcelColumnDefinitionUtils.getColumnDefinitions(context.table().getColumns());
        Sheet sheet = context.sheet();
        Row row = context.row();
        for (int i = 0; i < data.size(); i++) {
            Object value = data.get(i);
            int nextIndexRow = (indexRow + i);
            int currentIndexColumn = indexColumn;
            Row rowTargetStyle = ExcelRowUtils.createRowByIndex(sheet,nextIndexRow);
            Row rowSrc = ExcelRowUtils.createRowByIndex(sheet,(indexRow + i));
            if (indexRow != nextIndexRow) {
                ExcelRowUtils.copyRow(row, rowTargetStyle, nextIndexRow);
            }
            renderTableRow(cellStyle,value,rowSrc,columns,currentIndexColumn);
        }
    }

    private void renderTableRow(CellStyle cellStyle,Object data, Row row, List<ExcelColumnDefinition> columns, int indexColumn) {
        for (ExcelColumnDefinition column : columns) {
            Class<?> typeValue = column.getType();
            if (typeValue == BigDecimal.class) typeValue = Number.class;
            int span = column.getSpan();
            Cell cell = ExcelCellUtils.createCellByRow(row,indexColumn);
            ExcelExportCellMapper mapper = cellMapperRegistry.get(typeValue);
            Object res = DocumentReflectionDataExtractorUtils.invokeData(data, typeValue, column.getPathField());
            ExcelCellStyleUtils.addCellStyle(cell,cellStyle);
            mapper.writeToCell(cell, res, column);
            indexColumn++;
            indexColumn += span;
        }
    }


    private DynamicGroupDataTable buildTreeTable(List<JsonNode> data, List<ExcelGroupTotalDefinition> defs, int level) {
        if (defs.size() <= level) return null;
        ExcelGroupTotalDefinition def = defs.get(level);
        Set<String> keys = def.getGroupTotalKeys();
        Map<String, List<JsonNode>> grouped =
                DocumentReflectionDataExtractorUtils.toMapGroupByKeys(data, keys);
        DynamicGroupDataTable node = new DynamicGroupDataTable();
        node.setData(data);
        node.setFieldGroupKeys(keys);
        node.setRules(def.getRules());
        List<DynamicGroupDataTable> children = new ArrayList<>();
        for (Map.Entry<String, List<JsonNode>> entry : grouped.entrySet()) {
            HashMap<String,String> filters = new HashMap<>();
            DynamicGroupDataTable child = new DynamicGroupDataTable();
            putFilters(entry.getKey(),keys,filters);
            child.setFilters(filters);
            child.setDataKey(entry.getKey());
            child.setFieldGroupKeys(keys);
            child.setRules(def.getRules());
            child.setData(entry.getValue());
            child.setTotalColspan(def.getTotalColspan());
            if (level + 1 < defs.size()) {
                DynamicGroupDataTable builtChild =
                        buildTreeTable(entry.getValue(), defs, level + 1);
                if (builtChild != null) {
                    child.setChildren(builtChild.getChildren());
                }
            }
            children.add(child);
        }

        node.setChildren(children);
        return node.getChildren().isEmpty() ? null : node;
    }

    private int index(DynamicGroupDataTable dynamicGroupDataTable, List<JsonNode> data) {
        Deque<DynamicGroupDataTable> stack = new ArrayDeque<>();
        if (dynamicGroupDataTable == null) return 0;
        stack.addAll(dynamicGroupDataTable.getChildren());
        int index = data.size();
        while (!stack.isEmpty()) {
            DynamicGroupDataTable current = stack.pop();
            index++;
            if (current.getChildren() != null) {
                stack.addAll(current.getChildren());
            }
        }
        return index;
    }

    private void putFilters(String dataKey,Set<String> keys,Map<String,String> filters) {
        if (dataKey != null && keys != null) {
            String[] values = dataKey.split("\\|"); // тот же разделитель, что в groupBy
            int index = 0;
            for (String key : keys) {
                String value = index < values.length ? values[index] : null;
                if (value != null) {
                    filters.put(key, value);
                }
                index++;
            }
        }
    }


    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class DynamicGroupDataTable {

        private String dataKey;

        @Builder.Default
        private Map<String,String> filters = new HashMap<>();

        @Builder.Default
        private Set<String> fieldGroupKeys = new HashSet<>();

        @Builder.Default
        List<JsonNode> data = new ArrayList<>();

        @Builder.Default
        private List<ExcelRuleTotalDefinition> rules = new ArrayList<>();

        @Builder.Default
        private List<DynamicGroupDataTable> children = new ArrayList<>();

        @Builder.Default
        private int totalColspan = 0;

    }

     */

}
