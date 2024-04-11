package statements;

import java.util.Map;

import variables.Variable;

public interface ExecutableStatement<T> {
    T run(Map<String, Variable> namespace) throws Exception;
}