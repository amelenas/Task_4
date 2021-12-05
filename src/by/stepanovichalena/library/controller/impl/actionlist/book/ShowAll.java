package by.stepanovichalena.library.controller.impl.actionlist.book;

import by.stepanovichalena.library.controller.Command;
import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.factory.ServiceLibraryFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class ShowAll implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAll.class);
    private String delimiter = "/";

    private BookService bookService;
    private String[] requestParameters;

    public ShowAll(BookDAO bookDAO) {
        this.bookService = ServiceLibraryFactoryImpl.getInstance().getBookService(bookDAO);
    }

    public ShowAll() {
    }

    @Override
    public String execute() throws ControllerException {
        StringBuilder result = new StringBuilder();
        Collection<Book> books;
        try {
            books = bookService.showAll();
        } catch (ServiceException e) {
            LOGGER.warn("Exception in command show all", e);
            throw new ControllerException("Exception in command show all", e);
        }
        for (Book book : books){
            result.append(book.getId()).append(delimiter).append(book.getTitle()).append(delimiter).append(book.getAuthorsName()).append(delimiter).append("\n");
        }
        return result.toString();
    }

    @Override
    public void request(String... requestParameters) {
     this.requestParameters = requestParameters;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
