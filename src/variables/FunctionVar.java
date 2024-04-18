package variables;

import java.util.ArrayList;
import java.util.List;

import control_structure.Body;
import statements.ExecutableStatement;

/**
 * This class defines a Function variable (essentially the Function's definition)
 * To use this, the program must pass arguments into this variable to
 * 		obtain an ExecutableStatement that runs with the arguments
 * 
 * @author Anthony Nguyen
 */
public class FunctionVar extends Variable {
	List<String> parameters;
	Body statements;

	public FunctionVar(String name, List<String> params, Body statements) {
		super(name, statements);
		this.parameters = params;
		this.statements = statements;
	}

	public List<String> getParameters() {
		return parameters;
	}
	
	public ExecutableStatement arguments(List<ExecutableStatement> arguments) throws Exception {
		if (arguments.size() != parameters.size()) {
			throw new Exception("Argument list and parameter list do not match.");
		}
		
		return (namespace) -> {
			for (int i = 0; i < parameters.size(); i++) {
				String p = parameters.get(i);
				Object arg = arguments.get(i).run(namespace);
				if (arg instanceof FunctionVar) { // this gives us functional programming
					namespace.put(p, (Variable) arg);
				}
				else {
					namespace.put(p, new Variable(p, arg));
				}
			}
			return this.statements.run(namespace);
		};
	}
}
