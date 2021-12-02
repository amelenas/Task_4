package by.stepanovichalena.library.dao.source.reader;

import by.stepanovichalena.library.dao.source.LibraryReader;
import by.stepanovichalena.library.dao.source.exception.ReaderDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TXTReader implements LibraryReader {
    private static final Logger LOGGER = LogManager.getLogger(TXTReader.class);
    private static final Properties PROPERTIES;
    private static String PROPERTY_PATH = "out/production/Task_4/resource/application.properties";

    public static Properties getProperties() {
        return PROPERTIES;
    }

    public static void setPropertyPath(String path) {
        PROPERTY_PATH = path;
    }

    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(PROPERTY_PATH));
        } catch (IOException e) {
          LOGGER.error("Properties not found",e);
        }
    }

    public TXTReader() {
    }

    public List<String> readAll(String propertyName) throws ReaderDAOException {
        String path = PROPERTIES.getProperty(propertyName);
        List<String> textLines;
        try {
            textLines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            LOGGER.error("Can't read file ", e);
            throw new ReaderDAOException("Can't read file", e);
        }
        return textLines;
    }

    public void write(String sourceProperty, Collection<String> data, boolean isAppend) throws ReaderDAOException {
        String path = PROPERTIES.getProperty(sourceProperty);
        try (FileOutputStream writer = new FileOutputStream(path, isAppend)) {
            for (String values : data)
            writer.write(values.getBytes());
        } catch (IOException e) {
            LOGGER.error("Exception in BookSourceImpl while trying to write ", e);
            throw new ReaderDAOException("Can't write file", e);
        }
    }
}
