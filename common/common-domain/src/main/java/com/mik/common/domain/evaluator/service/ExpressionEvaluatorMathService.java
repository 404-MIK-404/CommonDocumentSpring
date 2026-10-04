package com.mik.common.domain.evaluator.service;

import com.mik.common.domain.evaluator.interpreter.PolishNotationInterpreter;
import com.mik.common.domain.evaluator.interpreter.ShuntingYardInterpreter;
import com.mik.common.domain.evaluator.model.EvaluatorToken;
import com.mik.common.domain.evaluator.model.ExpressionEvaluatorResult;
import com.mik.common.domain.evaluator.model.Operator;
import com.mik.common.domain.evaluator.model.PriorityOperator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ExpressionEvaluatorMathService {

    private final ShuntingYardInterpreter<BigDecimal> shuntingYardInterpreter;

    private final PolishNotationInterpreter<BigDecimal> polishNotationInterpreter;

    public ExpressionEvaluatorResult<BigDecimal> evaluate(String expression) {
        try {
            if (expression == null || expression.isEmpty()) return new ExpressionEvaluatorResult<BigDecimal>(BigDecimal.ZERO, "Формула пустая !");
            Pattern pattern = Pattern.compile(
                    "ОКРУГЛВВЕРХ|ОКРУГЛВНИЗ|ВОЗВЕДСТЕПЬ|\\d+(?:[.,]\\d+)?|[()+\\-*/]"
            );
            Matcher matcher = pattern.matcher(expression);
            List<String> expressions = new ArrayList<>();
            while (matcher.find()) {
                expressions.add(matcher.group());
            }
            List<EvaluatorToken<BigDecimal>> tokens = shuntingYardInterpreter.interpret(transformStrToToken(expressions));
            BigDecimal value = polishNotationInterpreter.interpreter(tokens);
            return new ExpressionEvaluatorResult<BigDecimal>(Objects.nonNull(value) ? value : BigDecimal.ZERO, null);
        } catch (RuntimeException e) {
            return new ExpressionEvaluatorResult<BigDecimal>(BigDecimal.ZERO, e.getMessage());
        }
    }

    public ExpressionEvaluatorResult<Boolean> evaluateToBoolean(String expression) {
        try {
            if (expression == null || expression.isEmpty()) return new ExpressionEvaluatorResult<Boolean>(false, "Формула пустая !");
            List<String> expressions = List.of(expression.split(" "));
            List<EvaluatorToken<BigDecimal>> tokens = shuntingYardInterpreter.interpret(transformStrToToken(expressions));
            BigDecimal value = polishNotationInterpreter.interpreter(tokens);
            return new ExpressionEvaluatorResult<Boolean>(Objects.nonNull(value), "");
        } catch (RuntimeException e) {
            return new ExpressionEvaluatorResult<Boolean>(false, e.getMessage());
        }
    }


    private List<EvaluatorToken<BigDecimal>> transformStrToToken(List<String> expressions) {
        return expressions.stream()
                          .map(this::getToken)
                          .toList();
    }

    private EvaluatorToken<BigDecimal> getToken(String expression) {
        EvaluatorToken<BigDecimal> tokenValue = new EvaluatorToken<BigDecimal>(
                expression,
                PriorityOperator.VALUE,
                BigDecimal::new,
                null );
        for (Operator operator : Operator.values()) {
            String operatorExpression = operator.getOperator();
            if (Objects.nonNull(operatorExpression)
                && operatorExpression.equalsIgnoreCase(expression)) {
                return operator.getToken();
            }
        }
        return tokenValue;
    }


}
