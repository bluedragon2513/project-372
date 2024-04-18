import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import variables.Variable;

/**
 * This program was made to simplify the running process
 * TODO: Erase this file and merge with Translator.java
 */
public class Compiler {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Expected 1 source file argument.");
            return;
        }

        String sourceCode = readInput(args[0]);
        Translator t = new Translator();
        Map<String, Variable> globalNamespace = new HashMap<String, Variable>();

		t.translate(sourceCode, globalNamespace);
    }

    private static String readInput(String fileName) {
		try {
            FileReader fr = new FileReader(fileName); 
			BufferedReader br = new BufferedReader(fr);
			
			StringBuilder str = new StringBuilder();

			while (br.ready()) {
				str.append(br.readLine());
			}
			
            br.close();
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
}
