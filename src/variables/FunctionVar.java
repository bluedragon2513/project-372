package variables;

import java.util.ArrayList;
import java.util.List;

import control_structure.Body;
import statements.ExecutableStatement;

public class FunctionVar extends Variable {
	List<String> parameters;
	Body statements;

	public FunctionVar(String name, List<String> params, Body statements) {
		super(name, statements);
		this.parameters = params;
		this.statements = statements;
	}
	
	public ExecutableStatement arguments(List<ExecutableStatement> arguments) throws Exception {
		if (arguments.size() != parameters.size()) {
			throw new Exception("Argument list and parameter list do not match.");
		}
		
		return (namespace) -> {
			for (int i = 0; i < parameters.size(); i++) {
				String p = parameters.get(i);
				namespace.put(p, new Variable(p, arguments.get(i).run(namespace)));
			}
			return this.statements.run(namespace);
		};
	}
}
