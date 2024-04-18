package statements;

import java.util.Map;
import variables.Variable;

public class NotStatement implements ExecutableStatement {
    private ExecutableStatement operand;

    public NotStatement (ExecutableStatement operand) {
        this.operand = operand;
    }

    @Override
    public Object run(Map<String, Variable> namespace) throws Exception {
        Object result = operand.run(namespace);

        if (!(result instanceof Boolean)) {
            throw new IllegalArgumentException("NOT operand must be Boolean");
        }

        Boolean boolResult = (Boolean) result;

        return !(boolResult);
    }
}
