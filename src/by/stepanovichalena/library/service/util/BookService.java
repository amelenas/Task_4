package by.stepanovichalena.library.service.util;

import by.stepanovichalena.library.service.exception.ServiceException;

public interface BookService {

    String addBook(String firstRequest, String secondRequest) throws ServiceException;

    String find(String firstRequest, String secondRequest);

    String showAll() throws ServiceException;

    String delete(String firstRequest, String secondRequest) throws ServiceException;

}
