package by.stepanovichalena.library.source.impl;

import by.stepanovichalena.library.source.BookSource;
import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BookSourceImpl implements BookSource {
    private static final Logger LOGGER = LogManager.getLogger(BookSourceImpl.class);
    private static final String PATH_TO_BOOK_LIST = "resource/register of books.txt";
    private final static String DELIMITER = "/";

    public BookSourceImpl() {
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
                String str = String.join(DELIMITER, String.valueOf(book.getId()), book.getTitle(), book.getAuthorsName(), "\n");
                writer.write(str.getBytes());
            }
        } catch (IOException e) {
            LOGGER.error("Exception in BookSourceImpl while trying to write ", e);
            throw new DAOException("Can't write file", e);
        }
    }

    private Book parse(String line) {
        Book book = new Book();
        if (line == null) {
            return book;
        }
        String[] array = line.split(DELIMITER);
        if (array.length == 3) {
            book = new Book(Integer.parseInt(array[0].trim()), array[1].trim(), array[2].trim());
        }
        return book;
    }
}
