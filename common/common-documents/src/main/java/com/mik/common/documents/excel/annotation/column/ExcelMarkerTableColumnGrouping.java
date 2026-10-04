package com.mik.common.documents.excel.annotation.column;


import com.mik.common.documents.excel.annotation.style.ExcelMarkerTableStyleColumn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для описания составной колонки таблицы Excel.
 * Общая колонка задаётся на поле типа {@code Object},
 * а поля этого объекта используются в качестве подколонок.
 *
 * @TODO В будущем будет добавлена поддержка стилей.
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerTableColumnGrouping {

    /** имя групповой колонки **/
    String name();

    /** Стиль групповой колонки **/
    ExcelMarkerTableStyleColumn style() default @ExcelMarkerTableStyleColumn();


}
