import java.util.ArrayList;
import java.util.HashMap;

import statements.ExecutableStatement;
import variables.Variable;

public class Body {
    ArrayList<ExecutableStatement> expressions;
    HashMap<String, Variable> local_namespace;

    public Body(ArrayList<ExecutableStatement> expressions) {
        this.expressions = expressions;
    }
    
    public void execute(HashMap<String, Variable> parent_namespace) {
    	this.local_namespace = new HashMap<String, Variable>(parent_namespace);
    	
    	for (ExecutableStatement ex : expressions) {
    		ex.run(local_namespace);
    	}
    }
}
