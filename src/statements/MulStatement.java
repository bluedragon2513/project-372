package statements;

import variables.Variable;
import java.util.Map;

public class MulStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public MulStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);
        if (left instanceof Number && right instanceof Number) {
        	
        	// Evaluate operation as two doubles
            if (left instanceof Double || right instanceof Double) {
                return ((Number) left).doubleValue() * ((Number) right).doubleValue();
            }
            
            // Otherwise, evaluate as two ints
            return ((Number) left).intValue() * ((Number) right).intValue();
        }
        throw new IllegalArgumentException("Invalid types for multiplication");
    }
}
