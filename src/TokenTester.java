public class TokenTester {
    public static void main(String[] args) {
        testTokenType(TokenType.integer, "int");
        testTokenType(TokenType.variable, "variable");
        testTokenType(TokenType.plus, "plus");
        testTokenType(TokenType.minus, "minus");
        testTokenType(TokenType.minus, "beep");
        testTokenType(TokenType.minus, "boop");

    }

    private static void testTokenType(TokenType tokenType, String expectedName) {
        if (tokenType.toString().equals(expectedName)) {
            System.out.println("passed: " + expectedName);
        } else {
            System.out.println("failed: " + expectedName + ", expected " +
                    tokenType.toString() + ", but got: " + expectedName);
        }
    }
}
