package by.stepanovichalena.library.controller.impl.actionlist.book;

import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.validation.BookValidation;
import by.stepanovichalena.library.validation.impl.BookValidator;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import by.stepanovichalena.library.service.BookService;

public class AddBook implements Command {
    private static final String ADDED = " added ";
    private static final String NOT_ADDED = " book not added ";
    private static final String INCORRECT_ACCESS_LEVEL = " incorrect access level ";
    private static final String INCORRECT_BOOK_DATA = " incorrect book data ";
    private BookValidation bookValidation = new BookValidator();
    private BookService bookService;
    private String[] requestParameters;

    public AddBook(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactoryImpl.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() throws ControllerException {
        boolean result = false;
        StringBuilder response = new StringBuilder();
        Book book;
        if (requestParameters != null) {
            String title = requestParameters[0];
            String authorsName = requestParameters[1];
            if (!isAccessLevelAdmin()) {
                response.append(INCORRECT_ACCESS_LEVEL);
            }
            if (!bookValidation.isBookDataValid(title, authorsName)) {
                response.append(INCORRECT_BOOK_DATA);
            } else {
                try {
                    book = new Book(-1, title, authorsName);
                    result = bookService.addBook(book);
                } catch (ServiceException e) {
                    response.append(e.getMessage());
                }
            }
            response.append(result ? ADDED : NOT_ADDED);
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

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}

