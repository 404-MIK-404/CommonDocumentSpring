package com.mik.common.documents.excel.annotation.column;

import com.mik.common.documents.excel.model.enums.ExcelTypeColumn;
import com.mik.common.documents.excel.model.enums.ExcelTypeTotal;
import com.mik.common.documents.excel.annotation.cell.ExcelCell;

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
public @interface ExcelColumn {

    int index() default -1;

    int span() default 0;

    /** Имя колонки **/
    String name() default "";

    /** Описание самой ячейки **/
    ExcelCell cell();

    /**
     * Тип колонки
     * Допустимые значения определены в {@link ExcelTypeColumn }
     *
     * @see ExcelTypeTotal
     * **/
    ExcelTypeColumn type() default ExcelTypeColumn.NONE;

    /**
     * Итоговое значение.
     * Допустимые значения определены в {@link ExcelTypeTotal}.
     *
     * @see ExcelTypeTotal
     */
    ExcelTypeTotal total() default ExcelTypeTotal.NONE;




}
