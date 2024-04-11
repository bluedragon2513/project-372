package control_structure;

import java.util.HashMap;

import statements.ExecutableStatement;
import statements.CallStatement;
import variables.Variable;

public class IfStatement {
	HashMap<String, Variable> localNamespace;
	ExecutableStatement ifStatement;
	
	public IfStatement(String condition, String then, String otherwise) {
		this.ifStatement = (namespace) -> {
			CallStatement conditional = new CallStatement(condition);
			CallStatement thenHere = new CallStatement(then);
			CallStatement other = new CallStatement(otherwise);
			
			if (conditional.run(namespace)) {
				thenHere.run(namespace);
			} else {
				other.run(namespace);
			}
		};
	}
	
	public void run(HashMap<String, Variable> parentNamespace) {
		ifStatement.run(parentNamespace);
	}
}
