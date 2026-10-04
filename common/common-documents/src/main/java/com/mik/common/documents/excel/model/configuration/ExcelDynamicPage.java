package com.mik.common.documents.excel.model.configuration;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ExcelDynamicPage {

    /**
     * Имена для каждого нового sheet
     */
    private List<String> names;

    /* id Sheet для какого отчёта */
    private String idSheet;

}
