package com.mik.common.domain.evaluator.interpreter;

import com.mik.common.domain.evaluator.model.EvaluatorToken;
import com.mik.common.domain.evaluator.model.PriorityOperator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.List;

@Component
@AllArgsConstructor
public class PolishNotationInterpreter<T> {


    public T interpreter(List<EvaluatorToken<T>> tokens) {
        ArrayDeque<T> stack = new ArrayDeque<>();
        for (EvaluatorToken<T> token : tokens) {
            PriorityOperator operator = token.getPriorityOperator();
            try {
                switch (operator) {
                    case LOW_PRIORITY_OPERATOR, HIGH_PRIORITY_OPERATOR,
                         FUNCTION_WITH_TWO_ARG,VERY_HIGH_PRIORITY_OPERATOR -> {
                        T right = stack.pop();
                        T left = stack.pop();
                        stack.push(token.eval(left, right));
                    }
                    case FUNCTION_WITH_ONE_ARG -> {
                        stack.push(token.eval(stack.pop(), null));
                    }
                    default -> stack.push(token.parse(token.getValue()));
                }
            } catch (RuntimeException e) {
                stack.push(token.parse("0"));
            }
        }
        return stack.pop();
    }


}
