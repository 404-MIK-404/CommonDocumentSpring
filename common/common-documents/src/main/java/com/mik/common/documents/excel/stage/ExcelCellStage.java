package com.mik.common.documents.excel.stage;

import com.mik.common.documents.excel.model.context.ExcelExportContext;
import com.mik.common.documents.excel.model.context.ExcelRenderContext;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.model.table.ExcelTableDefinition;
import com.mik.common.documents.excel.registry.ExcelRendererTemplateRegistry;
import com.mik.common.documents.excel.render.ExcelRender;
import com.mik.common.documents.excel.util.ExcelCellUtils;
import com.mik.common.documents.export.ExcelTemplateSyntax;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class ExcelCellStage {

    private final Pattern pattern = Pattern.compile(ExcelTemplateSyntax.TOKEN_PATTERN);

    private final ExcelRendererTemplateRegistry renderRegistry;

    public void processCell(Cell cell, ExcelExportContext properties) {
        StringBuilder value = valueFromCellToStringBuilder(cell);
        Matcher matcher = pattern.matcher(value.toString());
        while (matcher.find()) {
            String command = matcher.group();
            ExcelSectionRender render = ExcelSectionRender.findRenderTokenByCommand(command.replace("}}", "").replace("{{", ""));
            dispatch(new CellStage(cell,command,render,properties));
        }
    }

    private void dispatch(CellStage cellStage) {
        ExcelSectionRender render = cellStage.render;
        if (render == null) return;
        processCellProperty(cellStage);
        processCellTable(cellStage);
    }

    private void processCellTable(CellStage cellStage) {
        ExcelSectionRender renderType = cellStage.render;
        if (renderType.isPropertySectionRender()) return;
        Map<String, ExcelTableDefinition> tables = cellStage.properties.getTables();
        String[] lines = convertToArgs(cellStage.command);
        ExcelTableDefinition table = tables.get(lines[2]);
        ExcelRender render = renderRegistry.get(renderType);
        ExcelRenderContext context = ExcelRenderContext.builder()
                                                       .table(table)
                                                       .command(cellStage.command)
                                                       .build();
        render.render(context,cellStage.cell);
    }

    private void processCellProperty(CellStage cellStage) {
        ExcelSectionRender renderType = cellStage.render;
        if (!renderType.isPropertySectionRender()) return;
        String[] lines = convertToArgs(cellStage.command);
        ExcelRender render = renderRegistry.get(renderType);
        ExcelRenderContext context = ExcelRenderContext.builder()
                                                       .properties(cellStage.properties.getProperties())
                                                       .command(cellStage.command)
                                                       .key(lines[1])
                                                       .build();
        render.render(context,cellStage.cell);
    }


    private String[] convertToArgs(final String command) {
        return command.trim().substring(2, command.length() - 2).trim().split("::");
    }

    private StringBuilder valueFromCellToStringBuilder(Cell cell) {
        String value = ExcelCellUtils.cellValueToString(cell);
        if (value.isEmpty()) return new StringBuilder();
        return new StringBuilder(value);
    }

    private record CellStage(
            Cell cell,
            String command,
            ExcelSectionRender render,
            ExcelExportContext properties
    ) {}

}
