package statements;

import variables.Variable;

import java.util.Map;

public class IntegerStatement implements ExecutableStatement<Integer> {
    private int value;

    public IntegerStatement(int value) {
        this.value = value;
    }

    @Override
    public Integer run(Map<String, Variable> namespace) {
        // This particular statement simply returns an integer value
        return this.value;
    }
}
