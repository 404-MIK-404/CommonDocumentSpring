package com.mik.common.documents.export.enums;

public enum ExcelExportRowType {

    DATA,
    GROUP_TOTAL,
    TOTAL;

    public static ExcelExportRowType from(Object value) {
        if (value == null) return DATA;
        return ExcelExportRowType.valueOf(value.toString().toUpperCase());
    }
}
