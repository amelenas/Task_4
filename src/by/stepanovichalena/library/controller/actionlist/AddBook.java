package by.stepanovichalena.library.controller.actionlist;

import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.service.ServiceFactory;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.util.BookService;

public class AddBook implements Command {

    @Override
    public String execute(String firstRequest, String secondRequest) throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        BookService bookService = factory.getBookService();
        return bookService.addBook(firstRequest, secondRequest);
    }
}

