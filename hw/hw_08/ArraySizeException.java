package hw_08;

public class ArraySizeException extends RuntimeException {
    public ArraySizeException(String message) {
        super(message);
    }

    public ArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
