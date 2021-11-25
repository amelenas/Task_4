package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.dao.source.BookSource;

import java.util.Collection;

public interface BookDAO {

    boolean create(Book book) throws LibraryDAOException;

    Collection<Book> update() throws LibraryDAOException;

    boolean delete(Book book) throws LibraryDAOException;

    Collection<Book> find(Book book) throws LibraryDAOException;

}
