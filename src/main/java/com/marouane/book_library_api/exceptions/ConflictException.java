package com.marouane.book_library_api.exceptions;

import java.lang.RuntimeException;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
