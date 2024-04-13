package statements;

import variables.Variable;
import java.util.Map;

public class SubStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public SubStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);

        if (left instanceof Number && right instanceof Number) {
            // Convert to double if either operand is a double
            if (left instanceof Double || right instanceof Double) {
                return ((Number) left).doubleValue() - ((Number) right).doubleValue();
            }
            // Otherwise, handle as integers
            return ((Number) left).intValue() - ((Number) right).intValue();
        }

        throw new IllegalArgumentException("Subtraction not supported between " + left.getClass().getSimpleName() + " and " + right.getClass().getSimpleName());
    }
}
