package by.stepanovichalena.library.util;

import by.stepanovichalena.library.util.exeption.LibraryUtilException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public String hashPassword(String password) throws LibraryUtilException {
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
            throw new LibraryUtilException("Exception while hashing password", e);
        }
        return hashBuilder.toString();
    }
}
