package com.mik.common.documents.excel.annotation.style;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для установки цвета ячеек Excel.
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColorCell {

    /**
     * Цвет ячейки в формате HEX
     * <p>
     * Например: #FFFFFF.
     **/
    String color() default "";


}
