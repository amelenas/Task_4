package by.stepanovichalena.library.logic.util;

import by.stepanovichalena.library.logic.exception.LogicException;
import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.entity.AccessLevel;

import java.util.Collection;

public interface BookLogic {
    boolean create(Book book) throws LogicException;

    boolean delete(int id) throws LogicException;

    boolean update(Book book) throws LogicException;

    Collection<Book> find(Book book);

    AccessLevel getLevel();
}
