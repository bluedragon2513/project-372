package tokenization;

/**
 * Enum representing the type of token.
 */
public enum TokenType {
    NUMBER, VARIABLE, ASSIGN,
    LPAREN, RPAREN, COMMA, OPERATION, END, EOF,
    LANGLE, RANGLE, INBODY, LOOP, DONE, IF, THEN // control structures
}
