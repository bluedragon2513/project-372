import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import variables.IntegerVar;
import variables.Variable;

import statements.*;
import tokenization.Token;
import tokenization.Tokenizer;

public class Translator
{
	private Tokenizer tokenizer;
    private Parser parser;
    private String sourceCode;
    
	public Translator(String sourceCode) {
		this.sourceCode = sourceCode;
		this.tokenizer = new Tokenizer(sourceCode);
	}
	
	public static void main(String[] args)
	{
//		System.out.println("hello world");
//		readInput();
		
		Map<String, Variable> dict = new HashMap<String, Variable>();
		
		// test = 5
		// somehow identify the type of 5:
		Pattern intPattern = Pattern.compile("[0-9]+"); // ???
		
		// Create a new integer variable and add it to the dictionary
//		dict.put("test", new IntegerVar("test", 5));
		
//		System.out.println(dict.get("test").getValue());
//		System.out.println(dict.get("test").isComparable());
//		System.out.println(dict.get("test").isArithmetic());
		
		String sourceCode = "result = add(mult(2,3), mult(2,3))! test = add(result,1)!"; // Example source code
        Translator translator = new Translator(sourceCode);
        translator.translate();
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
//				checkValidExpr(curr);
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
	
	 public void translate() {
	        try {
	            // Tokenize the input source code
	            List<Token> tokens = tokenizer.tokenize();
	            System.out.println("Tokens:");
	            for (Token token : tokens) {
	                System.out.println(token.getType() + " " + token.getValue());
	            }

	            // Parse the tokens into executable statements
	            parser = new Parser(tokens);
	            List<ExecutableStatement> program = parser.parseProgram();
	            System.out.println("Parsed Statements:");
	            for (ExecutableStatement statement : program) {
	                System.out.println(statement.getClass().getSimpleName());
	            }

	            // Execute parsed statements and print results
	            Map<String, Variable> namespace = new HashMap<>();
	            System.out.println("Execution Outputs:");
	            for (ExecutableStatement statement : program) {
	                Object result = statement.run(namespace);
	                System.out.println(result); // Print the result of each statement execution
	            }

	        } catch (Exception e) {
	            System.err.println("Error during translation: " + e.getMessage());
	            e.printStackTrace(); // Provide more detailed error information
	        }
	    }
}
