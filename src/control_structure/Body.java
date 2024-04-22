package control_structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import statements.ExecutableStatement;
import variables.Variable;

/**
 * This class defines the Body control structure
 * 	Essentially, it acts as a container of ExecutableStatements,
 * 	yet is also an ExecutableStatement itself
 * 		This is technically the composite pattern
 * 
 * @author Anthony Nguyen
 */
public class Body implements ExecutableStatement {
	List<ExecutableStatement> statements;
	boolean localNamespace = false;
	
	/**
	 * This constructor defines the normal Body, but the namespace is always local
	 */
	public Body(List<ExecutableStatement> statements) {
		this.statements = statements;
	}
	
	/**
	 * This constructor can define whether the namespace is local or of the parent's
	 * This is especially useful in functions vs. loop Bodies
	 */
	public Body(List<ExecutableStatement> statements, boolean localNamespace) {
		this.statements = statements;
		this.localNamespace = localNamespace;
	}

	@Override
	public Object run(Map<String, Variable> parentNamespace) throws Exception {
		Map<String, Variable> localNamespace = parentNamespace;
		if (this.localNamespace) {
			localNamespace = new HashMap<String, Variable>(parentNamespace);
			for (Map.Entry<String, Variable> entry : localNamespace.entrySet()) {
				String k = entry.getKey();
				Variable v = entry.getValue();
				Object value = v.getValue();
				if (value instanceof String || 
					value instanceof Integer || 
					value instanceof Boolean || 
					value instanceof Double) {
					localNamespace.put(k, new Variable(k, value));
				}
			}
		}

		Object last = null;
		
		for (ExecutableStatement s : statements) {
			Object ret = s.run(localNamespace);
			if (ret instanceof ReturnValue) {
				return ret;
			}
			last = ret;
		}
		
		return last;
	}
}
