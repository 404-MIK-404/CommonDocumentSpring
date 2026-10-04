package com.mik.common.documents.excel.binding.impl;

import com.mik.common.documents.excel.annotation.ExcelColumnBindingType;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class ExcelColumnListBinding implements ExcelAnnotationBinding {

    @Override
    public List<ExcelColumnDefinition> apply(ExcelAnnotationContext context) {
        return List.of();
    }

    @Override
    public ExcelColumnBindingType getBindingType() {
        return ExcelColumnBindingType.LIST;
    }
}
