import java.util.regex.Pattern;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import variables.Variable;

public class LogicalExprParser {

    private static final Pattern lessThanPattern = Pattern.compile("^lessThan\\((.+),(.+)\\)$");
    private static final Pattern greaterThanPattern = Pattern.compile("^greaterThan\\((.+),(.+)\\)$");
    private static final Pattern equalToPattern = Pattern.compile("^equalTo\\((.+),(.+)\\)$");
    private static final Pattern andPattern = Pattern.compile("^and\\((.+),(.+)\\)$");
    private static final Pattern orPattern = Pattern.compile("^or\\((.+),(.+)\\)$");
    private static final Pattern notPattern = Pattern.compile("^not\\((.+)\\)$");

    public static Object parseAndEvaluate(String expression, Map<String, Variable> namespace) throws Exception {
        Matcher lessThanMatcher = lessThanPattern.matcher(expression);
        Matcher greaterThanMatcher = greaterThanPattern.matcher(expression);
        Matcher equalToMatcher = equalToPattern.matcher(expression);
        Matcher andMatcher = andPattern.matcher(expression);
        Matcher orMatcher = orPattern.matcher(expression);
        Matcher notMatcher = notPattern.matcher(expression);

        if(lessThanMatcher.find()) {
            return evaluateLessThan(lessThanMatcher, namespace);
        } else if(greaterThanMatcher.find()) {
            return evaluateGreaterThan(greaterThanMatcher, namespace);
        } else if(equalToMatcher.find()) {
            return evaluateEqualTo(equalToMatcher, namespace);
        } else if(andMatcher.find()) {
            return evaluateAnd(andMatcher, namespace);
        } else if(orMatcher.find()) {
            return evaluateOr(orMatcher, namespace);
        } else if(notMatcher.find()) {
            return evaluateNot(notMatcher, namespace);
        }

        throw new IllegalArgumentException("Unsupported expression: " + expression);
    }

    private static Boolean evaluateLessThan(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        int leftOperand = Integer.parseInt(matcher.group(1));
        int rightOperand = Integer.parseInt(matcher.group(2));
        return leftOperand < rightOperand;
    }

    private static Boolean evaluateGreaterThan(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        int leftOperand = Integer.parseInt(matcher.group(1));
        int rightOperand = Integer.parseInt(matcher.group(2));
        return leftOperand > rightOperand;
    }

    private static Boolean evaluateEqualTo(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        int leftOperand = Integer.parseInt(matcher.group(1));
        int rightOperand = Integer.parseInt(matcher.group(2));
        return leftOperand == rightOperand;
    }

    private static Boolean evaluateAnd(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        boolean leftOperand = Boolean.parseBoolean(matcher.group(1));
        boolean rightOperand = Boolean.parseBoolean(matcher.group(2));
        return leftOperand && rightOperand;
    }

    private static Boolean evaluateOr(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        boolean leftOperand = Boolean.parseBoolean(matcher.group(1));
        boolean rightOperand = Boolean.parseBoolean(matcher.group(2));
        return leftOperand || rightOperand;
    }

    private static Boolean evaluateNot(Matcher matcher, Map<String, Variable> namespace) throws Exception {
        boolean operand = Boolean.parseBoolean(matcher.group(1));
        return !operand;
    }
}
