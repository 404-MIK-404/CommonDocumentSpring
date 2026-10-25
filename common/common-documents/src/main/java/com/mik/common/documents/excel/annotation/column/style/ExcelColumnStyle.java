package com.mik.common.documents.excel.annotation.column.style;

import com.mik.common.documents.excel.annotation.style.ExcelColorCell;
import com.mik.common.documents.excel.annotation.style.ExcelStyleCell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumnStyle {

    /** Ширина ячейки. */
    float width() default -1.0f;

    ExcelStyleCell style() default @ExcelStyleCell();

    ExcelColorCell color() default @ExcelColorCell();

}
