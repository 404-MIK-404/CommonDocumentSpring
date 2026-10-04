package com.mik.common.documents.excel.model.context;

import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class ExcelExportContext {

    @Builder.Default
    private Map<String, ExcelTableDefinition> tables = new HashMap<>();

    @Builder.Default
    private Map<String, Object> properties = new HashMap<>();

    @Builder.Default
    private Map<Class<?>, List<?>> data = new HashMap<>();


}
