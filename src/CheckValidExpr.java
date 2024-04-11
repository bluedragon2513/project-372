import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import statements.AssignmentStatement;
import statements.CallStatement;



public class CheckValidExpr {
    

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print(">> ");
    	
    	String cmd = scanner.nextLine();
        while (!cmd.equals("exit")) {
        	parseAssignment(cmd);
            cmd = scanner.nextLine();
        }
    	System.out.println(">> ");
        scanner.close();
    	

    }

	private static boolean parseAssignment(String cmd) {
		// this is what an assignment statement will look like
        // x = 5
        // y = false
        // z = "hello world"
		String variable  = "(?<variable>[a-z]+)";
		String equals = "(?<equals> = )";
	    String value = "(?<value>\\d+|true|false|\".*?\")";
		
		Pattern assignment = Pattern.compile(variable+equals+value);
		Matcher m = assignment.matcher(cmd);

		System.out.println(m.find());
		System.out.print(m.group("variable"));
		System.out.print(m.group("equals"));
		System.out.println(m.group("value"));
		
		if (m.find()) {
			
			String v1 = m.group("variable");
            String v2 = m.group("value");
            
            
            CallStatement<?> varExpr = new CallStatement<>(v2);
            AssignmentStatement<?> statement = 
            		new AssignmentStatement<>(v1, varExpr);		
		
		}
		return false;
	}
	
	
}
