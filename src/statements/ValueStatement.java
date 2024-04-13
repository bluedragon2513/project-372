package statements;

import variables.Variable;
import java.util.Map;

/*
 * Generic version of IntegerStatement. This was implemented to ensure our language can properly use type inference
 */
public class ValueStatement implements ExecutableStatement {
    private Object value;

    public ValueStatement(Object value) {
        this.value = value;
    }

    @Override
    public Object run(Map<String, Variable> namespace) {
        return value;
    }
}
