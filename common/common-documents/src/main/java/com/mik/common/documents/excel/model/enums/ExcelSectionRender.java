package com.mik.common.documents.excel.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExcelSectionRender {

    COLUMNS("table::columns"),
    DATA("table::data"),
    TOTAL("table::total"),
    INDEX("table::index"),
    PROPERTIES("properties");

    private final String token;

    public static ExcelSectionRender findRenderTokenByCommand(String command) {
        for (ExcelSectionRender e : ExcelSectionRender.values()) {
            if (command.startsWith(e.token)) {
                return e;
            }
        }
        return null;
    }

    public boolean isPropertySectionRender() {
        return this == ExcelSectionRender.PROPERTIES;
    }

}
