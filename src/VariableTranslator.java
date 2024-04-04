import java.util.ArrayList;

public class VariableTranslator {
    public String variableTranslator(ArrayList<Token> tokens) {
        String result = "";

        for (Token curr : tokens) {
            TokenType type = curr.getType();
            String tokenName = curr.getName();

            if (type == TokenType.integer || type == TokenType.variable ||
                    type == TokenType.aString || type == TokenType.aBoolean) {
                result += tokenName + " ";
            }

            else if (type == TokenType.plus || type == TokenType.minus || type == TokenType.mult ||
                    type == TokenType.div || type == TokenType.mod) {
                result += tokenName + " ";
            }
            else if (type == TokenType.lparen || type == TokenType.rparen) {
                result += tokenName;
            }

            else if (type == TokenType.assignment) {
                result += "= ";
            }
            else if (type == TokenType.aSemicolon) {
                result += ";";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.integer, "int"));
        tokens.add(new Token(TokenType.variable, "x"));
        tokens.add(new Token(TokenType.assignment, "="));
        tokens.add(new Token(TokenType.integer, "5"));
        tokens.add(new Token(TokenType.aSemicolon, ";"));

        VariableTranslator translator = new VariableTranslator();
        String translatedCode = translator.variableTranslator(tokens);
        System.out.println(translatedCode);
    }
}
