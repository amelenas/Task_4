package by.stepanovichalena.library.controller.impl.actionlist.book;

import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.BookValidation;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.impl.BookValidator;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

public class FindBookByTitle implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindBookByTitle.class);
    private static final String NOT_FOUND = " book not found ";
    private static final String FOUND = " found book(s) ";
    private BookValidation bookValidation = new BookValidator();
    private BookService bookService;
    private String[] requestParameters;

    public FindBookByTitle(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() throws ControllerException {
        StringBuilder response = new StringBuilder();
        String title = requestParameters[0];
        Collection<Book> lib = new ArrayList<>();
        try {
            bookValidation.isTitleValid(title);
            lib = bookService.findByTitle(title);
        } catch (ServiceException e) {
            LOGGER.warn("Exception in command find book", e);
            response.append(e.getMessage());
        }
        if (lib != null && lib.size() > 0) {
            response = new StringBuilder(FOUND + "\n");
            for (Book b : lib) {
                String bookDetail = String.join("/", String.valueOf(b.getId()), b.getTitle(), b.getAuthorsName());
                response.append(bookDetail).append("\n");
            }
        } else {
            response.append(NOT_FOUND);
        }
        return response.toString();
    }

    @Override
    public void request(String... requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setBookValidation(BookValidation bookValidation) {
        this.bookValidation = bookValidation;
    }

}
