package by.stepanovichalena.library.service.validation.util;

import by.stepanovichalena.library.entity.Book;

public interface BookValidation {

    Book validateSearch(String book);

    Book validateCreate(String book);

    Book validateUpdate(String book);

    int validateDelete(String id);

}
