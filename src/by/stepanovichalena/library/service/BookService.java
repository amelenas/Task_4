package by.stepanovichalena.library.service;

import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.exception.ServiceException;

import java.util.Collection;

public interface BookService {

    boolean addBook(Book book) throws ServiceException;

    Collection<Book> find(Book book) throws ServiceException;

    Collection<Book> findByAuthor(String authorsName) throws ServiceException;

    Collection<Book> findByTitle(String title) throws ServiceException;

    Collection <Book> showAll() throws ServiceException;

    boolean delete(Book book) throws ServiceException;

    boolean deleteBookById(Integer id) throws ServiceException;

}
