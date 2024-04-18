package control_structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import statements.AssignmentStatement;
import statements.ExecutableStatement;
import variables.Variable;

/**
 * This class defines a loop.
 * 	While this class defines a classical for-loop,
 * 	it is not impossible to implement while-like loops
 * 
 * Syntax: loop <var=val> <sub(var,num)> * statement done <equalTo(var,endingValue)>
 * @author Anthony Nguyen
 */
public class Loop implements ExecutableStatement {
	HashMap<String, Variable> localNamespace;
	String startVar;
	ExecutableStatement start;
	ExecutableStatement opt = (namespace) -> { return null; }; // optional
	Body body;
	ExecutableStatement end;

	public Loop(ExecutableStatement start, ExecutableStatement end, List<ExecutableStatement> body) {
		this.start = start;
		this.end = end;
		this.body = new Body(body);
	}
	
	public Loop(String startVar, ExecutableStatement start, ExecutableStatement increment, ExecutableStatement end, List<ExecutableStatement> body) {
		this.startVar = startVar;
		this.start = start;
		this.opt = new AssignmentStatement(startVar, increment);
		this.end = end;
		this.body = new Body(body);
	}
    
    public Object run(Map<String, Variable> parentNamespace) throws Exception {
    	this.localNamespace = new HashMap<String, Variable>(parentNamespace);
    	
    	// TODO this incrementer is not correct!
    	Object lastVal = null;
    	
//    	int i = 0;
    	
    	start.run(localNamespace); // create variable
    	while (!(boolean) end.run(localNamespace)) {
    		lastVal = body.run(localNamespace); // run body
    		opt.run(localNamespace); // increment
    		
    		if (lastVal instanceof ReturnValue) {
    			return lastVal;
    		}
    	}
    	
//    	opt.run(localNamespace);
    	
    	return lastVal;
    }
}
