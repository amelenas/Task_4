package by.stepanovichalena.library.dao.impl;

import by.stepanovichalena.library.dao.source.LibraryReader;
import by.stepanovichalena.library.dao.source.exception.ReaderDAOException;
import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.dao.source.reader.TXTReader;
import by.stepanovichalena.library.entity.Book;

import java.util.*;

public class BookDAOImpl implements BookDAO {
    private static final String BOOKS_SOURCE_PATH = "books.txt.source.path";
    private String delimiter = "/";
    private LibraryReader bookSource = new TXTReader();
    private Map<Integer, Book> books;


    public BookDAOImpl() {
    }

    @Override
    public boolean create(Book book) throws LibraryDAOException {
        download();
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
        download();
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
    public boolean deleteById(Integer id) throws LibraryDAOException {
        download();
        if (id != null && books.size() >= id) {
            books.remove(id);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public Collection<Book> update() throws LibraryDAOException {
        download();
        return books.values();
    }

    @Override
    public Collection<Book> find(Book book) throws LibraryDAOException {
        download();
        Set<Book> resultByAuthor = new HashSet<>();
        if (book != null) {
            if (book.getTitle() != null || book.getAuthorsName() != null) {
                String title = book.getTitle();
                String author = book.getAuthorsName();
                Set<Book> resultByTitle = (Set<Book>) findLibByTitle(title);
                resultByAuthor = (Set<Book>) findByAuthor(author);
                resultByAuthor.addAll(resultByTitle);
            }
        }
        return resultByAuthor;
    }

    @Override
    public Collection<Book> findByAuthorBook(String authorsName) throws LibraryDAOException {
        download();
        Set<Book> resultByAuthor = new HashSet<>();
        if (authorsName != null) {
            resultByAuthor = (Set<Book>) findByAuthor(authorsName);
        }
        return resultByAuthor;
    }

    @Override
    public Collection<Book> findByTitle(String title) throws LibraryDAOException {
        download();
        Set<Book> resultByTitle = new HashSet<>();
        if (title != null) {
            resultByTitle = (Set<Book>) findLibByTitle(title);
        }
        return resultByTitle;
    }

    private Collection<Book> findLibByTitle(String title) throws LibraryDAOException {
        Set<Book> result = new HashSet<>();
        for (Book book : books.values()) {
            if (book == null) {
                throw new LibraryDAOException("Book data is null");
            }
            if (book.getTitle().length() > 0 && book.getTitle().contains(title)) {
                result.add(book);
            }
        }
        return result;
    }

    private Collection<Book> findByAuthor(String author) {
        Set<Book> result = new HashSet<>();
        for (Book book : books.values()) {
            if (book.getAuthorsName() != null && book.getAuthorsName().contains(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public void download() throws LibraryDAOException {
        try {
            parseStringLines(bookSource.readAll(BOOKS_SOURCE_PATH));
        } catch (ReaderDAOException e) {
            throw new LibraryDAOException("File reading error", e);
        }
    }

    private void upload() throws LibraryDAOException {
        Collection<String> result = new ArrayList<>();
        try {
            for (Book book : books.values()) {
                StringBuilder line = new StringBuilder();
                result.add(line.append(book.getId()).append(delimiter).append(book.getTitle()).append(delimiter).append(book.getAuthorsName()).append("\n").toString());
                bookSource.write(BOOKS_SOURCE_PATH, result, false);
            }
        } catch (ReaderDAOException e) {
            throw new LibraryDAOException("File writing error", e);
        }
    }

    private void parseStringLines(List<String> textLines) {
        books = new TreeMap<>();
        for (String line : textLines) {
            Book book = parse(line);
            books.put(book.getId(), book);
        }
    }

    private Book parse(String line) {
        Book book = new Book();
        if (line != null) {
            String[] array = line.split(delimiter);
            if (array.length > 2) {
                book.setId(Integer.parseInt(array[0].trim()));
                book.setTitle(array[1].trim());
                book.setAuthorsName(array[2].trim());
            }
        }
        return book;
    }

    public void setBookSource(LibraryReader bookSource) {
        this.bookSource = bookSource;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}