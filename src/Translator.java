import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class Translator
{
	public static void main(String[] args)
	{
		System.out.println("hello world");
//		readInput();
		
		
		Map<String, Variable> dict = new HashMap<String, Variable>();
		
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
}
