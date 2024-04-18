package tokenization;

import control_structure.exceptions.TokenException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Tokenizer class to convert input string into a list of tokens.
 */
public class Tokenizer {
    private String input;
    private int position = 0;
    private char currentChar;
    private static final Set<String> operations = Set.of("add", "sub", "mult", "div", "mod", "equalTo", "greaterThan", "lessThan", "print", "readi", "readb", "reads", "readf",
    													 "and", "not", "or");
    private static final Set<String> controllers = Set.of("loop", "done", "return", "if", "then", "else", "function"); 
    private static final Set<String> booleans = Set.of("true", "false"); 

    // Constructor
    public Tokenizer(String input) {
        this.input = input;
        if (!input.isEmpty()) {
            this.currentChar = input.charAt(position);
        } else {
            this.currentChar = '\0';  // Handle empty input
        }
    }

    /**
     * Advance the 'position' pointer and set the 'currentChar' variable.
     * This essentially just walks us through the input one character at a time.
     */
    private void advance() {
        position++;
        if (position >= input.length()) {
            currentChar = '\0';  // EOF
        } else {
            currentChar = input.charAt(position);
        }
    }

    /**
     * Skip whitespace characters in the input string.
     */
    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    /**
     * Returns a number (multi-digit integer or float) from the input.
     * @return a number token
     */
    private Token number() {
        StringBuilder result = new StringBuilder();
        while (currentChar != '\0' && (Character.isDigit(currentChar) || currentChar == '.' || currentChar == '-')) {
            result.append(currentChar);
            advance();
        }
        return new Token(TokenType.NUMBER, result.toString());
    }

    /**
     * Determines if the current token is a variable or an operation.
     * @return an identifier or operation token
     */
    private Token identifierOrOperation() {
        StringBuilder result = new StringBuilder();

        // Check for valid identifier characters moving through the input one character at a time with 'advance'
        while (currentChar != '\0' && (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
            result.append(currentChar);
            advance();
        }

        // Convert the result to a string and check if it is an operation or an identifier
        String resultString = result.toString();
        if (operations.contains(resultString)) {
            return new Token(TokenType.OPERATION, resultString);
        } else if (controllers.contains(resultString)) {
        	if (resultString.equals("loop")) {
        		return new Token(TokenType.LOOP, resultString);
        	} else if (resultString.equals("done")) {
        		return new Token(TokenType.DONE, resultString);
        	} else if (resultString.equals("if")) {
        		return new Token(TokenType.IF, resultString);
        	} else if (resultString.equals("then")) {
        		return new Token(TokenType.THEN, resultString);
        	} else if (resultString.equals("else")) {
        		return new Token(TokenType.ELSE, resultString);
        	} else if (resultString.equals("function")) {
        		return new Token(TokenType.FUNCTION, resultString);
        	} else if (resultString.equals("return")) {
        		return new Token(TokenType.RETURN, resultString);
        	} 
        } else if (booleans.contains(resultString)) {
        	return new Token(TokenType.BOOLEAN, resultString);
        } else {
            return new Token(TokenType.VARIABLE, resultString);
        }
        
        return null;
    }
    
    /**
     * Returns a string from the input.
     * @return a string token
     */
    public Token string() {
    	StringBuilder result = new StringBuilder();
    	
    	// Check for valid identifier characters moving through the input one character at a time with 'advance'
        while (currentChar != '\0' && currentChar != '\"') {
            result.append(currentChar);
            advance();
        }
    	return new Token(TokenType.STRING, result.toString());
    }

    /**
     * Tokenize the input string and return a list of tokens.
     *
     * NOTE: We can add more token types and rules here to support different operations
     * @return a list of tokens
     * @exception TokenException unexpected token
     */
    public List<Token> tokenize() throws Exception {
        List<Token> tokens = new ArrayList<>();
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
            } else if (Character.isDigit(currentChar) || currentChar == '.' || currentChar == '-') {
                tokens.add(number());
            } else if (Character.isLetter(currentChar) || currentChar == '_') {
                tokens.add(identifierOrOperation());
            } else if (currentChar == '=') {
                tokens.add(new Token(TokenType.ASSIGN, "="));
                advance();
            } else if (currentChar == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                advance();
            } else if (currentChar == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                advance();
            } else if (currentChar == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                advance();
            } else if (currentChar == '!') {
                tokens.add(new Token(TokenType.END, "!"));
                advance();
            } else if (currentChar == '<') {
                tokens.add(new Token(TokenType.LANGLE, "<"));
                advance();
            } else if (currentChar == '>') {
                tokens.add(new Token(TokenType.RANGLE, ">"));
                advance();
            } else if (currentChar == '*') {
                tokens.add(new Token(TokenType.INBODY, "*"));
                advance();
            } else if (currentChar == '\"') {
            	advance();
            	tokens.add(string());
            	advance();
            } else {
                throw new TokenException("Unexpected character: " + currentChar);
            }
        }

        // Add EOF token at the end
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
