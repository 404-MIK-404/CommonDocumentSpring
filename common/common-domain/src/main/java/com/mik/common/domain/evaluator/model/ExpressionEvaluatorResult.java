package com.mik.common.domain.evaluator.model;

import lombok.Builder;

@Builder
public record ExpressionEvaluatorResult<T>(
        T result,
        String message
) {
}
