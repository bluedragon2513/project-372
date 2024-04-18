package statements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control_structure.ReturnValue;
import variables.FunctionVar;
import variables.Variable;
import control_structure.Body;

public class FuncCallStatement implements ExecutableStatement {
	String functionName;
	List<ExecutableStatement> arguments;
	
	public FuncCallStatement(String funcName, List<ExecutableStatement> args) {
		this.functionName = funcName;
		this.arguments = args;
	}

	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		FunctionVar f = (FunctionVar) namespace.get(functionName);
		HashMap<String, Variable> localNamespace = new HashMap<>(namespace);
		
		Object retVal = f.arguments(this.arguments).run(localNamespace);
		if (retVal instanceof ReturnValue) {
			return ((ReturnValue) retVal).getValue();
		}
		return retVal;
	}
}
