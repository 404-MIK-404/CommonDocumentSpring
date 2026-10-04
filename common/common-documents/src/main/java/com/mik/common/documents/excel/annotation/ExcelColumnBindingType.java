package com.mik.common.documents.excel.annotation;

/**
 * Тип колонки
 * <p>
 * {@link ExcelColumnBindingType#FIELD} {@link ExcelColumnBindingType#MAP}
 *
 * **/
public enum ExcelColumnBindingType {

    /** Примитивный/Wrapper тип или же класс.**/
    FIELD,

    @Deprecated
    /** Привязка к полю-list формата List<Object> **/
    LIST,

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
