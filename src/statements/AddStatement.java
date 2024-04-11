package statements;

import variables.Variable;

import java.util.Map;

public class AddStatement implements ExecutableStatement<Integer> {
    private ExecutableStatement<Integer> operand1, operand2;

    public AddStatement(ExecutableStatement<Integer> operand1, ExecutableStatement<Integer> operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Integer run(Map<String, Variable> namespace) throws Exception {
        return operand1.run(namespace) + operand2.run(namespace);
    }
}
