import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import statements.ExecutableStatement;
import tokenization.Token;
import tokenization.Tokenizer;
import variables.Variable;

public class Translator {
	private Tokenizer tokenizer;
	private Parser parser;
	private String sourceCode;

	public Translator(String sourceCode) {
		this.sourceCode = sourceCode;
		this.tokenizer = new Tokenizer(sourceCode);
	}

	public static void main(String[] args) {
//		System.out.println("hello world");
//		readInput();

		Map<String, Variable> globalNamespace = new HashMap<String, Variable>();

		if (args.length == 1) {
			String sourceCode = readInput(args[0]);
			Translator translator = new Translator(sourceCode);
			translator.translate(globalNamespace);
		}
		
		Scanner sc = new Scanner(System.in);
		String next = "";
		Translator t;
		while (true) {
			System.out.print(">> ");
			next = next + sc.nextLine();
			if (next.equals("exit"))
				break;
			try {
				t = new Translator(next);
				t.translate(globalNamespace);
				next = "";
			} catch (Exception ex) {
				System.out.println("Idk" + ex);
			}
		}
		
		String sourceCode = "result = add(mult(2,3), mult(2,3))! test = add(result,1)!"; // Example source code
		sourceCode = "x = equalTo(9,9)!";
//		sourceCode = "test = 1! loop <x=5> <add(x,1)> * test = add(test,1) done <equalTo(x,9)>!";
//		sourceCode = "x=5! if <equalTo(x,5)> then y=true else y=false done! y!";
//		sourceCode = "loop <x=5> <add(x,1)> * if <equalTo(x,6)> then print(\"yes\") else print(\"no\") done done <equalTo(x,10)>!";
////		sourceCode = "print(\"YEEET\")!";
//		sourceCode = "x=10! function heh(y) * print(y) done! heh(x)!";
//		sourceCode = "function max(x,y) * if <greaterThan(x,y)> then return x else return y done done! max(5,7)!";
////		sourceCode = "function max(x,y) return y done! max(5,7)!";
//		sourceCode = "function sum(x,y) * add(x,y) done! sum(5,7)!";
//		sourceCode = "function difference(x,y) * sub(x,y) done! difference(5,7)!";
//		sourceCode = "function gcd(x,y) * if <equalTo(x,0)> then return y else return gcd(mod(y, x), x) done done! gcd(2,4)!";
		
//		sourceCode = "print(add(5,readi()))!"; // readi
//		sourceCode = "print(equalTo(false, readb()))!"; // readb
//		sourceCode = "print(\nequalTo(reads(), \"5\"))\n!"; // reads
//		sourceCode = "readf()";
		
//		sourceCode = "print(something(readi(), readb()))!";
		
		
		Translator translator = new Translator(sourceCode);
//		translator.translate(globalNamespace);
	}

	private static String readInput(String fileName) {
		try {
//			FileReader fr = new FileReader(fileName); // via command-line
			FileReader fr = new FileReader("src/" + fileName); // via Eclipse
			BufferedReader br = new BufferedReader(fr);
			
			StringBuilder str = new StringBuilder();

			while (br.ready()) {
				str.append(br.readLine());
			}
			
			return str.toString();
		}

		catch (FileNotFoundException fe) {
			System.out.println("Input file not found: ");
		}

		catch (IOException ioe) {
			System.out.println("IOException");
		}

		return "";
	}

	public void translate(Map<String, Variable> namespace) {
		try {
			// Tokenize the input source code
			List<Token> tokens = tokenizer.tokenize();
			System.out.println("Tokens:");
			for (Token token : tokens) {
				System.out.println(token.getType() + " " + token.getValue());
			}
			
			System.out.println(); // Separate tokenization and parsing output

			// Parse the tokens into executable statements
			parser = new Parser(tokens);
			List<ExecutableStatement> program = parser.parseProgram();
			System.out.println("Parsed Statements:");
			for (ExecutableStatement statement : program) {
				System.out.println(statement.getClass().getSimpleName());
			}
			
			System.out.println(); // Separate parsing and execution output

			// Execute parsed statements and print results
//			Map<String, Variable> namespace = new HashMap<>();
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
