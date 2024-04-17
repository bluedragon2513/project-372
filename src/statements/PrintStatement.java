package statements;

import java.util.Map;

import variables.Variable;

public class PrintStatement implements ExecutableStatement {
	ExecutableStatement string;
	
	public PrintStatement(ExecutableStatement string) {
		this.string = string;
	}

	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		Object result = string.run(namespace);
		System.out.println(result);
		return result;
	}
}
