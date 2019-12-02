package com.sas.isbntools;

import com.sas.models.Book;
import com.sas.services.ExternalISBNDataService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StockManagerTest {

    private ExternalISBNDataService testWebService;
    private StockManager stockManager;
    private ExternalISBNDataService testDatabaseService;

    @Before
    public void setup() {
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void getLocatorCode() {
        final String isbn = "0140449116";
        when(testWebService.lookup(anyString()))
                .thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
        when(testDatabaseService.lookup(anyString()))
                .thenReturn(null);

        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("9116J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        when(testDatabaseService.lookup("0140449116"))
                .thenReturn(new Book("0140449116", "abc", "abc"));

        final String isbn = "0140449116";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("0140449116");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        when(testDatabaseService.lookup("0140449116"))
                .thenReturn(null);
        when(testWebService.lookup("0140449116"))
                .thenReturn(new Book("0140449116", "abc", "abc"));

        final String isbn = "0140449116";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("0140449116");
        verify(testWebService).lookup("0140449116");
    }
}