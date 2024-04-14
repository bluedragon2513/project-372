import java.util.ArrayList;
import java.util.List;

import control_structure.*;
import statements.*;
import tokenization.Token;
import tokenization.TokenType;

/**
 * This class is responsible for parsing the tokens into executable statements.
 */
public class Parser {
    private List<Token> tokens;
    private int current = 0;

    // Constructor
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Advances the pointer to the next token in the list of tokens.
     * It returns the previous token, which is useful for parsing (i.e., current token is '=' and previous token is 'x'
     * we know that this is an ASSIGNMENT and 'x' is a variable).
     * @return Token the previous token
     */
    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    /**
     * Checks if the current token is the end of the list of tokens.
     * @return true if the current token is the end of the list of tokens, false otherwise
     */
    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    /**
     * Returns the current token.
     * @return Token the current token
     */
    private Token peek() {
        return tokens.get(current);
    }

    /**
     * Returns the previous token.
     * @return Token the previous token
     */
    private Token previous() {
        return tokens.get(current - 1);
    }

    /**
     * Returns the token at a given index relative to the current token.
     * @param i the index relative to the current token
     * @return Token the token at the given index
     */
    private Token lookAhead(int i) {
        return tokens.get(current + i);
    }

    /**
     * Consumes the current token if it matches the expected type.
     * If the current token does not match the expected type, it throws an exception.
     * @param type the expected token type
     * @param errorMessage the error message to display if the current token does not match the expected type
     * @return Token the consumed token
     */
    private Token consume(TokenType type, String errorMessage) {
        if (check(type)) {
            return advance();
        }
        throw new RuntimeException(errorMessage + " Found: " + peek().type);
    }

