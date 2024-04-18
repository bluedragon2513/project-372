package statements;

import java.util.Map;

import variables.Variable;

public class LessThanStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public LessThanStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @SuppressWarnings("unchecked") // Comparables cast is a requirement
    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object left = operand1.run(namespace);
        Object right = operand2.run(namespace);
        if (left instanceof Comparable && right instanceof Comparable) {
            return ((Comparable) left).compareTo((Comparable) right) < 0;
        }
        return null;
    }
}
