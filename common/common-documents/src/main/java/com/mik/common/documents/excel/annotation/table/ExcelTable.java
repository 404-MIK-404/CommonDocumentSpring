package com.mik.common.documents.excel.annotation.table;


import com.mik.common.documents.excel.model.enums.ExcelMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Аннотация для описание таблицы Excel
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTable {

    ExcelMode mode() default ExcelMode.BOTH;

    /** ID таблицы **/
    String id() default "";

    /** Количество колонок, занимаемых ячейкой. */
    int totalspan() default 0;

    /** Аннотация, которая описывает как группировать данные для таблицы Excel  **/
    ExcelGroupTableTotal[] groupTotals() default {};

    /**
     * Аннотация, которая описывает какие итоговые значения ставить для таблицы Excel
     * @TODO Переработать этот параметр !
     * **/
    ExcelGroupTableTotal[] grandTotals() default {};



}
