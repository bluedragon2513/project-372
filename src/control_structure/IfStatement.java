package control_structure;

import java.util.HashMap;
import java.util.Map;

import statements.ExecutableStatement;
import variables.Variable;

public class IfStatement implements ExecutableStatement {
	HashMap<String, Variable> localNamespace;
	ExecutableStatement conditional;
	ExecutableStatement ifTrue;
	ExecutableStatement ifFalse = (namespace) -> { return null; };
	
	public IfStatement(ExecutableStatement conditional, ExecutableStatement ifTrue) {
		this.conditional = conditional;
		this.ifTrue = ifTrue;
	}
	
	public IfStatement(ExecutableStatement conditional, ExecutableStatement ifTrue, ExecutableStatement ifFalse) {
		this(conditional, ifTrue);
		this.ifFalse = ifFalse;
	}
	
	public Object run(Map<String, Variable> parentNamespace) throws Exception {
		Object retVal;
		
		if ((boolean) conditional.run(parentNamespace)) {
			retVal = ifTrue.run(parentNamespace);
		} else {
			retVal = ifFalse.run(parentNamespace);
		}
		
		return retVal;
	}
}
