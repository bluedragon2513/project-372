package tokenization;

/**
 * Enum representing the type of token.
 */
public enum TokenType {
    NUMBER, BOOLEAN, STRING, // basic types
    VARIABLE, ASSIGN,
    LPAREN, RPAREN, COMMA, OPERATION, END, EOF,
    LANGLE, RANGLE, INBODY, LOOP, DONE, IF, THEN, ELSE, // control structures
    FUNCTION, RETURN, // functions
}
