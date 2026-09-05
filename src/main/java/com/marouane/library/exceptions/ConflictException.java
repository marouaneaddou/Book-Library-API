package com.marouane.library.exceptions;

import java.lang.RuntimeException;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
