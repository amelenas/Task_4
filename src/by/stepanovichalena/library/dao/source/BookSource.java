package by.stepanovichalena.library.dao.source;

import by.stepanovichalena.library.dao.source.exception.ReaderDAOException;

import java.util.Collection;
import java.util.List;

public interface BookSource {
    List<String> readAll(String propertyName) throws ReaderDAOException;

    void write(String sourceProperty, Collection<String> users, boolean isAppend) throws ReaderDAOException;

}
