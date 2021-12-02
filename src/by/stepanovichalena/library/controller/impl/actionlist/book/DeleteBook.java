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
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import by.stepanovichalena.library.service.BookService;

public class DeleteBook implements Command {
    private static final String NOT_DELETED = " book not deleted ";
    private static final String DELETED = " book deleted ";
    private static final String INCORRECT_ACCESS_LEVEL = " incorrect access level ";
    private BookValidation bookValidation = new BookValidator();
    private BookService bookService;
    private String[] requestParameters;

    public DeleteBook(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactoryImpl.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() {
        boolean result = false;
        String title = requestParameters[0];
        String authorsName = requestParameters[1];
        StringBuilder response = new StringBuilder();
        if (!isAccessLevelAdmin()) {
            response.append(INCORRECT_ACCESS_LEVEL);
        }else {
            try {
                bookValidation.isBookDataValid(title, authorsName);
                Book book = new Book(-1, title, authorsName);
                result = bookService.delete(book);
            } catch (ControllerException | ServiceException e) {
                response.append(e.getMessage());
            }
            response.append(result ? DELETED : NOT_DELETED);
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
