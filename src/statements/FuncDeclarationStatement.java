package statements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control_structure.Body;
import variables.Variable;
import variables.FunctionVar;

public class FuncDeclarationStatement implements ExecutableStatement {
	String functionName;
	List<String> parameters;
	Body body;
	
	public FuncDeclarationStatement(String funcName, List<String> params, List<ExecutableStatement> body) {
		this.functionName = funcName;
		this.parameters = params;
		this.body = new Body(body);
	}
	
	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		namespace.put(functionName, new FunctionVar(functionName, parameters, body));
		return this;
	}
}
