package com.mik.common.documents.excel.annotation.column.style;

import com.mik.common.documents.excel.annotation.style.ExcelColorCell;
import com.mik.common.documents.excel.annotation.style.ExcelStyleCell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDataStyle {


    ExcelStyleCell style() default @ExcelStyleCell();

    ExcelColorCell color() default @ExcelColorCell();

}
