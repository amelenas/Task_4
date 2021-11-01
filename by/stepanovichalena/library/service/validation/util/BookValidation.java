package by.stepanovichalena.library.service.validation.util;

import by.stepanovichalena.library.entity.Book;

public interface BookValidation {

    Book validateRead(String book);

    Book validateCreate(String book);

    Book validateUpdate(String book);

    int validateDelete(String id);

}
