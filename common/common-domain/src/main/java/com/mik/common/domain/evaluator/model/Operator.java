package com.mik.common.domain.evaluator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Getter
@AllArgsConstructor
public enum Operator {

    OPEN_BRACKET(
            new EvaluatorToken<BigDecimal>(
                    "(",
                    PriorityOperator.OPEN_BRACKET,
                    null,
                    null
            ),
            "("
    ),

    CLOSE_BRACKET(
            new EvaluatorToken<BigDecimal>(
                    ")",
                    PriorityOperator.CLOSE_BRACKET,
                    null,
                    null
            ),
            ")"
    ),

    PLUS(
            new EvaluatorToken<BigDecimal>(
                    "+",
                    PriorityOperator.LOW_PRIORITY_OPERATOR,
                    BigDecimal::new,
                    //(value1, value2) -> value1.add(value2).setScale(0, RoundingMode.CEILING)
                    BigDecimal::add
            ),
            "+"
    ),

    MINUS(
            new EvaluatorToken<BigDecimal>(
                    "-",
                    PriorityOperator.LOW_PRIORITY_OPERATOR,
                    BigDecimal::new,
                    //(value1, value2) -> value1.subtract(value2).setScale(0, RoundingMode.CEILING)
                    BigDecimal::subtract
            ),
            "-"
    ),

    MULTIPLY(
            new EvaluatorToken<BigDecimal>(
                    "*",
                    PriorityOperator.HIGH_PRIORITY_OPERATOR,
                    BigDecimal::new,
                    //(value1, value2) -> value1.multiply(value2).setScale(0, RoundingMode.CEILING)
                    BigDecimal::multiply
            ),
            "*"
    ),

    DIVIDE(
            new EvaluatorToken<BigDecimal>(
                    "/",
                    PriorityOperator.HIGH_PRIORITY_OPERATOR,
                    BigDecimal::new,
                    //(value1, value2) ->
                            //value1.divide(value2).setScale(0, RoundingMode.CEILING)
                    BigDecimal::divide
            ),
            "/"
    ),

    POW(
            new EvaluatorToken<BigDecimal>(
                    "ВОЗВЕДСТЕПЬ",
                    PriorityOperator.FUNCTION_WITH_TWO_ARG,
                    BigDecimal::new,
                    (BigDecimal value1, BigDecimal value2) -> {
                        try {
                            return value1.pow(value2.intValue());
                        } catch (RuntimeException e) {
                            return BigDecimal.ZERO;
                        }
                    }

            ),
            "ВОЗВЕДСТЕПЬ"
    ),

    ROUND_UP(
            new EvaluatorToken<BigDecimal>(
                    "ОКРУГЛВВЕРХ",
                    PriorityOperator.FUNCTION_WITH_ONE_ARG,
                    BigDecimal::new,
                    (BigDecimal value1, BigDecimal value2) -> {
                        try {
                            return value1.setScale(0, RoundingMode.CEILING);
                        } catch (RuntimeException ex) {
                            return BigDecimal.ZERO;
                        }
                    }
            ),
            "ОКРУГЛВВЕРХ"
    ),

    ROUND_DOWN(
            new EvaluatorToken<BigDecimal>(
                    "ОКРУГЛВНИЗ",
                    PriorityOperator.FUNCTION_WITH_ONE_ARG,
                    BigDecimal::new,
                    (BigDecimal value1, BigDecimal value2) -> {
                        try {
                            return value1.setScale(0, RoundingMode.FLOOR);
                        } catch (RuntimeException ex) {
                            return BigDecimal.ZERO;
                        }
                    }
            ),
    "ОКРУГЛВНИЗ"
    ),

    VALUE(
            new EvaluatorToken<>(
                    null,
                    PriorityOperator.VALUE,
                    BigDecimal::new,
                    null),
            null
    );

    private EvaluatorToken token;

    private String operator;

}
