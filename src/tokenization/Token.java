package tokenization;

/**
 * An instance of this class represents a token.
 */
public  class Token {
    public TokenType type;
    public String value;

    // Constructor
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Returns the type of the token.
     * @return TokenType
     */
    public TokenType getType() {
        return this.type;
    }

    /**
     * Returns the value of the token.
     * @return String
     */
    public String getValue() {
        return this.value;
    }
}

