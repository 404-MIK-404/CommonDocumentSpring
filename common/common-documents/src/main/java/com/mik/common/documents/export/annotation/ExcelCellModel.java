package com.mik.common.documents.export.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class ExcelCellModel {

    private Class<?> clazz;

    private String method;

    private String defaultValue;

}
