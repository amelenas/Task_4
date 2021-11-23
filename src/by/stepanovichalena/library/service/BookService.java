package by.stepanovichalena.library.service;

import by.stepanovichalena.library.dao.BookDAO;
import by.stepanovichalena.library.service.exception.ServiceException;
import by.stepanovichalena.library.service.validation.util.BookValidation;

public interface BookService {

    String addBook(String request) throws ServiceException;

    String find(String request);

    String showAll() throws ServiceException;

    String delete(String request) throws ServiceException;

    void connectBookDAO(BookDAO bookDAO);

    void setBookValidation(BookValidation bookValidation);
}
