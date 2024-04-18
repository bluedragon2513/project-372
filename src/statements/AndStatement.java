package statements;

import java.util.Map;
import control_structure.exceptions.TypeException;
import variables.Variable;

public class AndStatement implements ExecutableStatement {
    private ExecutableStatement operand1, operand2;

    public AndStatement(ExecutableStatement operand1, ExecutableStatement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object leftResult = operand1.run(namespace);
        Object rightResult = operand2.run(namespace);

        if (!(leftResult instanceof Boolean) || !(rightResult instanceof Boolean)) {
            throw new TypeException("AND operands must be Boolean");
        }

        Boolean left = (Boolean) leftResult;
        Boolean right = (Boolean) rightResult;

        return left && right;
    }
}
