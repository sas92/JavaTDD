package com.sas.isbntools;

public class ISBNValidator {

    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isValidLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isValidShortISBN(isbn);
        } else {
            throw new NumberFormatException("ISBN numbers must be of 10 or 13 digits");
        }
    }

    private boolean isValidShortISBN(String isbn) {
        int total = 0;
        for (int i = SHORT_ISBN_LENGTH - 1; i >= 0; i--) {
            char digit = isbn.charAt(i);
            if (!Character.isDigit(digit)) {
                if (i == 9 && digit == 'X') {
                    total += SHORT_ISBN_LENGTH;
                } else {
                    throw new NumberFormatException("ISBN must be numeric");
                }
            } else {
                total += Character.getNumericValue(digit) * (SHORT_ISBN_LENGTH - i);
            }
        }
        return total % SHORT_ISBN_MULTIPLIER == 0;
    }

    private boolean isValidLongISBN(String isbn) {
        int total = 0;
        for (int i = LONG_ISBN_LENGTH - 1; i >= 0; i--) {
            char digit = isbn.charAt(i);
            if (i % 2 == 0) {
                total += Character.getNumericValue(digit);
            } else {
                total += Character.getNumericValue(digit) * 3;
            }
        }
        return total % LONG_ISBN_MULTIPLIER == 0;
    }
}
