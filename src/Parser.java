import java.util.ArrayList;
import java.util.List;

import control_structure.*;
import control_structure.exceptions.ParseException;
import statements.*;
import tokenization.Token;
import tokenization.TokenType;

/**
 * This class is responsible for parsing the tokens into ExecutableStatements.
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
     * @exception ParseException the current token does not match the expected type
     */
    private Token consume(TokenType type, String errorMessage) throws Exception {
        if (check(type)) {
            return advance();
        }
        throw new ParseException(errorMessage + " Found: " + peek().type);
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
     * Parses the program and returns a list of ExecutableStatements.
     * @return a List of ExecutableStatement
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
     * Parses a single statement and returns an ExecutableStatement.
     * @return ExecutableStatement
     * @throws Exception if an error occurs while parsing the statement
     */
    private ExecutableStatement parseStatement() throws Exception {
        // Check if the statement is an assignment or an expression
        if (peek().type == TokenType.VARIABLE && lookAhead(1).type == TokenType.ASSIGN) {
            return parseAssignment();
        } else if (peek().type == TokenType.FUNCTION) {
        	return parseFunctionDefinition();
        } else if (peek().type == TokenType.LOOP) {
        	return parseLoop();
        } else if (peek().type == TokenType.IF) {
        	return parseIf();
        } else if (peek().type == TokenType.RETURN) {
        	return parseReturn();
        } else {
            return parseExpression();
        }
    }

    /**
     * Parses an assignment statement and returns an ExecutableStatement.
     * @return ExecutableStatement
     * @throws Exception if an error occurs while parsing the assignment statement
     */
    public ExecutableStatement parseAssignment() throws Exception {
        Token variable = consume(TokenType.VARIABLE, "Expected variable name.");
        consume(TokenType.ASSIGN, "Expected '=' after variable name.");
        ExecutableStatement value = parseExpression(); // Handles the right side of assignment
        return new AssignmentStatement(variable.value, value);
    }

    /**
     * Parses an expression and returns an ExecutableStatement.
     * @returnExecutableStatement
     */
    private ExecutableStatement parseExpression() throws Exception {
        /*
         * An expression can be a single value, a variable, or a nested operation.
         * If the current token is an operation, we parse the operation.
         * Otherwise, we parse the inside of the expression.
         */
        if (peek().type == TokenType.OPERATION) {
            return parseOperation();
        } else if (peek().type == TokenType.VARIABLE && lookAhead(1).type == TokenType.LPAREN) {
        	return parseFunctionCall();
        } else {
            return parseInside();
        }
    }

    /**
     * Parses the inside of an expression and returns an ExecutableStatement.
     * @return ExecutableStatement
     */
    private ExecutableStatement parseInside() throws Exception {
        // Check if the current token is a number
        if (check(TokenType.NUMBER)) {
        	if (peek().value.contains("."))
        		return new ValueStatement(Double.parseDouble(advance().value));
        	return new ValueStatement(Integer.parseInt(advance().value));
        // Check if the current token is a boolean
        } else if (check(TokenType.BOOLEAN)) {
        	return new ValueStatement(advance().value.equals("true") ? true : false);
        // Check if the current Token is a string
        } else if (check(TokenType.STRING)) {
        	return new ValueStatement(advance().value);
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
     * Parses an operation and returns an ExecutableStatement.
     * @return ExecutableStatement
     */
    private ExecutableStatement parseOperation() throws Exception {
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
            case "greaterThan":
            	return new GreaterThanStatement(statements.get(0), statements.get(1));
            case "lessThan":
                return new LessThanStatement(statements.get(0), statements.get(1));
            case "and":
            	return new AndStatement(statements.get(0), statements.get(1));
            case "or":
            	return new OrStatement(statements.get(0), statements.get(1));
            case "not":
            	return new NotStatement(statements.get(0));
            case "print":
            	return new PrintStatement(statements.get(0));
            case "printn":
                return new PrintnStatement(statements.get(0));
            case "readi":
            	return new ReadIntegerStatement(statements);
            case "reads":
            	return new ReadStringStatement(statements);
            case "readb":
            	return new ReadBooleanStatement(statements);
            default:
                throw new RuntimeException("Unknown operation: " + operation);
        }
    }
    
    /**
     * Parses a loop and returns an ExecutableStatement
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
    	
    	while (peek().type == TokenType.INBODY && lookAhead(1).type != TokenType.DONE) { 
    		consume(TokenType.INBODY, "Expected '*' to separate statements");
    		statements.add(parseStatement());
    	}
    	
    	if (peek().type == TokenType.INBODY && lookAhead(1).type == TokenType.DONE)
    		consume(TokenType.INBODY, "Expected '*' while in a body");
    	consume(TokenType.DONE, "Expected 'done' keyword after loop body.");
    	consume(TokenType.LANGLE, "Expected '<' after done keyword.");
    	ExecutableStatement end = parseOperation();
    	consume(TokenType.RANGLE, "Expected '>' after done closer.");
    	
    	return new Loop(startVar, start, increment, end, statements);
    }
    
    /**
     * Parses an if and returns an ExecutableStatement
     * 		if <equalTo(1,1)> then
     * 			* print("YES!")
     *      else
     *          * print("NO!")
     *      done
     * 
     * @return an executable statement
     * @throws Exception
     */
    private ExecutableStatement parseIf() throws Exception {
    	consume(TokenType.IF, "Expected 'if' keyword.");
    	
    	consume(TokenType.LANGLE, "Expected '<' after 'if' keyword.");
	ExecutableStatement conditional = parseExpression();
    	// ExecutableStatement conditional = parseOperation();
    	// if (!(conditional instanceof EqualStatement) && !(conditional instanceof GreaterThanStatement)
    	//    && !(conditional instanceof AndStatement) && !(conditional instanceof OrStatement) && !(conditional instanceof NotStatement)) {
    	// 	throw new Exception("Expected conditional operation.");
    	// }
    	consume(TokenType.RANGLE, "Expected '>' after conditional operation.");
    	
    	consume(TokenType.THEN, "Expected 'then' keyword after conditional operation.");
    	List<ExecutableStatement> ifTrueBody = new ArrayList<ExecutableStatement>();
    	ExecutableStatement ifTrue = null;
    	if (peek().type == TokenType.INBODY) {
	    	while (peek().type == TokenType.INBODY && lookAhead(1).type != TokenType.ELSE) { 
	    		consume(TokenType.INBODY, "Expected '*' to separate statements");
	    		ifTrueBody.add(parseStatement());
	    	}
    	} else {
    		ifTrue = parseStatement();
    	}

    	if (peek().type == TokenType.INBODY && lookAhead(1).type == TokenType.ELSE)
    		consume(TokenType.INBODY, "Expected 'inbody' keyword while in body");
    	
    	consume(TokenType.ELSE, "Expected 'else' keyword after true statement.");    	
    	List<ExecutableStatement> ifFalseBody = new ArrayList<ExecutableStatement>();
    	ExecutableStatement ifFalse = null;
    	if (peek().type == TokenType.INBODY) {
	    	while (peek().type == TokenType.INBODY) { 
	    		consume(TokenType.INBODY, "Expected '*' to separate statements");
	    		ifFalseBody.add(parseStatement());
	    	}
    	} else {
    		ifFalse = parseStatement();
    	}
    	
    	if (peek().type == TokenType.INBODY && lookAhead(1).type == TokenType.DONE)
    		consume(TokenType.INBODY, "Expected '*' while in body");
    	consume(TokenType.DONE, "Expected 'done' keyword for 'if' closer.");
    	
    	if (ifTrue == null && ifFalse == null)
    		return new IfStatement(conditional, new Body(ifTrueBody), new Body(ifFalseBody));
    	else if (ifTrue == null)
    		return new IfStatement(conditional, new Body(ifTrueBody), ifFalse);
    	else if (ifFalse == null)
    		return new IfStatement(conditional, ifTrue, new Body(ifFalseBody));
    	
    	return new IfStatement(conditional, ifTrue, ifFalse);
    }
    
    /**
     * Parses a function definition
     *      function name(params)
     *          * param1 = 5
     *      done
     * 
     * @return
     * @throws Exception
     */
    private ExecutableStatement parseFunctionDefinition() throws Exception {
    	consume(TokenType.FUNCTION, "Expected 'function' keyword.");
    	
    	String funcName = consume(TokenType.VARIABLE, "Expected a function name.").value;
    	consume(TokenType.LPAREN, "Expected '(' after operation name.");
    	List<String> parameters = new ArrayList<>();
        if (!check(TokenType.RPAREN)) {
            do {
            	parameters.add(advance().value);
                // Explicitly check and advance if the next token is a comma
                if (check(TokenType.COMMA)) {
                    advance();
                } else {
                    break; // Break if no comma is present
                }
            } while (true);
        }
        consume(TokenType.RPAREN, "Expected ')' after parameters.");
        
        List<ExecutableStatement> statements = new ArrayList<>();
        while (peek().type == TokenType.INBODY) { 
    		consume(TokenType.INBODY, "Expected '*' to separate statements");
    		statements.add(parseStatement());
    	}
    	
        if (peek().type == TokenType.INBODY && lookAhead(1).type == TokenType.DONE)
        	consume(TokenType.INBODY, "Expected '*' while in a body");
        consume(TokenType.DONE, "Expected 'done' keyword for 'function' closer.");
    	return new FuncDeclarationStatement(funcName, parameters, statements);
    }
    
    /**
     * Parses a function and returns an ExecutableStatement.
     *      funcName(arguments)
     * 
     * @return ExecutableStatement
     * @throws Exception
     */
    private ExecutableStatement parseFunctionCall() throws Exception {
        String function = consume(TokenType.VARIABLE, "Expected function name.").value;
        consume(TokenType.LPAREN, "Expected '(' after operation name.");
        List<ExecutableStatement> arguments = new ArrayList<>();
        if (!check(TokenType.RPAREN)) {
            do {
            	arguments.add(parseExpression());
                // Explicitly check and advance if the next token is a comma
                if (check(TokenType.COMMA)) {
                    advance();
                } else {
                    break; // Break if no comma is present
                }
            } while (true);
        }
        consume(TokenType.RPAREN, "Expected ')' after arguments in parse function call." + function + lookAhead(1).value);

        return new FuncCallStatement(function, arguments);
    }
    
    /**
     * Parses a return statement and returns an ExecutableStatement (ReturnStatement)
     *      return value
     * 
     * @return a ReturnStatement
     * @throws Exception
     */
    private ExecutableStatement parseReturn() throws Exception {
    	consume(TokenType.RETURN , "Expected 'return' keyword.");
    	ExecutableStatement retVal = parseStatement();
    	return new ReturnStatement(retVal);
    }
}
