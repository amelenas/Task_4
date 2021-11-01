package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.logic.LibraryFactory;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.logic.util.BookLogic;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.BookService;
import by.stepanovichalena.library.service.validation.BookValidator;
import by.stepanovichalena.library.service.validation.util.BookValidation;

import java.util.Collection;

public class BookServiceImpl implements BookService {
    private final static String ADDED = "Book added";
    private final static String DELETED = "Book deleted";
    private final static String CHANGED = "Book changed";
    private final static String FOUNDED = "Book found";
    private final static String SUCCESS = "Request completed";
    private final static String ERROR = "Error";
    public final static String DIVIDER = "/";

    LibraryFactory libraryFactory;
    BookLogic bookLogic;
    BookValidation bookValidation;

    public BookServiceImpl() throws LogicException {
        libraryFactory = LibraryFactory.getInstance();
        bookLogic = libraryFactory.getBookDAO();
        bookValidation = BookValidator.getInstance();
    }


    @Override
    public String addBook(String request) throws ServiceException {
        boolean needUpdate = false;
        Book book = bookValidation.validateCreate(request);
        try {
           if (isSameAccessLevel(AccessLevel.ADMIN)) {
                needUpdate = bookLogic.create(book);
                System.out.println(needUpdate);
            }
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        return result(ADDED, needUpdate);

    }

    @Override
    public String find(String request) {
        StringBuilder res;
        Book book = bookValidation.validateRead(request);
        Collection<Book> lib = bookLogic.find(book);
        res = new StringBuilder(FOUNDED + "\n");
        for (Book b : lib) {
            String line = String.join(DIVIDER, String.valueOf(b.getId()), b.getTitle(), b.getAuthorsName());
            res.append(line).append("\n");
        }
        return res.toString();
    }

    @Override
    public String update(String request) throws ServiceException {
        boolean needUpdate = false;
        try {
            if (isSameAccessLevel(AccessLevel.ADMIN)) {
                Book book = bookValidation.validateUpdate(request);
                needUpdate = bookLogic.update(book);
            }
        } catch (LogicException e) {
           throw new ServiceException();
        }
        return result(CHANGED, needUpdate);

    }

    @Override
    public String delete(String request) throws ServiceException {
        boolean needUpdate = false;
        try {
             if (isSameAccessLevel(AccessLevel.ADMIN)) {
                int id = bookValidation.validateDelete(request);
                needUpdate = bookLogic.delete(id);
            }
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        return result(DELETED, needUpdate);
    }

    private String result(String command, boolean worked) {
        String res = worked ? SUCCESS : ERROR;
        res = String.join(" ", res, command);
        return res;
    }

    private boolean isSameAccessLevel(AccessLevel ... levels) {
        AccessLevel level = bookLogic.getLevel();
        for (AccessLevel accessLevel : levels) {
            if (level.equals(accessLevel)) {
                return true;
            }
        }
        return false;
    }
}
