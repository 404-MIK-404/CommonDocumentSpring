package com.mik.common.documents.excel.model.enums;

import com.mik.common.documents.excel.aggregation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Итоговое значение.
 * <p>
 * Взято из интерфейса {@link STTotalsRowFunction} из библиотеки Apache POI.
 */
@Getter
@AllArgsConstructor
public enum ExcelTypeTotal {

    /** По умолчанию **/
    NONE(STTotalsRowFunction.NONE,null, null),

    /** Суммируем значения **/
    SUM(STTotalsRowFunction.SUM,
        Collector.of(
                ExcelSumState::new,
                (state, value) -> {
                    state.sum = state.sum.add(value);
                },
                (s1, s2) ->
                {
                    s1.sum = s1.sum.add(s2.sum);
                    return s1;
                }, state -> state.sum)
    ,BigDecimal.class),

    /** Минимальное значение **/
    MIN(STTotalsRowFunction.MIN, Collector.of(
            ExcelMinState::new,
            (state, val) -> {
                if (state.value == null || val.compareTo(state.value) > 0) {
                    state.value = val;
                }
            },
            (s1, s2) -> {
                if (s1.value == null) return s2;
                if (s2.value == null) return s1;
                s1.value = s1.value.min(s2.value);
                return s1;
            },
            state -> state.value == null ? BigDecimal.ZERO : state.value
    ),BigDecimal.class),

    /** Максимальное значение **/
    MAX(STTotalsRowFunction.MAX, Collector.of(
            ExcelMaxState::new,
            (state, val) -> {
                if (state.value == null || val.compareTo(state.value) < 0) {
                    state.value = val;
                }
            },
            (s1, s2) -> {
                if (s1.value == null) return s2;
                if (s2.value == null) return s1;
                s1.value = s1.value.max(s2.value);
                return s1;
            },
            state -> state.value == null ? BigDecimal.ZERO : state.value
    ) ,BigDecimal.class),

    /** Среднее значение **/
    AVG(STTotalsRowFunction.AVERAGE, Collector.of(
            ExcelAvgState::new,
            (state, value) -> {
                state.sum = state.sum.add(value);
                state.count++;
            },
            (s1, s2) -> {
                s1.sum = s1.sum.add(s2.sum);
                s1.count += s2.count;
                return s1;
            },
            state -> state.count == 0
                     ? BigDecimal.ZERO
                     : state.sum.divide(BigDecimal.valueOf(state.count), RoundingMode.HALF_UP)
    ), BigDecimal.class),
    /**Общее количество **/
    COUNT(STTotalsRowFunction.COUNT, Collectors.counting(), Long.class),

    @Deprecated
    COUNT_NUMS(STTotalsRowFunction.COUNT_NUMS, null,Long.class),
    @Deprecated
    STD_DEV(STTotalsRowFunction.STD_DEV,null,null),
    @Deprecated
    VAR(STTotalsRowFunction.VAR,null,null),
    @Deprecated
    CUSTOM(STTotalsRowFunction.CUSTOM,null,null);

    private final STTotalsRowFunction.Enum poi;

    private final Collector<BigDecimal,?,?> collector;

    private final Class<?> clazzType;

}
