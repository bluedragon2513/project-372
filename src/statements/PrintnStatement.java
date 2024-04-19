package statements;

import java.util.Map;

import variables.Variable;

/**
 * This class defines a print statement
 * 	* it allows all 3 types to be printed
 * 
 * @author Anthony Nguyen
 */
public class PrintnStatement implements ExecutableStatement {
	ExecutableStatement string;
	
	public PrintnStatement(ExecutableStatement string) {
		this.string = string;
	}

	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		Object result = string.run(namespace);
		System.out.println(result);
		return result;
	}
}
