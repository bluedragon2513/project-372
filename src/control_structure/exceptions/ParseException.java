package control_structure.exceptions;

public class ParseException extends Exception {
    public ParseException(String message) {
        super("Parse Exception: " + message);
    }
}
