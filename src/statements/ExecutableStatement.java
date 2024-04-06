import java.util.Map;

import variables.Variable;

public interface ExecutableStatement {
	public void run(Map<String, Variable> namespace);
}
