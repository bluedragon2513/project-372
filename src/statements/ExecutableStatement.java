package statements;

import java.util.Map;

import variables.Variable;

public interface ExecutableStatement {
    Object run(Map<String, Variable> namespace) throws Exception;
}