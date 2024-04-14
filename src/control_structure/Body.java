package control_structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import statements.ExecutableStatement;
import variables.Variable;

public class Body implements ExecutableStatement {
	List<ExecutableStatement> statements;
	HashMap<String, Variable> localNamespace;
	
	public Body(List<ExecutableStatement> statements) {
		this.statements = statements;
	}

	@Override
	public Object run(Map<String, Variable> parentNamespace) throws Exception {
		this.localNamespace = new HashMap<String, Variable>(parentNamespace);

		for (ExecutableStatement s : statements) {
			s.run(localNamespace);
		}
		
		return null;
	}
}
