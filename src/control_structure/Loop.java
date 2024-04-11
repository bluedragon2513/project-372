package control_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import statements.ExecutableStatement;
import statements.CallStatement;
import variables.Variable;

public class Loop {
	HashMap<String, Variable> localNamespace;
	ExecutableStatement start;
	ExecutableStatement opt = (namespace) -> {}; // optional
	Body body;
	ExecutableStatement end;

    public Loop(String startVar, String startExpr, String body, String endVar, String endExpr) {
    	this.start = (namespace) -> {
    		CallStatement startCall = new CallStatement(startExpr);
    		namespace.put(startVar, startCall.run(namespace));
    	};
    	this.body = new Body(body);
    	this.end = (namespace) -> {
    		CallStatement endCall = new CallStatement(endExpr);
    		namespace.put(endExpr, endCall.run(namespace));
    	};
    }
    
    public Loop(String startVar, String startExpr, String optVar, String optExpr, String body, String endVar, String endExpr) {
    	this(startVar,startExpr,body,endVar,endExpr);
    	
    	opt = (namespace) -> {
    		CallStatement optCall = new CallStatement(endExpr);
    		namespace.put(optVar, optCall.run(namespace));
    	};
    }
    
    public void run(HashMap<String, Variable> parentNamespace) {
    	this.localNamespace = new HashMap<String, Variable>(parentNamespace);
    	
    	start.run(localNamespace);
    	opt.run(localNamespace);
    	body.run(localNamespace);
    	end.run(localNamespace);
    }
}
