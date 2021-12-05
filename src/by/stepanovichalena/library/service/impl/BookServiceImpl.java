package by.stepanovichalena.library.service.impl;

import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.service.BookService;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.validation.BookValidation;
import by.stepanovichalena.library.validation.impl.BookValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class BookServiceImpl implements BookService {
    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);
    private BookValidation bookValidation = new BookValidator();
    private BookDAO bookDAO;

    public BookServiceImpl() {
    }

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean addBook(Book book) throws ServiceException {
        boolean result = false;
        if (book == null) return false;
        if (bookValidation.isBookDataValid(book.getAuthorsName(), book.getTitle())) {
            try {
                result = bookDAO.create(book);
            } catch (LibraryDAOException e) {
                LOGGER.error("Exception while adding the book ", e);
                throw new ServiceException("Exception while adding the book ", e);
            }
        }
        return result;
    }

    @Override
    public Collection<Book> find(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Incorrect data");
        }
        if (!bookValidation.isBookDataValid(book.getAuthorsName(), book.getTitle())) {
            throw new ServiceException("Incorrect data");
        }
        try {
            return bookDAO.find(book);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in BookServiceImpl while searching file", e);
            throw new ServiceException("Exception in BookServiceImpl while searching file", e);
        }
    }

    @Override
    public Collection<Book> findByAuthor(String authorsName) throws ServiceException {
        if (!bookValidation.isAuthorsNameValid(authorsName)) {
            throw new ServiceException("Incorrect data");
        }
        try {
            return bookDAO.findByAuthorBook(authorsName);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in BookServiceImpl while searching file", e);
            throw new ServiceException("Exception in BookServiceImpl while searching file", e);
        }
    }

    @Override
    public Collection<Book> findByTitle(String title) throws ServiceException {
        if (!bookValidation.isTitleValid(title)) {
            throw new ServiceException("Incorrect data");
        }
        try {
            return bookDAO.findByTitle(title);
        } catch (LibraryDAOException e) {
            LOGGER.error("Exception in BookServiceImpl while searching file", e);
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
        boolean result = false;
        if (book != null && bookValidation.isBookDataValid(book.getAuthorsName(), book.getTitle())) {
            try {
                result = bookDAO.delete(book);
            } catch (LibraryDAOException e) {
                LOGGER.error("Exception in BookServiceImpl while deleting the book ", e);
                throw new ServiceException("Exception in BookServiceImpl while deleting the book ", e);
            }
        }
        return result;
    }

    @Override
    public boolean deleteBookById(Integer id) throws ServiceException {
        boolean result = false;
        if (id != null && bookValidation.isIdValid(id.toString())) {
            try {
                result = bookDAO.deleteById(id);
                LOGGER.info(result);
            } catch (LibraryDAOException e) {
                LOGGER.error("Exception in BookServiceImpl while deleting the book ", e);
                throw new ServiceException("Exception in BookServiceImpl while deleting the book ", e);
            }
        }
        return result;
    }

    public void setBookValidation(BookValidation bookValidation) {
        this.bookValidation = bookValidation;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
