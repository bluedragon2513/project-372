package control_structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import statements.ExecutableStatement;
import variables.Variable;

public class Body implements ExecutableStatement {
	List<ExecutableStatement> statements;
	boolean localNamespace = false;
	
	public Body(List<ExecutableStatement> statements) {
		this.statements = statements;
	}
	
	public Body(List<ExecutableStatement> statements, boolean localNamespace) {
		this.statements = statements;
		this.localNamespace = localNamespace;
	}

	@Override
	public Object run(Map<String, Variable> parentNamespace) throws Exception {
		Map<String, Variable> localNamespace = parentNamespace;
		if (this.localNamespace)
			localNamespace = new HashMap<String, Variable>(parentNamespace);

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
