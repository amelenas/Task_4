package by.stepanovichalena.library.service.util;

import by.stepanovichalena.library.service.exception.ServiceException;

public interface BookService {
    String addBook(String request) throws ServiceException;

    String find(String request);

    String update(String request) throws ServiceException;

    String delete(String request) throws ServiceException;

}
