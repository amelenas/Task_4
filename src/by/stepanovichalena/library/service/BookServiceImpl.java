package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.logic.BookFactory;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.BookLogic;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.BookService;
import by.stepanovichalena.library.service.validation.BookValidator;
import by.stepanovichalena.library.service.validation.util.BookValidation;

import java.util.Collection;

public class BookServiceImpl implements BookService {
    private final static String ADD = "Book added";
    private final static String DELETE = "Book deleted";
    private final static String FIND = "Book found";
    private final static String SUCCESS = "Request completed";
    private final static String ERROR = "Error while";
    private final static String BOOK_NOT_FOUND = "Book not found";

    private BookFactory bookFactory;
    private BookLogic bookLogic;
    private BookValidation bookValidation = new BookValidator();

    public BookServiceImpl() throws ServiceException {
        try {
            bookFactory = BookFactory.getInstance();
        } catch (LogicException e) {
            throw new ServiceException("Exception in BookServiceImpl while LibraryFactory.getInstance()", e);
        }
        bookLogic = bookFactory.getBookDAO();
    }


    @Override
    public String addBook(String title, String authorsName) throws ServiceException {
        boolean needUpdate = false;
        Book book = new Book(0,"","");
        if (bookValidation.isBookDataValid(title, authorsName)) {
            book.setTitle(title);
            book.setAuthorsName(authorsName);
        }
        try {
            if (isCorrectAccessLevel(AccessLevel.ADMIN)) {
                needUpdate = bookLogic.addBook(book);
            }
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        return result(ADD, needUpdate);

    }

    @Override
    public String find(String title, String authorsName) {
        StringBuilder res = new StringBuilder();
        Book book = new Book(-1, "", "");
        if (bookValidation.isTitleValid(title)) {
            book.setTitle(title);
        }
        if (bookValidation.isAuthorsNameValid(authorsName)) {
            book.setAuthorsName(authorsName);
        }
        Collection<Book> lib = bookLogic.find(book);
        if (lib.size() > 0) {
            res = new StringBuilder(FIND + "\n");
            for (Book b : lib) {
                String line = String.join(" ", String.valueOf(b.getId()), b.getTitle(), b.getAuthorsName());
                res.append(line).append("\n");
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
            Collection<Book> books = bookLogic.downloadAll();
            for (Book book : books) {
                String line = String.join(" ", String.valueOf(book.getId()), book.getTitle(), book.getAuthorsName());
                result.append(line).append("\n");
            }
        } catch (LogicException e) {
            throw new ServiceException("Exception in BookServiceImpl while while loading the list", e);
        }

        return result.toString();
    }

    @Override
    public String delete(String title, String authorsName) throws ServiceException {
        boolean needUpdate = false;
        Book book = new Book();
        try {
            if (isCorrectAccessLevel(AccessLevel.ADMIN)) {
                if (bookValidation.isBookDataValid(title, authorsName)) {
                    book = new Book(-1, title, authorsName);
                }
                needUpdate = bookLogic.delete(book);
            }
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        return result(DELETE, needUpdate);
    }

    private String result(String command, boolean worked) {
        String res = worked ? SUCCESS : ERROR;
        res = String.join(" ", res, command);
        return res;
    }

    private boolean isCorrectAccessLevel(AccessLevel... levels) {
        AccessLevel level = bookLogic.getLevel();
        for (AccessLevel accessLevel : levels) {
            if (level.equals(accessLevel)) {
                return true;
            }
        }
        return false;
    }
}
