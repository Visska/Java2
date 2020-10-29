package java_2_homework_2;

public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException() {
        super();
    }

    public MyArrayDataException(String message) {
        super(message);
    }

    public MyArrayDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
