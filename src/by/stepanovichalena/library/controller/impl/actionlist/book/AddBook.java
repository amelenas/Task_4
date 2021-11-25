package by.stepanovichalena.library.controller.impl.actionlist.book;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.BookValidation;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.impl.BookValidator;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import by.stepanovichalena.library.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddBook implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddBook.class);
    private static final String ADDED = " added ";
    private static final String NOT_ADDED = " book not added ";
    private static final String INCORRECT_ACCESS_LEVEL = " incorrect access level ";
    private BookValidation bookValidation = new BookValidator();
    private BookService bookService;
    private String[] requestParameters;

    public AddBook(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() throws ControllerException {
        String title = requestParameters[0];
        String authorsName = requestParameters[1];
        StringBuilder response = new StringBuilder();
        if (!isAccessLevelAdmin()) {
            response.append(INCORRECT_ACCESS_LEVEL).append(NOT_ADDED);
        } else {
            try {
                bookValidation.isBookDataValid(title, authorsName);
            } catch (ControllerException e) {
                response.append(e.getMessage()).append(NOT_ADDED);
            }
            Book book = new Book(-1, title, authorsName);
            try {
                bookService.addBook(book);
            } catch (ServiceException e) {
                LOGGER.warn("Exception while adding a book", e);
                throw new ControllerException("Exception while adding a book");
            }
            response.append(book.getTitle()).append(" ").append(book.getAuthorsName()).append(ADDED);
        }
        return response.toString();
    }

    private boolean isAccessLevelAdmin() {
        return UserHolder.getUsersLevel().equals(AccessLevel.ADMIN);
    }

    @Override
    public void request(String... requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void setBookValidation(BookValidation bookValidation) {
        this.bookValidation = bookValidation;
    }
}

