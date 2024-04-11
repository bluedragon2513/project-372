import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import statements.*;
import variables.Variable;

public class ArithmeticExprParser {

    private static final Pattern addPattern = Pattern.compile("^add\\((.+),(.+)\\)$");
    private static final Pattern subPattern = Pattern.compile("^subtract\\((.+),(.+)\\)$");
    private static final Pattern mulPattern = Pattern.compile("^multiply\\((.+),(.+)\\)$");
    private static final Pattern divPattern = Pattern.compile("^divide\\((.+),(.+)\\)$");
    private static final Pattern modPattern = Pattern.compile("^mod\\((.+),(.+)\\)$");

    public static Integer parseAndEvaluate(String expression, Map<String, Variable> namespace) throws Exception {
        Matcher addMatcher = addPattern.matcher(expression);
        Matcher subMatcher = subPattern.matcher(expression);
        Matcher mulMatcher = mulPattern.matcher(expression);
        Matcher divMatcher = divPattern.matcher(expression);
        Matcher modMatcher = modPattern.matcher(expression);

        if (addMatcher.find()) {
            return evaluateAddition(addMatcher, namespace);
        } else if (subMatcher.find()) {
            return evaluateSubtraction(subMatcher, namespace);
        } else if (mulMatcher.find()) {
            return evaluateMultiplication(mulMatcher, namespace);
        } else if (divMatcher.find()) {
            return evaluateDivision(divMatcher, namespace);
        } else if (modMatcher.find()) {
            return evaluateModulo(modMatcher, namespace);
        }

        throw new IllegalArgumentException("Unsupported expression: " + expression);
    }

    private static Integer evaluateAddition(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        Integer leftValue = parseOperand(matcher.group(1), namespace);
        Integer rightValue = parseOperand(matcher.group(2), namespace);

        ExecutableStatement<Integer> addStatement = new AddStatement(new IntegerStatement(leftValue), new IntegerStatement(rightValue));
        return addStatement.run(namespace);
    }

    private static Integer evaluateSubtraction(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        Integer leftValue = parseOperand(matcher.group(1), namespace);
        Integer rightValue = parseOperand(matcher.group(2), namespace);

        ExecutableStatement<Integer> subStatement = new SubStatement(new IntegerStatement(leftValue), new IntegerStatement(rightValue));
        return subStatement.run(namespace);
    }

    private static Integer evaluateMultiplication(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        Integer leftValue = parseOperand(matcher.group(1), namespace);
        Integer rightValue = parseOperand(matcher.group(2), namespace);

        ExecutableStatement<Integer> mulStatement = new MulStatement(new IntegerStatement(leftValue), new IntegerStatement(rightValue));
        return mulStatement.run(namespace);
    }

    private static Integer evaluateDivision(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        Integer leftValue = parseOperand(matcher.group(1), namespace);
        Integer rightValue = parseOperand(matcher.group(2), namespace);

        ExecutableStatement<Integer> divStatement = new DivStatement(new IntegerStatement(leftValue), new IntegerStatement(rightValue));
        return divStatement.run(namespace);
    }

    private static Integer evaluateModulo(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        Integer leftValue = parseOperand(matcher.group(1), namespace);
        Integer rightValue = parseOperand(matcher.group(2), namespace);

        ExecutableStatement<Integer> modStatement = new ModStatement(new IntegerStatement(leftValue), new IntegerStatement(rightValue));
        return modStatement.run(namespace);
    }

    private static Integer parseOperand(String operand, Map<String, Variable> namespace) throws Exception {
        operand = operand.trim();
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            // could be an expression
        }

        // Attempt to parse it as an expression
        Object result = parseAndEvaluate(operand, namespace);
        if (result instanceof Integer) {
            return (Integer) result;
        }

        throw new IllegalArgumentException("Operand is not a valid integer or integer expression: " + operand);
    }
}