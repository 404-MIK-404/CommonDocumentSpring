package com.mik.common.documents.excel.annotation.cell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для рендера колонки таблицы Excel.
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerCell {

    /**
     * Тип колонки таблицы.
     * <p>
     * Допустимые типы: {@link Number}, {@link String},
     * {@link java.time.LocalDate}, {@link Boolean}.
     */
    Class<?> type();


    /** Значение по умолчанию  **/
    String defaultValue() default "";

}
