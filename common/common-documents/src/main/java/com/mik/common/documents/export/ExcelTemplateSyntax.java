package com.mik.common.documents.export;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExcelTemplateSyntax {

    private final String IDENTIFIER = "[a-zA-Z_][a-zA-Z0-9_]*";

    private final String PREFIX = "\\{\\{";

    private final String SUFFIX = "\\}\\}";


    public static final String TOKEN_PATTERN = PREFIX + "(?:properties::" + IDENTIFIER +
                                         "|table::(?:data|columns|index|total)::" + IDENTIFIER +
                                         ")" + SUFFIX;


}
