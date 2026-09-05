package com.marouane.book_library_api.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.marouane.book_library_api.exceptions.ErrorResponse;
import com.marouane.book_library_api.exceptions.NotFoundException;
import com.marouane.book_library_api.exceptions.ConflictException;
import com.marouane.book_library_api.exceptions.AuthorHasBooksException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<ErrorResponse> handleNotFound( NotFoundException ex, 
            HttpServletRequest request) {
                return this.builderResponse( 
                    HttpStatus.NOT_FOUND,
                    ex.getMessage(),
                    request.getRequestURI()
                );
    }

    @ExceptionHandler( ConflictException.class )
    public ResponseEntity<ErrorResponse> handleConflit( ConflictException ex, 
            HttpServletRequest request) {
                return this.builderResponse( 
                    HttpStatus.CONFLICT,
                    ex.getMessage(),
                    request.getRequestURI()
                );
    }

    @ExceptionHandler( AuthorHasBooksException.class )
    public ResponseEntity<ErrorResponse> handleConflit( AuthorHasBooksException ex, 
            HttpServletRequest request) {
                return this.builderResponse( 
                    HttpStatus.CONFLICT,
                    ex.getMessage(),
                    request.getRequestURI()
                );
    }

    private ResponseEntity<ErrorResponse> builderResponse( 
        HttpStatus status,
        String message,
        String path
    ) {
        ErrorResponse response = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message,
            path,
            Instant.now()
        );

        return ResponseEntity
            .status( status.value() )
            .body( response );
    }
}
