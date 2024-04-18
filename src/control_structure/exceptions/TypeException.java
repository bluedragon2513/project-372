package control_structure.exceptions;

public class TypeException extends Exception {
    public TypeException(String message) {
        super("Type Exception: " + message);
    }
}
