package by.stepanovichalena.library.dao.util;

import by.stepanovichalena.library.dao.exception.DAOException;
import by.stepanovichalena.library.entity.Book;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface BookDAO {
    Map<Integer, Book> readAll() throws IOException, DAOException;

    void writeAll(Collection<Book> books) throws IOException, DAOException;

}
