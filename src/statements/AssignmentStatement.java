package statements;

import java.util.Map;
import variables.Variable;
import statements.ExecutableStatement;

public class AssignmentStatement<T> implements ExecutableStatement<Void> {
    private String variableName;
    private ExecutableStatement<T> valueExpression;

    public AssignmentStatement(String variableName, ExecutableStatement<T> valueExpression) {
        this.variableName = variableName;
        this.valueExpression = valueExpression;
    }

    @Override
    public Void run(Map<String, Variable> namespace) throws Exception {
        // Evaluate the expression to determine the value to assign
        T value = valueExpression.run(namespace);

        // Check if the variable exists in the namespace
        if (namespace.containsKey(variableName)) {
            Variable variable = namespace.get(variableName);
            variable.setValue(value);
        } else {
            throw new IllegalArgumentException("Variable '" + variableName + "' not found in the namespace.");
        }

        return null; // Assignment statements don't need to return a value
    }
}