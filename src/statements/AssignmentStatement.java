import java.util.Map;

import variables.Variable;

public class AssignmentStatement implements ExecutableStatement {
	
	ExecutableStatement statement;
	
	public AssignmentStatement(String variable, String value) {
		CallStatement c = new CallStatement(value);
		statement = (namespace) -> {namespace.get(variable).setValue(c.run(namespace));};
	}
	
	@Override
	public void run(Map<String, Variable> parentNamespace) {
		statement.run(parentNamespace);
	}

}
