package by.stepanovichalena.library.service.validation;

import by.stepanovichalena.library.entity.Book;
import by.stepanovichalena.library.service.validation.util.BookValidation;

public class BookValidator implements BookValidation {
    private static final int ID = -1;
    public final static String DIVIDER = " ";

    private BookValidator() {
    }

    private static class BookValidatorHolder {
        public static final BookValidator HOLDER_INSTANCE = new BookValidator();
    }

    public static BookValidator getInstance() {
        return BookValidatorHolder.HOLDER_INSTANCE;
    }

    @Override
    public Book validateRead(String book) {
        if (isEmpty(book)) {
            return new Book(ID, "", "");
        }
        String[] array = book.split(DIVIDER);
        String title = array[0];
        String author = "";
        if (array.length == 2) {
            author = array[1];
        }
        return new Book(ID, title.trim(), author);

    }

    @Override
    public Book validateCreate(String book) {
        if (isEmpty(book)) {
            return null;
        }
        String[] array = book.split(DIVIDER);
        if (array.length != 2) {
            return null;
        }
        return new Book(ID, array[0], array[1]);

    }

    @Override
    public Book validateUpdate(String book) {
        if (isEmpty(book)) {
            return null;
        }
        String[] array = book.split(DIVIDER);
        if (array.length != 3) {
            return null;
        }
        int id = validateId(array[0]);
        if (id == ID) {
            return null;
        }
        return new Book(id, array[1], array[2]);

    }

    @Override
    public int validateDelete(String id) {
        return validateId(id);
    }

    private int validateId(String str) {
        if (isEmpty(str)) {
            return ID;
        }
        int id;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return ID;
        }
        return id;
    }

    private static boolean isEmpty(String str) {
        return (str == null || str.equals(""));
    }
}
