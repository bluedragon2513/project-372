package control_structure.exceptions;

public class TokenException extends Exception {
    public TokenException(String message) {
        super("Token Exception: " + message);
    }
}
