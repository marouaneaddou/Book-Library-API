package com.marouane.book_library_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.marouane.book_library_api.commands.book.CreateBookCommand;
import com.marouane.book_library_api.domain.BookEntity;
import com.marouane.book_library_api.dtos.book.BookResponseDetailsDto;
import com.marouane.book_library_api.dtos.book.BookResponseDto;
import com.marouane.book_library_api.dtos.book.CreateBookRequest;
import com.marouane.book_library_api.mappers.BookMapper;
import com.marouane.book_library_api.projections.BookSummary;
import com.marouane.book_library_api.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookMapper bookMapper;
    private BookService bookService;

    public BookController( BookMapper bookMapper, BookService bookService ) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDetailsDto> createBook(@Valid @RequestBody CreateBookRequest book ) {
        CreateBookCommand bookCommand = this.bookMapper.toCommand( book );
        BookEntity saveBook = this.bookService.create( bookCommand );
        return ResponseEntity
            .status( HttpStatus.CREATED )
            .body( this.bookMapper.toDetailsResponse( saveBook ) );
    }

    @GetMapping
    public List<BookResponseDto> findAll( ) {
        List<BookSummary> books = this.bookService.findAll();
        System.out.println(books);
        return books.stream()
            .map( book -> this.bookMapper.toResponse(book))
            .toList();
    }
 }
