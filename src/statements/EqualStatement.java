package statements;

import java.util.Map;

import variables.Variable;

public class EqualStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public EqualStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Boolean run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);
        if (left instanceof Double && right instanceof Double) {
            return ((double) left) == ((double) right);
        }
        throw new IllegalArgumentException("Equal operation is only valid for doubles");
    }
}
