package com.mik.common.domain.evaluator.interpreter;

import com.mik.common.domain.evaluator.model.EvaluatorToken;
import com.mik.common.domain.evaluator.model.Operator;
import com.mik.common.domain.evaluator.model.PriorityOperator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ShuntingYardInterpreter<T> {

    public List<EvaluatorToken<T>> interpret(List<EvaluatorToken<T>> tokens) {
        ArrayDeque<EvaluatorToken<T>> stack = new ArrayDeque<>();
        List<EvaluatorToken<T>> output = new ArrayList<>();
        for (EvaluatorToken<T> token : tokens) {
            getShuntingYard(token,output,stack);
        }
        while (!stack.isEmpty()) {
            if (Objects.equals(stack.peek().getPriorityOperator(), Operator.OPEN_BRACKET)) throw new IllegalArgumentException("Incorrect expression with extra '('.");
            output.add(stack.pop());
        }
        return output;
    }

    private void getShuntingYard(EvaluatorToken<T> token,List<EvaluatorToken<T>> output,ArrayDeque<EvaluatorToken<T>> stack) {
        PriorityOperator priorityOperator = token.getPriorityOperator();
        switch (priorityOperator) {
            case VALUE -> output.add(token);
            case OPEN_BRACKET -> stack.push(token);
            case CLOSE_BRACKET -> closeBracket(stack, output);
            default -> defaultOperator(stack, output, priorityOperator, token);
        }
    }

    private void closeBracket(ArrayDeque<EvaluatorToken<T>> stack,List<EvaluatorToken<T>> output) {
        while (!stack.isEmpty() && !Objects.equals(stack.peek().getPriorityOperator(), PriorityOperator.OPEN_BRACKET)) {
            output.add(stack.pop());
        }
        stack.pop();
    }

    private void defaultOperator(ArrayDeque<EvaluatorToken<T>> stack, List<EvaluatorToken<T>> output,
                                 PriorityOperator priorityOperator,EvaluatorToken<T> token ) {
        while (shouldPopDefaultOperator(stack,priorityOperator,token)) {
            output.add(stack.pop());
        }
        stack.push(token);
    }

    private boolean shouldPopUntilOpenBracket(ArrayDeque<EvaluatorToken<T>> stack) {
        return !stack.isEmpty() && stack.peek().getPriorityOperator() != PriorityOperator.OPEN_BRACKET;
    }

    private boolean shouldPopDefaultOperator(ArrayDeque<EvaluatorToken<T>> stack,PriorityOperator priorityOperator,EvaluatorToken<T> token) {
        return !stack.isEmpty() &&
               priorityOperator.getPrecedence() <=
               stack.peek().getPriorityOperator().getPrecedence()
               && token.getPriorityOperator().isLeftAssociativity();
    }

}
