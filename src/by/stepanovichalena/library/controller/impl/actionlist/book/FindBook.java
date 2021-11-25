package by.stepanovichalena.library.controller.impl.actionlist.book;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.BookValidation;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.impl.BookValidator;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class FindBook implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindBook.class);
    private static final String NOT_FOUND = " book not found ";
    private static final String FOUND = " found book(s) ";
    private BookValidation bookValidation = new BookValidator();
    private BookService bookService;
    private String[] requestParameters;

    public FindBook(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() throws ControllerException {
        StringBuilder res = new StringBuilder();
        Book book = new Book();
        String title = requestParameters[0];
        String authorsName = requestParameters[1];
        StringBuilder response = new StringBuilder();
        try {
            bookValidation.isTitleValid(title);
            book.setTitle(title);
        } catch (ControllerException e) {
            response.append(NOT_FOUND);
        }
        try {
            bookValidation.isAuthorsNameValid(authorsName);
            book.setAuthorsName(authorsName);
        } catch (ControllerException e) {
            response.append(e.getMessage());
        }
        Collection<Book> lib = null;
        try {
            lib = bookService.find(book);
        } catch (ServiceException e) {
            LOGGER.warn("Exception in command find book", e);
            response.append(e.getMessage());
        }
        if (lib != null && lib.size() > 0) {
            res = new StringBuilder(FOUND + "\n");
            for (Book b : lib) {
                String bookDetail = String.join(" ", String.valueOf(b.getId()), b.getTitle(), b.getAuthorsName());
                res.append(bookDetail).append("\n");
            }
        } else {
            res.append(NOT_FOUND);
        }
        return res.toString();
    }

    @Override
    public void request(String... requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setBookValidation(BookValidation bookValidation) {
        this.bookValidation = bookValidation;
    }


}
