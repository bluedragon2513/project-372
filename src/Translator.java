import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import statements.ExecutableStatement;
import tokenization.Token;
import tokenization.Tokenizer;
import variables.Variable;

public class Translator {
	private Tokenizer tokenizer;
	private Parser parser;

	public Translator() {}

	public static void main(String[] args) {
		Map<String, Variable> globalNamespace = new HashMap<String, Variable>();
		Translator translator = new Translator();

		if (args.length == 1) {
			String sourceCode = readInput(args[0]);
			translator = new Translator();
			translator.translate(sourceCode, globalNamespace);
		}
		
		translator.interpreter(globalNamespace);
	}

	private static String readInput(String fileName) {
		FileReader fr; // get file from src/ or ./
		try {
			fr = new FileReader("src/" + fileName);
		} catch (FileNotFoundException fe) {
			try {
				fr = new FileReader(fileName);
			} catch (FileNotFoundException fe2) {
				System.out.println("Input file not found: " + fileName);
				return "";
			}
		}

		try {
			BufferedReader br = new BufferedReader(fr);
			
			StringBuilder str = new StringBuilder();

			while (br.ready()) {
				str.append(br.readLine());
				str.append('\n');
			}
			
			return str.toString();
		}
		catch (IOException ioe) {
			System.out.println("IOException");
		}

		return "";
	}

	@SuppressWarnings("resource") // for System.in resource
	public void interpreter(Map<String, Variable> globalNamespace) {
		Scanner sc = new Scanner(System.in); // do not close this Scanner!
		String next = ""; // ideally used for unfinished multi-line code
		Translator t = new Translator();
		while (true) {
			System.out.print(">> ");
			next = next + sc.nextLine();
			if (next.equals("exit"))
				break;
			try {
				t.translate(next, globalNamespace);
				next = "";
			} catch (Exception ex) { // should only be TypeExceptions
				System.out.println(ex);
			}
		}
	}

	public void translate(String sourceCode, Map<String, Variable> namespace) {
		this.tokenizer = new Tokenizer(sourceCode);

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
			System.out.println();
		}
	}
}
