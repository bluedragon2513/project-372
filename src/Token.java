//represents individual tokens in source code
public class Token {
        private TokenType type;
        private String name;

        public Token(TokenType type, String name) {
            this.type = type;
            this.name = name;
        }

        public TokenType getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return type + "('" + name + "')";
        }
    }
