package com.mik.common.domain.evaluator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.BiFunction;
import java.util.function.Function;

@Data
@AllArgsConstructor
public class EvaluatorToken<T> {

    private String value;

    private PriorityOperator priorityOperator;

    private Function<String, T> parser;

    private BiFunction<T, T, T> function;

    public T eval(T firstOperand, T secondOperand) {
        return function.apply(firstOperand, secondOperand);
    }

    public T parse(String str) {
        return parser.apply(str);
    }


}
