package by.stepanovichalena.library.logic;

import by.stepanovichalena.library.dao.BookDAOImpl;
import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.logic.util.BookLogic;

import java.util.*;

public class BookLogicImpl implements BookLogic {
    private BookDAOImpl bookDAO;
    private static final Map<Integer, Book> books = new TreeMap<>();

    public BookLogicImpl() throws LogicException {
        bookDAO = BookDAOImpl.getInstance();
        download();
    }


    @Override
    public boolean create(Book book) throws LogicException {
        if (book != null && !books.containsValue(book)) {
            int id = books.size() + 1;
            book.setId(id);
            books.put(id, book);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws LogicException {
        if (books.containsKey(id)) {
            books.remove(id);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Book book) throws LogicException {
        if (book == null) {
            return false;
        }
        if (books.containsKey(book.getId())) {
            books.replace(book.getId(), book);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Book> find(Book book) {
        String title = book.getTitle();
        String author = book.getAuthorsName();
        Set<Book> result = new HashSet<>();
        Set<Book> resultByTitle = (Set<Book>) findLibByTitle(title);
        Set<Book> resultByAuthor = (Set<Book>) findLibByAuthor(author);
        for (Book b : resultByTitle) {
            if (resultByAuthor.contains(b)) {
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public AccessLevel getLevel() {
        return UserFactory.getUsersLevel();
    }

    private Collection<Book> findLibByTitle(String title) {
        Set<Book> result = new HashSet<>();
        if (title == null || title.equals("")) {
            result.addAll(books.values());
            return result;
        }
        for (Book book : books.values()) {
            if (book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    private Collection<Book> findLibByAuthor(String author) {
        Set<Book> result = new HashSet<>();
        if (author == null || author.equals("")) {
            result.addAll(books.values());
            return result;
        }
        for (Book book : books.values()) {
            if (book.getAuthorsName().contains(author)) {
                result.add(book);
            }
        }
        return result;
    }

    private void download() throws LogicException {
        try {
            books.putAll(bookDAO.readAll());
        } catch (DAOException e) {
            throw new LogicException("File reading error", e);
        }
    }

    private void upload() throws LogicException {
        try {
            bookDAO.writeAll(books.values());
        } catch (DAOException e) {
            throw new LogicException("File writing error", e);
        }
    }
}