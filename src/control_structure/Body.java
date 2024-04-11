package control_structure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import variables.Variable;
import statements.ExecutableStatement;

public class Body {
	String any = "(?<any>[^*]+)";
	String space = "\s*";
	String loopBody = "(?<body>(\\*" + any + ")+?)" + space;
	
    ArrayList<ExecutableStatement> expressions;
    HashMap<String, Variable> localNamespace;

    public Body(String unparsed) {
    	Pattern p = Pattern.compile("\\*" + space + any + space);
    	Matcher m = p.matcher(unparsed);
    	while (m.find())
    		System.out.println(m.group(1));
    }
    
    public void run(HashMap<String, Variable> parentNamespace) {
    	this.localNamespace = new HashMap<String, Variable>(parentNamespace);
    	
    	for (ExecutableStatement ex : expressions) {
    		ex.run(localNamespace);
    	}
    }
}
