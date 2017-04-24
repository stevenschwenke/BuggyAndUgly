package de.stevenschwenke.java.buggyandugly.importer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImportServiceTest {

    @Test
    public void integrationTest() {
        ImportService importService = new ImportService();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test_import.txt").getFile());
        assertNotNull(file);
        assertTrue(file.exists());
        ImportMetadata importMetadata = importService.readImportMetaData(file.getPath());
        assertNotNull(importMetadata);

        int periodFromYear = extractYear(importMetadata.getPeriodFrom());
        assertEquals(2016, periodFromYear);

        int periodUntilYear = extractYear(importMetadata.getPeriodUntil());
        assertEquals(2025, periodUntilYear);

        String type = importMetadata.getType();
        assertEquals("Ultra Fast Bike", type);

        String someInternalNumber = importMetadata.getSomeInternalNumber();
        assertEquals("123  7, 789  7, 456  L", someInternalNumber);
    }

    private int extractYear(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(GregorianCalendar.YEAR);
    }
}
