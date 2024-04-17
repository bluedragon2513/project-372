package statements;

import variables.Variable;
import java.util.Map;

public class StringStatement implements ExecutableStatement {
    private String value;

    public StringStatement(String value) {
        this.value = value;
    }

    @Override
    public String run(Map<String, Variable> namespace) {
        // Simply returns the string value
        return this.value;
    }
}