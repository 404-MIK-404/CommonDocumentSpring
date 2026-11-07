package com.mik.common.documents.excel.processor.table;

import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.annotation.table.ExcelTable;
import com.mik.common.documents.excel.factory.DocumentExcelDefinitionFactory;
import com.mik.common.documents.excel.model.context.TableContext;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.processor.column.ExcelAnnotationProcessor;
import com.mik.common.documents.util.DocumentReflectionDataExtractorUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelTableAnnotationProcessor implements ExcelOneCreateAnnotationProcessor<ExcelTableDefinition, TableContext> {

    private final DocumentExcelDefinitionFactory factory;

    @Override
    public ExcelTableDefinition process(TableContext context) {
        ExcelTable annotation = context.annotation();
        List<JsonNode> data = DocumentReflectionDataExtractorUtils.convertToListJsonNode(context.data());
        return factory.onCreateTableDefinition(annotation,context.clazz(), data);
    }

    @Override
    public Class<? extends Annotation> annotation() {
        return ExcelTable.class;
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
