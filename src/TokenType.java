//defines tokens that can appear in our language
public class TokenType {
    //integer token (1, 2, 3, and so on)
    public static TokenType integer = new TokenType("int");
    //token type for our identifiers
    public static TokenType variable = new TokenType("variable");
    public static TokenType plus = new TokenType("plus");
    public static TokenType minus = new TokenType("minus");
    public static TokenType mult = new TokenType("mult");
    public static TokenType div = new TokenType("div");
    public static TokenType mod = new TokenType("mod");
    public static TokenType lparen = new TokenType("lparen");
    public static TokenType rparen = new TokenType("rparen");
    public static TokenType assignment = new TokenType("assignment");
    public static TokenType aSemicolon = new TokenType("aSemicolon");
    public static TokenType aString = new TokenType("aString");
    public static TokenType aBoolean = new TokenType("aBoolean");

    private String name;

    private TokenType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
