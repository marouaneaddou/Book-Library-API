package com.marouane.book_library_api.exceptions;

import java.lang.RuntimeException;

public class NotFoundException extends RuntimeException {
    public NotFoundException( String message ) {
        super( message );
    }
}
