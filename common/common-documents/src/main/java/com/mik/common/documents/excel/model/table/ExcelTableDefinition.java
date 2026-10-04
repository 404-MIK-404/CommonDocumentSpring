package com.mik.common.documents.excel.model.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.annotation.table.ExcelMarkerTable;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.total.ExcelGrandTotalDefinition;
import com.mik.common.documents.excel.model.total.ExcelGroupTotalDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelTableDefinition {

    @Builder.Default
    private ExcelMarkerTable table = null;

    private Class<?> clazz;

    @Builder.Default
    private List<JsonNode> data = new ArrayList<>();

    @Builder.Default
    private List<ExcelColumnDefinition> columns = new ArrayList<>();

    @Builder.Default
    private List<ExcelGrandTotalDefinition> grandTotals = new ArrayList<>();

    @Builder.Default
    private List<ExcelGroupTotalDefinition> groupTotals = new ArrayList<>();

    @Builder.Default
    private int totalColspan = 0;

}
