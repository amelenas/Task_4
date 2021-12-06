package by.stepanovichalena.library.util.exeption;

public class LibraryUtilException extends Exception{
    public LibraryUtilException() {
        super();
    }

    public LibraryUtilException(String message) {
        super(message);
    }

    public LibraryUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryUtilException(Throwable cause) {
        super(cause);
    }
}
