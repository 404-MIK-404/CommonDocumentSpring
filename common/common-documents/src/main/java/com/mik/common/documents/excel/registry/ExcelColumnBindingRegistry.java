package com.mik.common.documents.excel.registry;

import com.mik.common.documents.excel.annotation.ExcelColumnBindingType;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelColumnBindingRegistry {

    private final Map<ExcelColumnBindingType, ExcelAnnotationBinding> registry;

    @SuppressWarnings("unchecked")
    public ExcelAnnotationBinding get(final ExcelColumnBindingType type) {
        return registry.get(type);
    }

}
