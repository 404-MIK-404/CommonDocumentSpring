package com.mik.common.documents.excel.model.enums;

/**
 * Тип колонки
 * <p>
 * {@link ExcelTypeColumn#FIELD} {@link ExcelTypeColumn#MAP}
 *
 * **/
public enum ExcelTypeColumn {

    /** Примитивный/Wrapper тип или же класс.**/
    FIELD,

    /**
     * Поле ключ -> значение.
     * Привязка к полю типа {@code Map<String, Wrapper>}.
     *
     * @see java.util.Map
     */
    MAP,

    /** По умолчанию **/
    NONE

}
