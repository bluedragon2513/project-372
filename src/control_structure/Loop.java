package control_structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import statements.ExecutableStatement;
import variables.Variable;

public class Loop implements ExecutableStatement {
	HashMap<String, Variable> localNamespace;
	ExecutableStatement start;
	ExecutableStatement opt = (namespace) -> { return null; }; // optional
	Body body;
	ExecutableStatement end;

	public Loop(ExecutableStatement start, ExecutableStatement end, List<ExecutableStatement> body) {
		this.start = start;
		this.end = end;
		this.body = new Body(body);
	}
    
    public Object run(Map<String, Variable> parentNamespace) throws Exception {
    	this.localNamespace = new HashMap<String, Variable>(parentNamespace);
    	
    	// TODO this incrementer is not correct!
    	double i = (double) start.run(localNamespace);
    	double j = (double) end.run(new HashMap<String, Variable>());
    	double increment = i < j ? 1 : i > j ? -1 : 0;
    	for (; i != j; i += increment) {
    		body.run(localNamespace);
    	}
    	
//    	opt.run(localNamespace);
    	
    	return i;
    }
}
