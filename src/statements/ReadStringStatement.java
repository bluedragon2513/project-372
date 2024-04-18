package statements;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import variables.Variable;

public class ReadStringStatement implements ExecutableStatement {
	private ExecutableStatement out = null;
	
	public ReadStringStatement(List<ExecutableStatement> arguments) {
		if (!arguments.isEmpty())
			out = arguments.get(0);
		assert(arguments.size() < 2);
	}
	
	@SuppressWarnings("resource") // for System.in resource!
	@Override
	public Object run(Map<String, Variable> namespace) throws Exception {
		if (out != null)
			System.out.print(out.run(namespace));
		
		Scanner sc = new Scanner(System.in); // do not close Scanner!
		String x = sc.nextLine();
		
		return x;
	}

}