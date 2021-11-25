package by.stepanovichalena.library.dao.source.exception;

public class ReaderDAOException extends Exception{

    public ReaderDAOException() {
        super();
    }

    public ReaderDAOException(String message) {
        super(message);
    }

    public ReaderDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderDAOException(Throwable cause) {
        super(cause);
    }
}
