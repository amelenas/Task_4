package by.stepanovichalena.library.dao.exception;

public class LibraryDAOException extends Exception{

    public LibraryDAOException() {
        super();
    }

    public LibraryDAOException(String message) {
        super(message);
    }

    public LibraryDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryDAOException(Throwable cause) {
        super(cause);
    }
}
