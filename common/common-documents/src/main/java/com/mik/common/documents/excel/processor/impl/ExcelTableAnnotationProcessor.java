package com.mik.common.documents.excel.processor.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.annotation.table.ExcelMarkerTable;
import com.mik.common.documents.excel.annotation.table.ExcelMarkerTableColumnGroupRule;
import com.mik.common.documents.excel.annotation.table.ExcelMarkerTableGroupTotal;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.model.total.ExcelGrandTotalDefinition;
import com.mik.common.documents.excel.model.total.ExcelGroupTotalDefinition;
import com.mik.common.documents.excel.model.total.ExcelRuleTotalDefinition;
import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import com.mik.common.documents.util.DocumentReflectionDataExtractorUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.*;

@Component
@AllArgsConstructor
public class ExcelTableAnnotationProcessor implements ExcelAnnotationProcessor<ExcelTableDefinition> {

    @Override
    public ExcelTableDefinition process(ExcelAnnotationContext context) {
        /*
        ExcelMarkerTable table = (ExcelMarkerTable) context.getAnnotation();
        List<JsonNode> data = DocumentReflectionDataExtractorUtils.convertToListJsonNode(context.getData());
        return ExcelTableDefinition.builder()
                                   .table(table)
                                   .data(data)
                                   .grandTotals(buildGrandTotalDefinition(table.grandTotals()))
                                   .groupTotals(buildGroupTotalDefinition(table.groupTotals()))
                                   .totalColspan(table.totalColspan())
                                   .clazz(context.getClazz())
                                   .build();

         */
        return null;
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ExcelMarkerTable.class;
    }

    /*
    private List<ExcelGrandTotalDefinition> buildGrandTotalDefinition(ExcelMarkerTableGroupTotal[] groupTotals) {
        List<ExcelGrandTotalDefinition> grandTotals = new ArrayList<>();
        for (ExcelMarkerTableGroupTotal groupTotal : groupTotals) {
            Set<String> groupKeys = new HashSet<>(Arrays.asList(groupTotal.field()));
            List<ExcelRuleTotalDefinition> rules = buildRuleTotalDefinition(groupTotal.rules());
            grandTotals.add(ExcelGrandTotalDefinition.builder()
                                                     .title(groupTotal.title())
                                                     .grandTotalKeys(groupKeys)
                                                     .rules(rules)
                                                     .totalColspan(groupTotal.totalColspan())
                                                     .build());
        }
        return grandTotals;
    }

    private List<ExcelGroupTotalDefinition> buildGroupTotalDefinition(ExcelMarkerTableGroupTotal[] groupTotals) {
        List<ExcelGroupTotalDefinition> groupTotalDefinitions = new ArrayList<>();
        for (ExcelMarkerTableGroupTotal groupTotal : groupTotals) {
            Set<String> groupKeys = new HashSet<>(Arrays.asList(groupTotal.field()));
            List<ExcelRuleTotalDefinition> rules = buildRuleTotalDefinition(groupTotal.rules());
            groupTotalDefinitions.add(ExcelGroupTotalDefinition.builder()
                                                               .groupTotalKeys(groupKeys)
                                                               .title(groupTotal.title())
                                                               .rules(rules)
                                                               .build());
        }
        return groupTotalDefinitions;
    }

    private List<ExcelRuleTotalDefinition> buildRuleTotalDefinition(ExcelMarkerTableColumnGroupRule[] rules) {
        List<ExcelRuleTotalDefinition> definitionsRules = new ArrayList<>();
        for (ExcelMarkerTableColumnGroupRule rule : rules) {
            definitionsRules.add(ExcelRuleTotalDefinition.builder()
                                                         .field(rule.field())
                                                         .typeTotal(rule.total())
                                                         .build());
        }
        return definitionsRules;
    }

     */

}
