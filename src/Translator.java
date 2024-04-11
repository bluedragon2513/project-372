import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import variables.IntegerVar;
import variables.Variable;

import statements.*;

public class Translator
{
	public static void main(String[] args)
	{
		System.out.println("hello world");
//		readInput();
		
		Map<String, Variable> dict = new HashMap<String, Variable>();
		
		try {
			directStatementExecution(dict);
			testArithmeticExprParser(dict);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// test = 5
		// somehow identify the type of 5:
		Pattern intPattern = Pattern.compile("[0-9]+"); // ???
		
		// Create a new integer variable and add it to the dictionary
		dict.put("test", new IntegerVar("test", 5));
		
		System.out.println(dict.get("test").getValue());
		System.out.println(dict.get("test").isComparable());
		System.out.println(dict.get("test").isArithmetic());
	}

	private static void readInput()
	{
		try
		{
			FileReader fr = new FileReader("src/TestInput.txt");
			BufferedReader br = new BufferedReader(fr);

			char c;
			String curr = "";
			while (br.ready())
			{
				curr = br.readLine();
				checkValidExpr(curr);
			}
		}
		
		catch (FileNotFoundException fe)
		{
			System.out.println("Input file not found");
		}
		
		catch (IOException ioe)
		{
			System.out.println("IOException");
		}

	}
	
	private static void directStatementExecution(Map<String, Variable> dict) throws Exception {
		System.out.println("Directly Executing Statements:");
		
        IntegerStatement operand1 = new IntegerStatement(3);
        IntegerStatement operand2 = new IntegerStatement(5);

        AddStatement addStatement = new AddStatement(operand1, operand2);
        Integer result = addStatement.run(dict);
        System.out.println("Result of 'add(3,5)' is: " + result);
    }
	
	private static void testArithmeticExprParser(Map<String, Variable> dict) throws Exception {
		System.out.println("\nTesting ArithmeticAndLogicalExprParser:");
		
        String[] testExpressions = {
                "add(multiply(3,2),3)",
                "add(3,2)",
                "subtract(10,5)",
                "multiply(4,6)",
                "divide(10,2)",
                "mod(10,3)",
                "test(10,3)",
        };

        for (String expr : testExpressions) {
            try {
                System.out.println("Expression: " + expr);
                Integer result = ArithmeticExprParser.parseAndEvaluate(expr, dict);
                System.out.println("Result: " + result + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to parse: " + expr + ". Error: " + e.getMessage());
            }
        }
    }
}
