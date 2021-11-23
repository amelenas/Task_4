package by.stepanovichalena.library.dao;

import by.stepanovichalena.library.dao.exception.LibraryDAOException;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.entity.AccessLevel;
import by.stepanovichalena.library.source.BookSource;

import java.util.Collection;

public interface BookDAO {

    boolean addBook(Book book) throws LibraryDAOException;

    boolean delete(Book book) throws LibraryDAOException;

    Collection<Book> downloadAll() throws LibraryDAOException;

    Collection<Book> find(Book book);

    AccessLevel getLevel();

    void connectBookSource (BookSource bookSource) throws LibraryDAOException;
}
