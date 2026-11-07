package com.mik.common.documents.excel.factory;


import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.annotation.column.style.ExcelColumnStyle;
import com.mik.common.documents.excel.annotation.column.style.ExcelDataStyle;
import com.mik.common.documents.excel.annotation.style.ExcelColorCell;
import com.mik.common.documents.excel.annotation.style.ExcelStyleCell;
import com.mik.common.documents.excel.annotation.table.ExcelGroupTableTotal;
import com.mik.common.documents.excel.annotation.table.ExcelMarkerTableColumnGroupRule;
import com.mik.common.documents.excel.annotation.table.ExcelTable;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelColumnStyleDefinition;
import com.mik.common.documents.excel.model.column.style.ExcelDataStyleDefinition;
import com.mik.common.documents.excel.model.style.ExcelCellColorDefinition;
import com.mik.common.documents.excel.model.style.ExcelCellStyleDefinition;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.model.total.ExcelGrandTotalDefinition;
import com.mik.common.documents.excel.model.total.ExcelGroupTotalDefinition;
import com.mik.common.documents.excel.model.total.ExcelRuleTotalDefinition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DocumentExcelDefinitionFactory {


    public ExcelTableDefinition onCreateTableDefinition(ExcelTable annotation,Class<?> clazz, List<JsonNode> data) {
        List<ExcelGroupTotalDefinition> groups = Arrays.stream(annotation.groupTotals()).map(this::onCreateExcelGroupTotalDefinition).toList();
        return ExcelTableDefinition.builder()
                .totalspan(annotation.totalspan())
                .mode(annotation.mode())
                .id(annotation.id())
                .groupTotals(groups)
                .clazz(clazz)
                .data(data)
                .build();
    }


    public ExcelColumnDefinition onCreateColumnDefinition(ExcelColumn annotation,String path, ExcelColumnStyleDefinition columnStyle, ExcelDataStyleDefinition dataStyle) {
        return ExcelColumnDefinition.builder()
                .columnStyle(columnStyle)
                .dataStyle(dataStyle)
                .total(annotation.total())
                .clazz(annotation.cell().type())
                .name(annotation.name())
                .span(annotation.span())
                .path(path)
                .build();
    }


    public ExcelDataStyleDefinition onCreateDataStyle(ExcelDataStyle style) {
        if (Objects.isNull(style)) return null;
        return ExcelDataStyleDefinition.builder()
                .style(onCreateCellStyle(style.style()))
                .color(onCreateColorStyle(style.color()))
                .build();
    }


    public ExcelColumnStyleDefinition onCreateColumnStyle(ExcelColumnStyle style) {
        if (Objects.isNull(style)) return null;
        return ExcelColumnStyleDefinition.builder()
                .width(style.width())
                .style(onCreateCellStyle(style.style()))
                .color(onCreateColorStyle(style.color()))
                .build();
    }



    private ExcelCellStyleDefinition onCreateCellStyle(ExcelStyleCell style) {
        if (Objects.isNull(style)) return null;
        return ExcelCellStyleDefinition.builder()
                .horizontal(style.horizontal())
                .vertical(style.vertical())
                .bold(style.bold())
                .wrap(style.wrap())
                .isDecimal(style.isDecimal())
                .build();
    }


    private ExcelCellColorDefinition onCreateColorStyle(ExcelColorCell color) {
        if (Objects.isNull(color)) return null;
        return ExcelCellColorDefinition.builder()
                .color(color.color())
                .build();
    }


    private ExcelGrandTotalDefinition onCreateExcelGrandTotalDefinition() {
        return ExcelGrandTotalDefinition.builder().build();
    }

    private ExcelGroupTotalDefinition onCreateExcelGroupTotalDefinition(ExcelGroupTableTotal annotation) {
        if (Objects.isNull(annotation)) return null;
        Set<String> group = Arrays.stream(annotation.field()).collect(Collectors.toSet());
        List<ExcelRuleTotalDefinition> rules = Arrays.stream(annotation.rules())
                .map(this::onCreateRuleTotalDefinition).filter(Objects::nonNull).toList();
        return ExcelGroupTotalDefinition.builder()
                .title(annotation.title())
                .groupTotalKeys(group)
                .rules(rules)
                .totalColspan(annotation.totalColspan())
                .build();
    }

    private ExcelRuleTotalDefinition onCreateRuleTotalDefinition(ExcelMarkerTableColumnGroupRule annotation) {
        if (Objects.isNull(annotation)) return null;
        return ExcelRuleTotalDefinition.builder()
                .field(annotation.field())
                .typeTotal(annotation.total())
                .build();
    }


}
