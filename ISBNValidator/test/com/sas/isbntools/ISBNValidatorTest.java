package com.sas.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ISBNValidatorTest {

    @Test
    public void checkValid10DigitISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("Valid first ISBN", result);
        result = validator.checkISBN("9388754794");
        assertTrue("Valid second ISBN", result);
    }

    @Test
    public void checkInvalid10DigitISBN() {
        boolean result = new ISBNValidator().checkISBN("0140449115");
        assertFalse(result);
    }

    @Test
    public void checkXAtEndValidISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkXAtEndInvalidISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("012000031X");
        assertFalse(result);
    }

    @Test
    public void checkValid13DigitISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("9787070481946");
        assertTrue(result);
    }

    @Test
    public void checkInvalid13DigitISBN() {
        boolean result = new ISBNValidator().checkISBN("9787070481947");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void onlyTenOrThirteenDigitISAllowed() {
        new ISBNValidator().checkISBN("140449115");
    }

    @Test(expected = NumberFormatException.class)
    public void onlyNumericISBNAllowed() {
        new ISBNValidator().checkISBN("Hello World");
    }
}