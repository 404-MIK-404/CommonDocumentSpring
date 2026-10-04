package com.mik.common.domain.evaluator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PriorityOperator {

    FUNCTION_WITH_TWO_ARG(false, 4),

    FUNCTION_WITH_ONE_ARG(false,4),

    VERY_HIGH_PRIORITY_OPERATOR(false,3),

    HIGH_PRIORITY_OPERATOR(true, 2),

    LOW_PRIORITY_OPERATOR(true, 1),

    OPEN_BRACKET(false, 0),

    CLOSE_BRACKET(false, 0),

    VALUE(false, 0);

    private final boolean leftAssociativity;

    private final int precedence;




}
