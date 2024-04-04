/**
 * This class is used to parse arithmetic and boolean expressions
 * The structure is based on Parser.java provided by Professor Lotz
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArithmeticAndBooleanExprParser {
    private static Pattern addPattern = Pattern.compile("^add\\((.+),(.+)\\)$");
    private static Pattern subPattern = Pattern.compile("^subtract\\((.+),(.+)\\)$");
    private static Pattern mulPattern = Pattern.compile("^multiply\\((.+),(.+)\\)$");
    private static Pattern divPattern = Pattern.compile("^divide\\((.+),(.+)\\)$");
    private static Pattern modPattern = Pattern.compile("^mod\\((.+),(.+)\\)$");

    private static Pattern lessThanPattern = Pattern.compile("^lessThan\\((.+),(.+)\\)$");
    private static Pattern greaterThanPattern = Pattern.compile("^greaterThan\\((.+),(.+)\\)$");
    private static Pattern equalPattern = Pattern.compile("^equalTo\\((.+),(.+)\\)$");
    private static Pattern notEqualPattern = Pattern.compile("^notEqualTo\\((.+),(.+)\\)$");
    private static Pattern greaterOrEqualPattern = Pattern.compile("^greaterOrEqual\\((.+),(.+)\\)$");
    private static Pattern lessOrEqualPattern = Pattern.compile("^lessOrEqual\\((.+),(.+)\\)$");
    private static Pattern andPattern = Pattern.compile("^and\\((.+),(.+)\\)$");
    private static Pattern orPattern = Pattern.compile("^or\\((.+),(.+)\\)$");
    private static Pattern notPattern = Pattern.compile("^not\\((.+)\\)$");

    /**
     * This function attempts to parse an arithmetic expression, then prints the translated expression.
     * @param cmd The command to be parsed
     * @return True if the command matches the arithmetic expression pattern, false otherwise
     */
    private static boolean arithmeticExpr(String cmd) {
        Matcher addMatcher = addPattern.matcher(cmd);
        Matcher subMatcher = subPattern.matcher(cmd);
        Matcher mulMatcher = mulPattern.matcher(cmd);
        Matcher divMatcher = divPattern.matcher(cmd);
        Matcher modMatcher = modPattern.matcher(cmd);

        boolean match = false;

        if (addMatcher.find()) {
            String left = addMatcher.group(1);
            String right = addMatcher.group(2);
            String translated = left + " + " + right;
            System.out.println(translated);
            match = true;
        } else if (subMatcher.find()) {
            String left = subMatcher.group(1);
            String right = subMatcher.group(2);
            String translated = left + " - " + right;
            System.out.println(translated);
            match = true;
        } else if (mulMatcher.find()) {
            String left = mulMatcher.group(1);
            String right = mulMatcher.group(2);
            String translated = left + " * " + right;
            System.out.println(translated);
            match = true;
        } else if (divMatcher.find()) {
            String left = divMatcher.group(1);
            String right = divMatcher.group(2);
            String translated = left + " / " + right;
            System.out.println(translated);
            match = true;
        } else if (modMatcher.find()) {
            String left = modMatcher.group(1);
            String right = modMatcher.group(2);
            String translated = left + " % " + right;
            System.out.println(translated);
            match = true;
        }

        return match;
    }

    /**
     * This function attempts to parse a boolean expression, then prints the translated expression.
     * @param cmd The command to be parsed
     * @return True if the command matches the boolean expression pattern, false otherwise
     */
    public static boolean booleanExpr(String cmd) {
        Matcher lessThanMatcher = lessThanPattern.matcher(cmd);
        Matcher greaterThanMatcher = greaterThanPattern.matcher(cmd);
        Matcher equalMatcher = equalPattern.matcher(cmd);
        Matcher notEqualMatcher = notEqualPattern.matcher(cmd);
        Matcher greaterOrEqualMatcher = greaterOrEqualPattern.matcher(cmd);
        Matcher lessOrEqualMatcher = lessOrEqualPattern.matcher(cmd);
        Matcher andMatcher = andPattern.matcher(cmd);
        Matcher orMatcher = orPattern.matcher(cmd);
        Matcher notMatcher = notPattern.matcher(cmd);

        boolean match = false;

        if (lessThanMatcher.find()) {
            String left = lessThanMatcher.group(1);
            String right = lessThanMatcher.group(2);
            String translated = left + " < " + right;
            System.out.println(translated);
            match = true;
        } else if (greaterThanMatcher.find()) {
            String left = greaterThanMatcher.group(1);
            String right = greaterThanMatcher.group(2);
            String translated = left + " > " + right;
            System.out.println(translated);
            match = true;
        } else if (equalMatcher.find()) {
            String left = equalMatcher.group(1);
            String right = equalMatcher.group(2);
            String translated = left + " == " + right;
            System.out.println(translated);
            match = true;
        } else if (notEqualMatcher.find()) {
            String left = notEqualMatcher.group(1);
            String right = notEqualMatcher.group(2);
            String translated = left + " != " + right;
            System.out.println(translated);
            match = true;
        } else if (greaterOrEqualMatcher.find()) {
            String left = greaterOrEqualMatcher.group(1);
            String right = greaterOrEqualMatcher.group(2);
            String translated = left + " >= " + right;
            System.out.println(translated);
            match = true;
        } else if (lessOrEqualMatcher.find()) {
            String left = lessOrEqualMatcher.group(1);
            String right = lessOrEqualMatcher.group(2);
            String translated = left + " <= " + right;
            System.out.println(translated);
            match = true;
        } else if (andMatcher.find()) {
            String left = andMatcher.group(1);
            String right = andMatcher.group(2);
            String translated = left + " && " + right;
            System.out.println(translated);
            match = true;
        } else if (orMatcher.find()) {
            String left = orMatcher.group(1);
            String right = orMatcher.group(2);
            String translated = left + " || " + right;
            System.out.println(translated);
            match = true;
        } else if (notMatcher.find()) {
            String operand = notMatcher.group(1);
            String translated = "!" + operand;
            System.out.println(translated);
            match = true;
        }

        return match;
    }

    /**
     * This function attempts to evaluate an arithmetic expression, returning the result.
     * @param cmd The command to be evaluated
     * @return The result of the arithmetic expression
     */
    private static int evaluateArithmeticExpr(String cmd) {
        Matcher addMatcher = addPattern.matcher(cmd);
        Matcher subMatcher = subPattern.matcher(cmd);
        Matcher mulMatcher = mulPattern.matcher(cmd);
        Matcher divMatcher = divPattern.matcher(cmd);
        Matcher modMatcher = modPattern.matcher(cmd);

        if (addMatcher.find()) {
            int left = Integer.parseInt(addMatcher.group(1));
            int right = Integer.parseInt(addMatcher.group(2));
            return left + right;
        } else if (subMatcher.find()) {
            int left = Integer.parseInt(subMatcher.group(1));
            int right = Integer.parseInt(subMatcher.group(2));
            return left - right;
        } else if (mulMatcher.find()) {
            int left = Integer.parseInt(mulMatcher.group(1));
            int right = Integer.parseInt(mulMatcher.group(2));
            return left * right;
        } else if (divMatcher.find()) {
            int left = Integer.parseInt(divMatcher.group(1));
            int right = Integer.parseInt(divMatcher.group(2));
            return left / right;
        } else if (modMatcher.find()) {
            int left = Integer.parseInt(modMatcher.group(1));
            int right = Integer.parseInt(modMatcher.group(2));
            return left % right;
        }

        // No match found for the given expression
        return -999;
    }

    /**
     * This function attempts to evaluate a boolean expression, returning the result.
     * @param cmd The command to be evaluated
     * @return The result of the boolean expression
     */
    private static boolean evaluateBooleanExpr(String cmd) {
        Matcher lessThanMatcher = lessThanPattern.matcher(cmd);
        Matcher greaterThanMatcher = greaterThanPattern.matcher(cmd);
        Matcher equalMatcher = equalPattern.matcher(cmd);
        Matcher notEqualMatcher = notEqualPattern.matcher(cmd);
        Matcher greaterOrEqualMatcher = greaterOrEqualPattern.matcher(cmd);
        Matcher lessOrEqualMatcher = lessOrEqualPattern.matcher(cmd);
        Matcher andMatcher = andPattern.matcher(cmd);
        Matcher orMatcher = orPattern.matcher(cmd);
        Matcher notMatcher = notPattern.matcher(cmd);

        if (lessThanMatcher.find()) {
            int left = Integer.parseInt(lessThanMatcher.group(1));
            int right = Integer.parseInt(lessThanMatcher.group(2));
            return left < right;
        } else if (greaterThanMatcher.find()) {
            int left = Integer.parseInt(greaterThanMatcher.group(1));
            int right = Integer.parseInt(greaterThanMatcher.group(2));
            return left > right;
        } else if (equalMatcher.find()) {
            int left = Integer.parseInt(equalMatcher.group(1));
            int right = Integer.parseInt(equalMatcher.group(2));
            return left == right;
        } else if (notEqualMatcher.find()) {
            int left = Integer.parseInt(notEqualMatcher.group(1));
            int right = Integer.parseInt(notEqualMatcher.group(2));
            return left != right;
        } else if (greaterOrEqualMatcher.find()) {
            int left = Integer.parseInt(greaterOrEqualMatcher.group(1));
            int right = Integer.parseInt(greaterOrEqualMatcher.group(2));
            return left >= right;
        } else if (lessOrEqualMatcher.find()) {
            int left = Integer.parseInt(lessOrEqualMatcher.group(1));
            int right = Integer.parseInt(lessOrEqualMatcher.group(2));
            return left <= right;
        } else if (andMatcher.find()) {
            boolean left = Boolean.parseBoolean(andMatcher.group(1));
            boolean right = Boolean.parseBoolean(andMatcher.group(2));
            return left && right;
        } else if (orMatcher.find()) {
            boolean left = Boolean.parseBoolean(orMatcher.group(1));
            boolean right = Boolean.parseBoolean(orMatcher.group(2));
            return left || right;
        } else if (notMatcher.find()) {
            boolean operand = Boolean.parseBoolean(notMatcher.group(1));
            return !operand;
        }

        // No match found for the given expression
        return false;
    }


    public static void main(String[] args) {
        System.out.println("***Testing parsing of arithmetic expressions...***\n");
        testArithmeticExpr();
        System.out.println("***Testing parsing of boolean expressions...***\n");
        testBooleanExpr();
        System.out.println("***Testing evaluation of arithmetic expressions...***\n");
        testEvaluateArithmeticExpr();
        System.out.println("***Testing evaluation of boolean expressions...***\n");
        testEvaluateBooleanExpr();
    }

    /**
     * This function strictly tests whether the arithmetic expressions are being parsed correctly.
     * It does not evaluate the expressions.
     */
    private static void testArithmeticExpr() {
        String[] testExpressions = {
                "add(1,2)",             // Expected output: 1 + 2
                "subtract(5,3)",        // Expected output: 5 - 3
                "multiply(4,2)",        // Expected output: 4 * 2
                "divide(10,5)",         // Expected output: 10 / 5
                "mod(7,3)",             // Expected output: 7 % 3
                "add(1)",               // Invalid input, test to see handling
                "randomOperation(5,2)", // Unsupported operation, test to see handling
                "add(3,var)",            // Expected output: 3 + (2 * 3)
                "add(add(3,4),6)"
        };

        for (String expr : testExpressions) {
            System.out.println("Testing expression: " + expr);
            boolean result = arithmeticExpr(expr);
            System.out.println("Match: " + result + "\n");
        }
    }

    /**
     * This function strictly tests whether the boolean expressions are being parsed correctly.
     * It does not evaluate the expressions.
     */
    private static void testBooleanExpr() {
        String[] testExpressions = {
                "lessThan(1,2)",        // Expected output: 1 < 2
                "greaterThan(5,3)",     // Expected output: 5 > 3
                "equalTo(4,2)",         // Expected output: 4 == 2
                "notEqualTo(10,5)",     // Expected output: 10 != 5
                "greaterOrEqual(7,3)",  // Expected output: 7 >= 3
                "lessOrEqual(1,2)",     // Expected output: 1 <= 2
                "and(true,false)",      // Expected output: true && false
                "or(true,false)",       // Expected output: true || false
                "not(true)"             // Expected output: !true
        };

        for (String expr : testExpressions) {
            System.out.println("Testing expression: " + expr);
            boolean result = booleanExpr(expr);
            System.out.println("Match: " + result + "\n");
        }
    }

    /**
     * This function tests the evaluation of arithmetic expressions
     */
    private static void testEvaluateArithmeticExpr() {
        String[] testExpressions = {
                "add(1,2)",             // Expected output: 3
                "subtract(5,3)",        // Expected output: 2
                "multiply(4,2)",        // Expected output: 8
                "divide(10,5)",         // Expected output: 2
                "mod(7,3)",             // Expected output: 1
                "add(1)",               // Invalid input, test to see handling
                "randomOperation(5,2)", // Unsupported operation, test to see handling
        };

        for (String expr : testExpressions) {
            System.out.println("Testing expression: " + expr);
            int result = evaluateArithmeticExpr(expr);
            System.out.println("Result: " + result + "\n");
        }
    }

    /**
     * This function tests the evaluation of boolean expressions
     */
    private static void testEvaluateBooleanExpr() {
        String[] testExpressions = {
                "lessThan(1,2)",        // Expected output: true
                "greaterThan(5,3)",     // Expected output: true
                "equalTo(4,2)",         // Expected output: false
                "notEqualTo(10,5)",     // Expected output: true
                "greaterOrEqual(7,3)",  // Expected output: true
                "lessOrEqual(1,2)",     // Expected output: true
                "and(true,false)",      // Expected output: false
                "or(true,false)",       // Expected output: true
                "not(true)"             // Expected output: false
        };

        for (String expr : testExpressions) {
            System.out.println("Testing expression: " + expr);
            boolean result = evaluateBooleanExpr(expr);
            System.out.println("Result: " + result + "\n");
        }
    }
}