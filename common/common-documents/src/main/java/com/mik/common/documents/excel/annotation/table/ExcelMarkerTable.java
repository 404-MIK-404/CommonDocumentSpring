package com.mik.common.documents.excel.annotation.table;

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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerTable {

    /** ID таблицы **/
    String id() default "";

    /** Количество колонок, занимаемых ячейкой. */
    int totalColspan() default 0;


    /** Аннотация, которая описывает как группировать данные для таблицы Excel  **/
    ExcelMarkerTableGroupTotal[] groupTotals() default {};

    /**
     * Аннотация, которая описывает какие итоговые значения ставить для таблицы Excel
     * @TODO Переработать этот параметр !
     * **/
    ExcelMarkerTableGroupTotal[] grandTotals() default {};

}
