package statements;

import java.util.Map;

import variables.Variable;

public class CallStatement<T> implements ExecutableStatement<T> {
    private ExecutableStatement<T> statement;

    public CallStatement(String unparsed) {
        
    }
    
    

    @Override
    public T run(Map<String, Variable> namespace) throws Exception {
        return statement.run(namespace);
    }
}
