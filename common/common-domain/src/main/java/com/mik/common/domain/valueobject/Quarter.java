package com.mik.common.domain.valueobject;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

@AllArgsConstructor
public enum Quarter {

    Q1(Month.JANUARY, Month.MARCH),
    Q2(Month.APRIL, Month.JUNE),
    Q3(Month.JULY, Month.SEPTEMBER),
    Q4(Month.OCTOBER, Month.DECEMBER);

    private final Month startMonth;

    private final Month endMonth;

    public LocalDate start(Integer year) {
        return LocalDate.of(year, startMonth, 1);
    }

    public LocalDate end(Integer year) {
        return LocalDate.of(year, endMonth,
                            YearMonth.of(year, endMonth).lengthOfMonth());
    }

    public static Quarter from(LocalDate date) {
        return switch ((date.getMonthValue() - 1) / 3) {
            case 0 -> Quarter.Q1;
            case 1 -> Quarter.Q2;
            case 2 -> Quarter.Q3;
            default -> Quarter.Q4;
        };
    }

}
