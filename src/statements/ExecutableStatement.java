package statements;

import java.io.Serializable;
import java.util.Map;

import variables.Variable;

public interface ExecutableStatement extends Serializable {
    Object run(Map<String, Variable> namespace) throws Exception;
}