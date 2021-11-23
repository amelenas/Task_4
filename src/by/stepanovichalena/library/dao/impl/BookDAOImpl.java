package by.stepanovichalena.library.dao.impl;

import by.stepanovichalena.library.source.BookSource;
import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.service.UserHolder;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class BookDAOImpl implements BookDAO {
    private static final Logger LOGGER = LogManager.getLogger(BookDAOImpl.class);
    private BookSource bookSource;
    private static final Map<Integer, Book> books = new TreeMap<>();

    public BookDAOImpl() {
    }

    public void connectBookSource(BookSource bookSource) throws LibraryDAOException {
        this.bookSource = bookSource;
        download();
    }

    @Override
    public boolean addBook(Book book) throws LibraryDAOException {
        if (book != null && !books.containsValue(book)) {
            int id = books.size() + 2;
            book.setId(id);
            books.put(id, book);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Book book) throws LibraryDAOException {
        Collection<Book> tempBooks = find(book);
        for (Book b : tempBooks) {
            if (b.getTitle().equals(book.getTitle()) && b.getAuthorsName().equals(book.getAuthorsName())) {
                books.remove(b.getId());
            }
            upload();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Book> downloadAll() throws LibraryDAOException {
        download();
        return books.values();
    }

    @Override
    public Collection<Book> find(Book book) {
        String title = book.getTitle();
        String author = book.getAuthorsName();
        Set<Book> resultByTitle = (Set<Book>) findLibByTitle(title);
        Set<Book> resultByAuthor = (Set<Book>) findByAuthor(author);
        resultByAuthor.addAll(resultByTitle);
        return resultByAuthor;
    }

    public AccessLevel getLevel() {
        return UserHolder.getUsersLevel();
    }

    private Collection<Book> findLibByTitle(String title) {
        Set<Book> result = new HashSet<>();
        for (Book book : books.values()) {
            if (title.length() > 0 && book.getTitle() != null && book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    private Collection<Book> findByAuthor(String author) {
        Set<Book> result = new HashSet<>();
        for (Book book : books.values()) {
            if (author.length() > 0 && book.getAuthorsName() != null && book.getAuthorsName().contains(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public void download() throws LibraryDAOException {
        try {
            books.putAll(bookSource.readAll());
        } catch (DAOException e) {
            throw new LibraryDAOException("File reading error", e);
        }
    }

    private void upload() throws LibraryDAOException {
        try {
            if (bookSource != null) {
                bookSource.writeAll(books.values());
                LOGGER.info(books.values());
            }
        } catch (DAOException e) {
            throw new LibraryDAOException("File writing error", e);
        }
    }
}