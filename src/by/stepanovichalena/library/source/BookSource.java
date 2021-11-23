package by.stepanovichalena.library.source;

import by.stepanovichalena.library.source.exception.DAOException;
import by.stepanovichalena.library.entity.Book;

import java.util.Collection;
import java.util.Map;

public interface BookSource {
    Map<Integer, Book> readAll() throws DAOException;

    void writeAll(Collection<Book> books) throws DAOException;

}
