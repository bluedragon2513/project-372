package statements;

import java.util.Map;

import control_structure.ReturnValue;
import variables.Variable;

/**
 * This class defines a return statement and returns a ReturnValue
 * 
 * Syntax: return <retVal>
 * @author Anthony Nguyen
 */
public class ReturnStatement implements ExecutableStatement {
	ExecutableStatement returnValue;
	
	public ReturnStatement(ExecutableStatement retVal) {
		this.returnValue = retVal;
	}

	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		return new ReturnValue(returnValue.run(namespace));
	}
}
