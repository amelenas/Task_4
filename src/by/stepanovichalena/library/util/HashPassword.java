package by.stepanovichalena.library.util;

import by.stepanovichalena.library.controller.exception.ControllerException;
import by.stepanovichalena.library.util.exeption.LibraryException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public String hashPassword(String password) throws LibraryException {
        StringBuilder hashBuilder = new StringBuilder();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            if (password != null) {
                byte[] hash = messageDigest.digest(password.getBytes());
                for (byte b : hash) {
                    hashBuilder.append(b);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new LibraryException("Exception while hashing password", e);
        }
        return hashBuilder.toString();
    }
}
