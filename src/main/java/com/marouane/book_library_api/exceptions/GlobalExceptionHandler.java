package com.marouane.book_library_api.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    public ResponseEntity<Map<String, Object>> handleValidationFaild( MethodArgumentNotValidException ex, 
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

    @ExceptionHandler( HttpMessageNotReadableException.class )
    public ResponseEntity<ErrorResponse> handleInvalidJson( HttpMessageNotReadableException ex, 
            HttpServletRequest request) {
            
                return this.builderResponse( 
                    HttpStatus.BAD_REQUEST,
                    "Invalid request body",
                    request.getRequestURI()
                );
    }

    @ExceptionHandler( HttpRequestMethodNotSupportedException.class )
    public ResponseEntity<ErrorResponse> handleMethodNotSupported( HttpRequestMethodNotSupportedException ex, 
            HttpServletRequest request) {
            
                return this.builderResponse( 
                    HttpStatus.METHOD_NOT_ALLOWED,
                    "HTTP method not allowed",
                    request.getRequestURI()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
        Exception ex,
        HttpServletRequest request ) {
            System.out.println(
                "Unexpected error while processing {}"
            );
            return builderResponse( 
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
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
