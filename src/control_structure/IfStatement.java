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
		if ((boolean) conditional.run(parentNamespace)) {
			ifTrue.run(parentNamespace);
		} else {
			ifFalse.run(parentNamespace);
		}
		
		return null;
	}
}
