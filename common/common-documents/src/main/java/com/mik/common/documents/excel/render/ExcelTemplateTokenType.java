package com.mik.common.documents.excel.render;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExcelTemplateTokenType {

    PROPERTY("\\{\\{properties::[a-zA-Z_][a-zA-Z0-9_]*\\}\\}"),
    TABLE("\\{\\{table::(data|columns|index|total)::[a-zA-Z_][a-zA-Z0-9_]*\\}\\}");

    private final String token;


}
