import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import statements.ExecutableStatement;
import tokenization.Token;
import tokenization.Tokenizer;

public class Compiler {

	/**
	 * Compiles the given file into a list of ExecutableStatements
	 * and saves the compiled program with the extension .oop
	 * 
	 * @param args the first element has the fileName
	 */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Expected 1 source file argument.");
            return;
        }

        String sourceCode = Translator.readInput(args[0]);
        
		List<ExecutableStatement> statements = compile(sourceCode);
		if (statements == null) return;

		String outFileName = args[0].substring(0, args[0].lastIndexOf('.')) + ".oop";
		if (save(statements, outFileName)) {
			System.out.println("File successfully compiled: " + outFileName);
		}
    }

	/**
	 * Compiles the sourceCode into a list of ExecutableStatements
	 * 
	 * @param sourceCode
	 * @return List<ExecutableStatement>
	 */
	public static List<ExecutableStatement> compile(String sourceCode) {
		Tokenizer tokenizer = new Tokenizer(sourceCode);

		try {
			List<Token> tokens = tokenizer.tokenize();
			Parser parser = new Parser(tokens);
			return parser.parseProgram();
		} catch (Exception e) {
			System.err.println("Error during compilation: " + e.getMessage());
			e.printStackTrace(); // Provide more detailed error information
			System.out.println();
		}

		return null;
	}
	
	/**
	 * Save the list of statements of the program in a file
	 * 	Uses the Serializable interface to save the program
	 * 
	 * @author Anthony Nguyen
	 * @param statements is the program
	 * @param outFileName is where the program will be stored
	 * @return whether the save was completed or not
	 */
	private static boolean save(List<ExecutableStatement> statements, String outFileName) {
		try {
			FileOutputStream outStream = new FileOutputStream(outFileName);
			ObjectOutputStream outFile = new ObjectOutputStream(outStream);

			// write to the file and close it
			outFile.writeObject(statements);
			outFile.close();
			return true;
		} catch (IOException ex) {
			System.out.println("Failed to save file.");
		}
		
		return false;
	}
}
