package com.sas.services;

import com.sas.models.Book;

public interface ExternalISBNDataService {
    Book lookup(String isbn);
}
