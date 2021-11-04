package by.stepanovichalena.library.controller;

public enum Actions {
    SIGN_UP,
    LOG_IN,
    ADD_BOOK,
    FIND_BOOK,
    DELETE_BOOK,
    CHANGE_BOOK,
    WRONG_REQUEST;

    public static Actions parse(String command) {
        Actions action;
        try {
            action = Actions.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            action = Actions.WRONG_REQUEST;
        }
        return action;
    }
}

