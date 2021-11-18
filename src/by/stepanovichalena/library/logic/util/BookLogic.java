package by.stepanovichalena.library.logic.util;

import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.entity.AccessLevel;

import java.util.Collection;

public interface BookLogic {

    boolean addBook(Book book) throws LogicException;

    boolean delete(Book book) throws LogicException;

    Collection<Book> downloadAll() throws LogicException;

    Collection<Book> find(Book book);

    AccessLevel getLevel();
}
