package hw_08;

public class ArrayDataException extends NumberFormatException {

    public ArrayDataException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
