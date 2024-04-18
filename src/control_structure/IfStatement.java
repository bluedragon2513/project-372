package control_structure;

import java.util.HashMap;
import java.util.Map;

import statements.ExecutableStatement;
import variables.Variable;

/**
 * This class defines a normal If Statement
 * 	- It is possible to chain if statements together to create
 * 	else-if chains
 * 	- It is possible to omit INBODY (*) characters for single
 * 	line statements
 * 
 * Syntax: if <bool> then * statement else * statement done
 * @author Anthony Nguyen
 */
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
