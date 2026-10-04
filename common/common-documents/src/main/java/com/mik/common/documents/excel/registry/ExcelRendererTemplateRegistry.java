package com.mik.common.documents.excel.registry;

import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.render.ExcelRender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelRendererTemplateRegistry {

    private final Map<ExcelSectionRender, ExcelRender> renders;

    public ExcelRender get(ExcelSectionRender type) {
        return renders.get(type);
    }





}
