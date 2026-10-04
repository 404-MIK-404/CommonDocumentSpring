package com.mik.common.documents.excel.model.configuration;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ExcelDynamicTemplate {

    /* Динамические страницы */
    private List<ExcelDynamicPage> pages;

    /* Путь динамического шаблона */
    private String pathTemplate;

}
