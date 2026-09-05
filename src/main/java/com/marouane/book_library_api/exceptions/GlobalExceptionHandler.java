package com.marouane.book_library_api.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import com.marouane.book_library_api.exceptions.ErrorResponse;
import com.marouane.book_library_api.exceptions.NotFoundException;
import com.marouane.book_library_api.exceptions.ConflictException;
import com.marouane.book_library_api.exceptions.AuthorHasBooksException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

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

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<Map<String, Object>> handleConflit( MethodArgumentNotValidException ex, 
            HttpServletRequest request) {
            
            Map<String, String> fieldErrors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                fieldErrors.put(error.getField(), error.getDefaultMessage());
            });

            Map<String, Object> response = new HashMap<>();

            response.put( "status", HttpStatus.BAD_REQUEST.value() );
            response.put( "error", HttpStatus.BAD_REQUEST.getReasonPhrase() );
            response.put( "message", "Validation failed" );
            response.put( "path", request.getRequestURI() );
            response.put( "timestamp", Instant.now() );
            response.put( "fieldErrors", fieldErrors );

            return ResponseEntity
                .status(  HttpStatus.BAD_REQUEST.value() )
                .body( response );
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
