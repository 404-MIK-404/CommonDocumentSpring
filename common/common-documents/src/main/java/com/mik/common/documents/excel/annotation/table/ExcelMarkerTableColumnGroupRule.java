package com.mik.common.documents.excel.annotation.table;


import com.mik.common.documents.excel.annotation.ExcelTotalBindingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Аннотация для описание правил итоговых значений при группировки данных в рамках таблицы Excel
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerTableColumnGroupRule {

    /** поле **/
    String field();

    /** Итоговое значение **/
    ExcelTotalBindingType total() default ExcelTotalBindingType.NONE;

}
