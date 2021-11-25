package by.stepanovichalena.library.service.impl;

import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);
    private BookDAO bookDAO;

    public BookServiceImpl() {
    }

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean addBook(Book book) throws ServiceException {
        boolean result;
        if (book == null){
            return false;
        }
        try {
            result = bookDAO.create(book);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception while adding the book ", e);
            throw new ServiceException("Exception while adding the book ", e);
        }
        return result;
    }

    @Override
    public Collection<Book> find(Book book) throws ServiceException {
        try {
            return bookDAO.find(book);
        } catch (LibraryDAOException e) {
            throw new ServiceException("Exception in BookServiceImpl while searching file", e);
        }
    }

    @Override
    public Collection<Book> showAll() throws ServiceException {
        Collection<Book> books;
        try {
            books = bookDAO.update();
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in BookServiceImpl while loading the list ", e);
            throw new ServiceException("Exception in BookServiceImpl while loading the list ", e);
        }
        return books;
    }

    @Override
    public boolean delete(Book book) throws ServiceException {
        boolean result;
        try {
            result = bookDAO.delete(book);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in BookServiceImpl while deleting the book ", e);
            throw new ServiceException("Exception in BookServiceImpl while deleting the book ", e);
        }
        return result;
    }
}
