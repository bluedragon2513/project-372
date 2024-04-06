import java.util.Map;

import variables.Variable;

public class CallStatement implements ExecutableStatement {
	ExecutableStatement statement;
	
	public CallStatement(String unparsed) {
		
	}

	@Override
	public void run(Map<String, Variable> namespace) {
		statement.run(namespace);
	}
}
