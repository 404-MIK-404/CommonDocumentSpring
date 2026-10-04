package com.mik.common.documents.excel.render;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExcelTemplateSectionType {

    COLUMNS("table::columns"),
    DATA("table::data"),
    TOTAL("table::total"),
    INDEX("table::index"),
    PROPERTIES("properties");

    private String template;

    public static ExcelTemplateSectionType getEnum(final String value) {
        for (ExcelTemplateSectionType e : ExcelTemplateSectionType.values()) {
            if (e.template.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown section: " + value);
    }
}
