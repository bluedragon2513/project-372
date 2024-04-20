package statements;

import variables.Variable;
import java.util.Map;

public class AssignmentStatement implements ExecutableStatement {

    private String variableName;
    private ExecutableStatement valueExpression;

    public AssignmentStatement(String variableName, ExecutableStatement valueExpression) {
        this.variableName = variableName;
        this.valueExpression = valueExpression;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        // Evaluate the expression to determine the value to assign.
        Object value = valueExpression.run(namespace);

        // Check if the variable already exists in the namespace, or create a new one if it doesn't.
        // If the variable exists but is of a different type, replace it
        Variable variable = namespace.get(variableName);
        if (variable == null || variable.getValue().getClass() != value.getClass()) {
            variable = new Variable(variableName, value);
            namespace.put(variableName, variable);
        } else { // variable.getClass() == value.getClass()
            variable.setValue(value);
        }

        return value;
    }
}