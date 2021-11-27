package test.stepanovichalena.reader;

import by.stepanovichalena.library.dao.source.exception.ReaderDAOException;
import by.stepanovichalena.library.dao.source.reader.TXTReader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class TXTReaderTest {
    private static final String BOOKS_SOURCE_PATH = "books.txt.source.path";
    Properties properties;

    @Before
    public void downloadProperties() throws IOException {
        String PROPERTY_PATH = "out/production/Task_4/resource/application.properties";
        properties = new Properties();
        properties.load(new FileInputStream(PROPERTY_PATH));
    }

    @Test
    public void readAll() throws ReaderDAOException {
        List<String> readAll = new TXTReader().readAll(BOOKS_SOURCE_PATH);
        System.out.println(readAll);
    }

    @Test
    public void writeAll() throws ReaderDAOException {
        List<String> readAll = new TXTReader().readAll(BOOKS_SOURCE_PATH);
        new TXTReader().write(BOOKS_SOURCE_PATH, readAll, false);
        List<String> result = new TXTReader().readAll(BOOKS_SOURCE_PATH);
        System.out.println(result);
    }
}