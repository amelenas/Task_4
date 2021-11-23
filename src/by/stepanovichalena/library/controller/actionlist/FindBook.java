package by.stepanovichalena.library.controller.actionlist;

import by.stepanovichalena.library.controller.util.Command;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.factory.BookDAOFactory;
import by.stepanovichalena.library.service.factory.ServiceBookFactory;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.source.BookSource;
import by.stepanovichalena.library.source.impl.BookSourceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindBook implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindBook.class);

    @Override
    public String execute(String request) throws ServiceException {
        BookSource bookSource = new BookSourceImpl();
        BookDAO bookDAO = BookDAOFactory.getInstance();
        try {
            bookDAO.connectBookSource(bookSource);
        } catch (LibraryDAOException e) {
            LOGGER.warn(e.getMessage());
            throw new ServiceException("Exception while FindBook", e);
        }
        BookService bookService = ServiceBookFactory.getInstance();
        bookService.connectBookDAO(bookDAO);
        return bookService.find(request);
    }
}
