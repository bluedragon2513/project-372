package statements;

import variables.Variable;
import java.util.Map;

public class VariableStatement implements ExecutableStatement {
    private String variableName;

    public VariableStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Variable variable = namespace.get(variableName);
        if (variable == null) {
            throw new RuntimeException("Variable '" + variableName + "' not defined");
        }
        return variable.getValue();
    }
}
