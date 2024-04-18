package statements;

import variables.Variable;
import control_structure.exceptions.TypeException;
import java.util.Map;

public class DivStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public DivStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);

        // Check for numeric types before attempting division.
        if (left instanceof Number && right instanceof Number) {
            // Handle division by zero and type conversion for floating-point division
            if (right.equals(0) || right.equals(0.0)) {
                throw new ArithmeticException("Division by zero");
            }

            // Use double division if either operand is a double to handle fractional results
            if (left instanceof Double || right instanceof Double) {
                return ((Number) left).doubleValue() / ((Number) right).doubleValue();
            }

            // Integer division for two integer operands
            return ((Number) left).intValue() / ((Number) right).intValue();
        }

        // Throw an exception if the types are not suitable for division
        throw new TypeException("Division not supported between " + left.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }
}