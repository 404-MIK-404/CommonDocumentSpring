package com.mik.common.documents.excel.registry;

import com.mik.common.documents.excel.model.enums.ExcelTypeColumn;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelColumnBindingRegistry {

    private final Map<ExcelTypeColumn, ExcelAnnotationBinding> registry;

    @SuppressWarnings("unchecked")
    public ExcelAnnotationBinding get(final ExcelTypeColumn type) {
        return registry.get(type);
    }

}
