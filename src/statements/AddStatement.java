package statements;

import variables.Variable;
import control_structure.exceptions.TypeException;
import java.util.Map;

public class AddStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public AddStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);

        // Handle integer and double addition
        if (left instanceof Number && right instanceof Number) {
        	// Evaluate statement as two doubles
            if (left instanceof Double || right instanceof Double) {
                return ((Number) left).doubleValue() + ((Number) right).doubleValue();
            }
            
            // Otherwise evaluate as two ints
            return ((Number) left).intValue() + ((Number) right).intValue();
        }
        else // Treat the objects as strings and concatenate them
        {
        	return left.toString() + right.toString();
        }
        
//        throw new TypeException("Invalid types for addition");
    }
}
