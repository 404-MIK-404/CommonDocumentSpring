package com.mik.common.documents.excel.annotation.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для описания составной колонки таблицы Excel.
 * Общая колонка задаётся ключом типа {@code String},
 * а значение это объект, а поля в рамках объекта используются в качестве подколонок.
 *
 *
 * @see Target
 * @see Retention
 * @see ElementType#FIELD
 * @see RetentionPolicy#RUNTIME
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMapColumn {
}
