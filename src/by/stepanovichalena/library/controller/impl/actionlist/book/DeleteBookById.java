package by.stepanovichalena.library.controller.impl.actionlist.book;

import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.UserHolder;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.BookValidation;
import by.stepanovichalena.library.controller.impl.actionlist.book.validator.impl.BookValidator;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteBookById implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteBookById.class);
    private static final String NOT_DELETED = " book not deleted ";
    private static final String DELETED = " book deleted ";
    private static final String INCORRECT_ACCESS_LEVEL = " incorrect access level ";
    private static final String INCORRECT_VALUE = " incorrect value ";
    private BookValidation bookValidation = new BookValidator();
    private BookService bookService;
    private String[] requestParameters;

    public DeleteBookById(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactory.getInstance().getBookService(bookDAO);
    }

    @Override
    public String execute() throws ControllerException {
        boolean result = false;
        StringBuilder response = new StringBuilder();
        if (requestParameters != null) {
            String id = requestParameters[0];
            try {
                if (!bookValidation.isIdValid(id)) {
                    response.append(INCORRECT_VALUE);
                }
                if (!isAccessLevelAdmin()) {
                    response.append(INCORRECT_ACCESS_LEVEL);
                } else {
                    result = bookService.deleteBookById(Integer.parseInt(id));
                    LOGGER.info(result);
                }
            } catch (ControllerException | ServiceException e) {
                throw new ControllerException("Exception in command delete by id ", e);
            }
        }
        response.append(result ? DELETED : NOT_DELETED);
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
