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
    public boolean addBook(Book book) throws LogicException {
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
    public boolean delete(Book book) throws LogicException {
        Collection<Book> tempBooks = find(book);
        for (Book b : tempBooks){
            if (b.getTitle().equals(book.getTitle()) && b.getAuthorsName().equals(book.getAuthorsName())){
            books.remove(b.getId());}
            upload();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Book> downloadAll() throws LogicException {
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
            if (title.length() > 0 && book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    private Collection<Book> findByAuthor(String author) {
        Set<Book> result = new HashSet<>();
        for (Book book : books.values()) {
            if (author.length() >0 && book.getAuthorsName().contains(author)) {
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