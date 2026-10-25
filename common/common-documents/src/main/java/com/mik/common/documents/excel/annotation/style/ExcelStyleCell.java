package com.mik.common.documents.excel.annotation.style;


import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelStyleCell {

    /** Горизонтальное выравнивание. */
    HorizontalAlignment horizontal() default HorizontalAlignment.GENERAL;

    /** Вертикальное выравнивание. */
    VerticalAlignment vertical() default VerticalAlignment.BOTTOM;

    /** Полужирное начертание текста. */
    boolean bold() default false;

    /** Перенос текста. */
    boolean wrap() default false;

    /** Вывод числа в формате 0.00 **/
    boolean isDecimal() default false;

}
