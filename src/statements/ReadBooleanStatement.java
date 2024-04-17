package statements;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import variables.Variable;

public class ReadBooleanStatement implements ExecutableStatement {
	private ExecutableStatement out = null;
	
	public ReadBooleanStatement(List<ExecutableStatement> arguments) {
		if (!arguments.isEmpty())
			out = arguments.get(0);
		assert(arguments.size() < 2);
	}
	
	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		if (out != null)
			System.out.print(out.run(namespace));
		
		Scanner sc = new Scanner(System.in);
		boolean x = sc.nextBoolean();
		
		return x;
	}

}
