package com.mik.common.documents.excel.render;

import com.mik.common.documents.excel.model.context.ExcelRenderContext;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import org.apache.poi.ss.usermodel.Cell;

public interface ExcelRender {

    void render(ExcelRenderContext context, Cell cell);

    ExcelSectionRender getType();

}
