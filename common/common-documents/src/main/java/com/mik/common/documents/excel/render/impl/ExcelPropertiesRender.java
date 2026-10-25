package com.mik.common.documents.excel.render.impl;

import com.mik.common.documents.excel.mapper.impl.StringExcelCellMapper;
import com.mik.common.documents.excel.model.context.ExcelRenderContext;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.render.ExcelRender;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ExcelPropertiesRender implements ExcelRender {

    private final StringExcelCellMapper mapper;

    @Override
    public void render(ExcelRenderContext context, Cell cell) {
        /*
        Map<String, Object> properties = context.getProperties();
        String key = context.getKey();
        String cellValue = mapper.readFromCell(cell);
        String command = context.getCommand();
        String value = String.valueOf(properties.get(key));
        value = cellValue.replace(command,value);
        mapper.writeToCell(cell,value);

         */
    }

    @Override
    public ExcelSectionRender getType() {
        return ExcelSectionRender.PROPERTIES;
    }
}