    /**
     * Checks if the current token matches the expected type.
     * @param type the expected token type
     * @return true if the current token matches the expected type, false otherwise
     */
    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    /**
     * Parses the program and returns a list of executable Java statements.
     * @return a list of executable statements
     * @throws Exception if an error occurs while parsing the program
     */
    public List<ExecutableStatement> parseProgram() throws Exception {
        List<ExecutableStatement> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(parseStatement());
            consume(TokenType.END, "Expected '!' after statement.");
        }
        return statements;
    }

    /**
     * Parses a single statement and returns an executable Java statement.
     * @return an executable statement
     * @throws Exception if an error occurs while parsing the statement
     */
    private ExecutableStatement parseStatement() throws Exception {
        // Check if the statement is an assignment or an expression
        if (peek().type == TokenType.VARIABLE && lookAhead(1).type == TokenType.ASSIGN) {
            return parseAssignment();
        } else if (peek().type == TokenType.LOOP) {
        	return parseLoop();
        } else if (peek().type == TokenType.IF) {
        	return parseIf();
        } else {
            return parseExpression();
        }
    }

    /**
     * Parses an assignment statement and returns an executable Java statement.
     * @return an executable statement
     * @throws Exception if an error occurs while parsing the assignment statement
     */
    public ExecutableStatement parseAssignment() throws Exception {
        Token variable = consume(TokenType.VARIABLE, "Expected variable name.");
        consume(TokenType.ASSIGN, "Expected '=' after variable name.");
        ExecutableStatement value = parseExpression(); // Handles the right side of assignment
        return new AssignmentStatement(variable.value, value);
    }

    /**
     * Parses an expression and returns an executable Java statement.
     * @return an executable statement
     */
    private ExecutableStatement parseExpression() {
        /*
         * An expression can be a single value, a variable, or a nested operation.
         * If the current token is an operation, we parse the operation.
         * Otherwise, we parse the inside of the expression.
         */
        if (peek().type == TokenType.OPERATION) {
            return parseOperation();
        } else {
            return parseInside();
        }
    }

    /**
     * Parses the inside of an expression and returns an executable Java statement.
     * @return an executable statement
     */
    private ExecutableStatement parseInside() {
        // Check if the current token is a number
        if (check(TokenType.NUMBER)) {
            return new ValueStatement(Double.parseDouble(advance().value));
        // Check if the current token is a boolean
        } else if (check(TokenType.BOOLEAN)) {
        	return new ValueStatement(advance().value.equals("true") ? true : false);
        // Check if the current token is a variable
        } else if (check(TokenType.VARIABLE)) {
            return new VariableStatement(advance().value);
        // Check if the current token is a left parenthesis (This could lead to an expression to parse, or an operation)
        } else if (check(TokenType.LPAREN)) {
            advance();
            ExecutableStatement expression = parseExpression();
            consume(TokenType.RPAREN, "Expect ')' after expression.");
            return expression;
        }
        throw new RuntimeException("Unexpected token: " + peek().type);
    }

    /**
     * Parses an operation and returns an executable Java statement.
     * @return an executable statement
     */
    private ExecutableStatement parseOperation() {
        String operation = consume(TokenType.OPERATION, "Expected operation name.").value;
        consume(TokenType.LPAREN, "Expected '(' after operation name.");
        List<ExecutableStatement> statements = new ArrayList<>();
        if (!check(TokenType.RPAREN)) {
            do {
                statements.add(parseExpression());
                // Explicitly check and advance if the next token is a comma
                if (check(TokenType.COMMA)) {
                    advance();
                } else {
                    break; // Break if no comma is present
                }
            } while (true);
        }
        consume(TokenType.RPAREN, "Expected ')' after arguments.");

        switch (operation) {
            case "add":
                return new AddStatement(statements.get(0), statements.get(1));
            case "sub":
                return new SubStatement(statements.get(0), statements.get(1));
            case "mult":
                return new MulStatement(statements.get(0), statements.get(1));
            case "div":
                return new DivStatement(statements.get(0), statements.get(1));
            case "mod":
            	return new ModStatement(statements.get(0), statements.get(1));
            case "equalTo":
            	return new EqualStatement(statements.get(0), statements.get(1));
            default:
                throw new RuntimeException("Unknown operation: " + operation);
        }
    }
    
    /**
     * Parses a loop and returns an executable Java statement
     * 		loop <x=0> <add(x,2)>
     * 			* result = add(x,1)!
     * 		done <equalTo(x,6)>!
     * 
     * @return an executable statement
     * @throws Exception
     */
    private ExecutableStatement parseLoop() throws Exception {
    	consume(TokenType.LOOP, "Expected 'loop' keyword");
    	consume(TokenType.LANGLE, "Expected '<' after 'loop' keyword.");
    	
    	List<ExecutableStatement> statements = new ArrayList<>();
    	String startVar = peek().value;
    	ExecutableStatement start = parseAssignment();
    	consume(TokenType.RANGLE, "Expected '>' after loop initializer.");
    	
    	consume(TokenType.LANGLE, "Expected '<' after loop initializer.");
    	ExecutableStatement increment = parseOperation();
    	if (!(increment instanceof AddStatement) && !(increment instanceof SubStatement)) {
    		throw new Exception("Expected incremental operation.");
    	}
    	consume(TokenType.RANGLE, "Expected '>' after loop incrementer.");
    	
    	while (peek().type == TokenType.INBODY) { 
    		consume(TokenType.INBODY, "Expected '*' to separate statements");
    		statements.add(parseStatement());
    	}
    	consume(TokenType.DONE, "Expected 'done' keyword after loop body.");
    	consume(TokenType.LANGLE, "Expected '<' after done keyword.");
    	ExecutableStatement end = parseOperation();
    	consume(TokenType.RANGLE, "Expected '>' after done closer.");
    	
    	return new Loop(startVar, start, increment, end, statements);
    }
    
    private ExecutableStatement parseIf() throws Exception {
    	consume(TokenType.IF, "Expected 'if' keyword.");
    	
    	consume(TokenType.LANGLE, "Expected '<' after 'if' keyword.");
    	ExecutableStatement conditional = parseOperation();
    	if (!(conditional instanceof EqualStatement)) {
    		throw new Exception("Expected conditional operation.");
    	}
    	consume(TokenType.RANGLE, "Expected '>' after conditional operation.");
    	
    	consume(TokenType.THEN, "Expected 'then' keyword after conditional operation.");
    	ExecutableStatement ifTrue = parseStatement();

    	consume(TokenType.ELSE, "Expected 'else' keyword after true statement.");    	
    	ExecutableStatement ifFalse = parseStatement();
    	consume(TokenType.DONE, "Expected 'done' keyword for 'if' closer.");
    	
    	return new IfStatement(conditional, ifTrue, ifFalse);
    }
}
