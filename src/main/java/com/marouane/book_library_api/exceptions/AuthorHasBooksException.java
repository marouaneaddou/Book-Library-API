package com.marouane.book_library_api.exceptions;

import java.lang.RuntimeException;

public class AuthorHasBooksException extends RuntimeException {
    public AuthorHasBooksException( ) {
        super("Author cannot be deleted because they have books.");
    }
}
