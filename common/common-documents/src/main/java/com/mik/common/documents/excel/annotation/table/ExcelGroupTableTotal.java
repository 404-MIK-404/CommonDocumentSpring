package com.mik.common.documents.excel.annotation.table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Аннотация для описание правил группировки/итоговых данных в рамках таблицы Excel
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelGroupTableTotal {


    /** Заголовок итоговой строки. */
    String title() default "ИТОГО ПО %s";

    /**поля для группировки **/
    String[] field();

    /** Правила вывода итоговых значений на основе выбранных полей см. в {@link ExcelMarkerTableColumnGroupRule} **/
    ExcelMarkerTableColumnGroupRule[] rules() default {};

    /** Количество колонок, занимаемых ячейкой. */
    int totalColspan() default 0;


}
