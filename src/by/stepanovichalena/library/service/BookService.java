package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.exception.ServiceException;

import java.util.Collection;

public interface BookService {

    boolean addBook(Book book) throws ServiceException;

    public Collection<Book> find(Book book) throws ServiceException;

    public Collection <Book> showAll() throws ServiceException;

    public boolean delete(Book book) throws ServiceException;

}
