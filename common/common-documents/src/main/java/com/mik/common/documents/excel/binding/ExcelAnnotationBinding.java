package com.mik.common.documents.excel.binding;


import com.mik.common.documents.excel.annotation.ExcelColumnBindingType;
import com.mik.common.documents.excel.model.column.ExcelColumnDefinition;
import com.mik.common.documents.excel.model.context.ExcelAnnotationContext;

import java.util.List;

public interface ExcelAnnotationBinding {

    List<ExcelColumnDefinition> apply(ExcelAnnotationContext context);

    ExcelColumnBindingType getBindingType();


}
