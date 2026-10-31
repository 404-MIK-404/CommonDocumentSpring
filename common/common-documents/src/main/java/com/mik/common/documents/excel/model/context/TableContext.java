package com.mik.common.documents.excel.model.context;

import com.mik.common.documents.excel.annotation.table.ExcelTable;
import lombok.Builder;

import java.util.List;

@Builder
public record TableContext(
        ExcelTable annotation,
        List<?> data
) implements ProcessorContext {
}
