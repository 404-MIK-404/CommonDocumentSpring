package com.mik.common.documents.excel.annotation.style;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для установки стилей и цвета для ячеек Excel
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerTableStyleColumn {

    /** Стили */
    ExcelMarkerStyleCell style() default @ExcelMarkerStyleCell();

    /** Цвет */
    ExcelMarkerColorStyleCell color() default @ExcelMarkerColorStyleCell();

}
