package com.mik.common.documents.excel.model.context;

import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelAnnotationContext {

    @Builder.Default
    private List<ExcelColumnDefinition> columns = new ArrayList<>();

    private Annotation annotation;

    private Class<?> clazz;

    private List<?> data;

    private Field field;

    private String parentPath;

}
