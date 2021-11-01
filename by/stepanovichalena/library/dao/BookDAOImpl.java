package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.dao.util.BookDAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BookDAOImpl implements BookDAO {
    public final static String PATH_TO_BOOK_LIST = "./resources/register of books.txt";
    public final static String DIVIDER = "/";

    private BookDAOImpl() {
    }

    private static class BookSourceActionHolder {
        public static final BookDAOImpl HOLDER_INSTANCE = new BookDAOImpl();
    }

    public static BookDAOImpl getInstance() {
        return BookSourceActionHolder.HOLDER_INSTANCE;
    }

    @Override
    public Map<Integer, Book> readAll() throws DAOException {
        Map<Integer, Book> books = new TreeMap<>();
        List<String> textLines;
        try {
            textLines = Files.readAllLines(Paths.get(PATH_TO_BOOK_LIST));
        } catch (IOException e) {
            throw new DAOException("Can't read file", e);
        }
        for (String line : textLines) {
            Book book = parse(line);
            if (book != null) {
                books.put(book.getId(), book);
            }
        }
        return books;
    }

    @Override
    public void writeAll(Collection<Book> books) throws DAOException {
        try (FileOutputStream writer = new FileOutputStream(PATH_TO_BOOK_LIST, false)) {
            for (Book book : books) {
                String str = String.join(DIVIDER, String.valueOf(book.getId()), book.getTitle(), book.getAuthorsName(), "\n");
                writer.write(str.getBytes());
            }
        } catch (IOException e) {
            throw new DAOException("Can't write file", e);
        }
    }

        private Book parse(String line) {
        Book book = null;
        if (line != null || !line.equals("")) {
            String[] array = line.split(DIVIDER);
            if (array.length == 3) {
                book = new Book(Integer.parseInt(array[0].trim()), array[1].trim(), array[2].trim());
            }
        }
        return book;
    }
}
