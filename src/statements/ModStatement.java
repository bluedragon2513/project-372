package statements;

import variables.Variable;
import java.util.Map;

public class ModStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public ModStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);
        if (left instanceof Integer && right instanceof Integer) {
            return ((Integer) left) % ((Integer) right);
        }
        throw new IllegalArgumentException("Modulo operation is only valid for integers");
    }
}
