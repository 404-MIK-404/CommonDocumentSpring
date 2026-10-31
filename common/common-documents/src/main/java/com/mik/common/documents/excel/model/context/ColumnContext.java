package com.mik.common.documents.excel.model.context;


import com.fasterxml.jackson.databind.JsonNode;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import lombok.Builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

@Builder
public record ColumnContext(
        Annotation annotation,
        ExcelColumnDefinition column,
        List<JsonNode> data,
        Field field
) implements ProcessorContext {
}
