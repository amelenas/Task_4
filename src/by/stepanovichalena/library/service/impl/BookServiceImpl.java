package by.stepanovichalena.library.service.impl;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.validation.BookValidator;
import by.stepanovichalena.library.service.validation.util.BookValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collection;

public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

    private final static String ADD = "Book added";
    private final static String DELETE = "Book deleted";
    private final static String FIND = "Book found";
    private final static String SUCCESS = "Request completed";
    private final static String ERROR = "Error while";
    private final static String BOOK_NOT_FOUND = "Book not found";
    private final static String DELIMITER = "/";

    private BookDAO bookDAO;
    private BookValidation bookValidation;

    public BookServiceImpl() {
    }

    @Override
    public void connectBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public String addBook(String line) throws ServiceException {
        boolean needUpdate = false;
        Book book = new Book(-1, "", "");
        if (line != null) {
            String[] array = line.split(DELIMITER);
            if (array.length > 1) {
                if (bookValidation.isBookDataValid(array[0], array[1])) {
                    book.setTitle(array[0]);
                    book.setAuthorsName(array[1]);
                }
            }
        }
        try {
            if (isCorrectAccessLevel(AccessLevel.ADMIN)) {
                needUpdate = bookDAO.addBook(book);
            }
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception while adding the book ", e);
            throw new ServiceException("Exception while adding the book ", e);
        }
        return result(ADD, needUpdate);
    }

    @Override
    public String find(String line) {
        StringBuilder res = new StringBuilder();
        Book book = new Book(-1, "", "");
        if (line != null) {
            String[] array = line.split(DELIMITER);
            LOGGER.info(Arrays.toString(array));
            if (array.length == 1) {
                if (bookValidation.isTitleValid(array[0])) {
                    book.setTitle(array[0]);
                }
            }
            if (array.length > 1) {
                if (bookValidation.isAuthorsNameValid(array[1])) {
                    book.setTitle(array[0]);
                    book.setAuthorsName(array[1]);
                }
            }
        }
        Collection<Book> lib = bookDAO.find(book);
        if (lib.size() > 0) {
            res = new StringBuilder(FIND + "\n");
            for (Book b : lib) {
                String bookDetail = String.join(" ", String.valueOf(b.getId()), b.getTitle(), b.getAuthorsName());
                res.append(bookDetail).append("\n");
            }
        } else {
            res.append(BOOK_NOT_FOUND);
        }
        return res.toString();
    }

    @Override
    public String showAll() throws ServiceException {
        StringBuilder result = new StringBuilder();
        try {
            Collection<Book> books = bookDAO.downloadAll();
            for (Book book : books) {
                String line = String.join(" ", String.valueOf(book.getId()), book.getTitle(), book.getAuthorsName());
                result.append(line).append("\n");
            }
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in BookServiceImpl while loading the list ", e);
            throw new ServiceException("Exception in BookServiceImpl while loading the list ", e);
        }
        return result.toString();
    }

    @Override
    public String delete(String request) throws ServiceException {
        boolean needUpdate = false;
        Book book = new Book();
        if (request != null) {
            try {
                String[] array = request.split(DELIMITER);
                LOGGER.info(Arrays.toString(array));
                if (isCorrectAccessLevel(AccessLevel.ADMIN) && array.length > 1) {
                    if (bookValidation.isBookDataValid(array[0], array[1])) {
                        book = new Book(-1, array[0], array[1]);
                    }
                    needUpdate = bookDAO.delete(book);
                }
            } catch (LibraryDAOException e) {
                LOGGER.error("Exception in BookServiceImpl while deleting the book ", e);
                throw new ServiceException("Exception in BookServiceImpl while deleting the book ", e);
            }
        }
        return result(DELETE, needUpdate);
    }


    private String result(String command, boolean worked) {
        String res = worked ? SUCCESS : ERROR;
        res = String.join(" ", res, command);
        return res;
    }

    private boolean isCorrectAccessLevel(AccessLevel... levels) {
        AccessLevel level = bookDAO.getLevel();
        for (AccessLevel accessLevel : levels) {
            if (level.equals(accessLevel)) {
                return true;
            }
        }
        return false;
    }

    public void setBookValidation(BookValidation bookValidation) {
        this.bookValidation = bookValidation;
    }
}
