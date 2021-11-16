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
    private final static String CHANGE = "Book changed";
    private final static String FIND = "Book found";
    private final static String SUCCESS = "Request completed";
    private final static String ERROR = "Error while";
    private final static String BOOK_NOT_FOUND = "Book not found";

    public final static String DIVIDER = "/";

    private BookFactory bookFactory;
    private BookLogic bookLogic;
    private BookValidation bookValidation;

    public BookServiceImpl() throws ServiceException {
        try {
            bookFactory = BookFactory.getInstance();
        } catch (LogicException e) {
            throw new ServiceException("Exception in BookServiceImpl while LibraryFactory.getInstance()", e);
        }
        bookLogic = bookFactory.getBookDAO();
        bookValidation = BookValidator.getInstance();
    }


    @Override
    public String addBook(String request) throws ServiceException {
        boolean needUpdate = false;
        Book book = bookValidation.validateCreate(request);
        try {
           if (isCorrectAccessLevel(AccessLevel.ADMIN)) {
                needUpdate = bookLogic.create(book);
                System.out.println(needUpdate);
            }
        } catch (LogicException e) {
            throw new ServiceException(e);
        }
        return result(ADD, needUpdate);

    }

    @Override
    public String find(String request) {
        StringBuilder res = new StringBuilder();
        Book book = bookValidation.validateSearch(request);
        Collection<Book> lib = bookLogic.find(book);
        if(lib.size()>0){
        res = new StringBuilder(FIND + "\n");
        for (Book b : lib) {
            String line = String.join(DIVIDER, String.valueOf(b.getId()), b.getTitle(), b.getAuthorsName());
            res.append(line).append("\n");
        }
        }else {
            res.append(BOOK_NOT_FOUND);
        }
        return res.toString();
    }

    @Override
    public String update(String request) throws ServiceException {
        boolean needUpdate = false;
        try {
            if (isCorrectAccessLevel(AccessLevel.ADMIN)) {
                Book book = bookValidation.validateUpdate(request);
                needUpdate = bookLogic.update(book);
            }
        } catch (LogicException e) {
           throw new ServiceException();
        }
        return result(CHANGE, needUpdate);

    }

    @Override
    public String delete(String request) throws ServiceException {
        boolean needUpdate = false;
        try {
             if (isCorrectAccessLevel(AccessLevel.ADMIN)) {
                int id = bookValidation.validateDelete(request);
                needUpdate = bookLogic.delete(id);
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

    private boolean isCorrectAccessLevel (AccessLevel ... levels) {
        AccessLevel level = bookLogic.getLevel();
        for (AccessLevel accessLevel : levels) {
            if (level.equals(accessLevel)) {
                return true;
            }
        }
        return false;
    }
}
