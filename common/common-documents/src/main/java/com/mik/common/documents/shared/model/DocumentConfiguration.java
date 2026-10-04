package com.mik.common.documents.shared.model;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@SuperBuilder
public class DocumentConfiguration {

    protected String fileName;

    protected String template;

    protected Map<String, Object> properties;

    protected Map<Class<?>, List<?>> data;

    /**
     * Имена sheet-s из workbook-а
     */
    @Builder.Default
    protected List<String> workbookNames = new ArrayList<>();

}
