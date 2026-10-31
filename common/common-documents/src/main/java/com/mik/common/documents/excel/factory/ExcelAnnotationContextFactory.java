package com.mik.common.documents.excel.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.annotation.column.ExcelColumn;
import com.mik.common.documents.excel.annotation.table.ExcelTable;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ColumnContext;
import com.mik.common.documents.excel.model.context.TableContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelAnnotationContextFactory {



    public TableContext onCreateTableContext(ExcelTable annotation, List<?> data) {
        return TableContext.builder()
                .annotation(annotation)
                .data(data)
                .build();
    }


    public ColumnContext onCreateColumnContext(Annotation annotation, ExcelColumnDefinition column, List<JsonNode> data, Field field) {
        return ColumnContext.builder()
                .annotation(annotation)
                .column(column)
                .field(field)
                .data(data)
                .build();
    }




}
