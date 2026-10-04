package com.mik.common.documents.excel.annotation.column;


import com.mik.common.documents.excel.annotation.ExcelColumnBindingType;
import com.mik.common.documents.excel.annotation.ExcelTotalBindingType;
import com.mik.common.documents.excel.annotation.cell.ExcelMarkerCell;
import com.mik.common.documents.excel.annotation.style.ExcelMarkerTableStyleColumn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для описания колонки таблицы Excel.
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerTableColumn {

    /** Имя колонки **/
    String name() default "";

    /** Описание самой ячейки **/
    ExcelMarkerCell cell();

    /** Стиль для самой колонки **/
    ExcelMarkerTableStyleColumn columnStyle() default @ExcelMarkerTableStyleColumn();

    /** Стиль данных в рамках этой колонки **/
    ExcelMarkerTableStyleColumn dataStyle() default @ExcelMarkerTableStyleColumn();

    /** Вывод числа в формате 0.00 **/
    boolean decimal() default false;

    /**
     * Тип колонки
     * Допустимые значения определены в {@link ExcelColumnBindingType }
     *
     * @see ExcelTotalBindingType
     * **/
    ExcelColumnBindingType type() default ExcelColumnBindingType.NONE;

    /**
     * Итоговое значение.
     * Допустимые значения определены в {@link ExcelTotalBindingType}.
     *
     * @see ExcelTotalBindingType
     */
    ExcelTotalBindingType total() default ExcelTotalBindingType.NONE;

    int span() default 0;

}
