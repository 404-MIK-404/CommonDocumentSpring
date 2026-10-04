package com.mik.common.documents.excel.annotation.style;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для установки стилей ячеек Excel.
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMarkerStyleCell {

    /** Горизонтальное выравнивание. */
    HorizontalAlignment horizontal() default HorizontalAlignment.GENERAL;

    /** Вертикальное выравнивание. */
    VerticalAlignment vertical() default VerticalAlignment.BOTTOM;

    /** Полужирное начертание текста. */
    boolean bold() default false;

    /** Перенос текста. */
    boolean wrap() default false;

    /** Ширина ячейки. */
    float width() default -1.0f;

    /** Высота строки. */
    float height() default -1.0f;

    /** Вывод числа в формате 0.00 **/
    boolean isDecimal() default false;

}
