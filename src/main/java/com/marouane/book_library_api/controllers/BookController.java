package com.marouane.book_library_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.marouane.book_library_api.commands.book.CreateBookCommand;
import com.marouane.book_library_api.domain.BookEntity;
import com.marouane.book_library_api.dtos.book.BookResponseDetailsDto;
import com.marouane.book_library_api.dtos.book.BookResponseDto;
import com.marouane.book_library_api.dtos.book.CreateBookRequest;
import com.marouane.book_library_api.dtos.book.UpdateBookRequest;
import com.marouane.book_library_api.mappers.BookMapper;
import com.marouane.book_library_api.projections.BookSummary;
import com.marouane.book_library_api.services.BookService;
import com.marouane.book_library_api.services.AuthorService;
import com.marouane.book_library_api.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookMapper bookMapper;
    private BookService bookService;
    private AuthorService authorService;

    public BookController( BookMapper bookMapper, 
        BookService bookService,
        AuthorService authorService ) {
            this.bookMapper = bookMapper;
            this.bookService = bookService;
            this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDetailsDto> createBook( @Valid @RequestBody CreateBookRequest book ) {
        CreateBookCommand bookCommand = this.bookMapper.toCommand( book );
        BookEntity saveBook = this.bookService.create( bookCommand );
        return ResponseEntity
            .status( HttpStatus.CREATED )
            .body( this.bookMapper.toDetailsResponse( saveBook ) );
    }

    @GetMapping
    public List<BookResponseDto> findAll( ) {
        List<BookSummary> books = this.bookService.findAll();
        return books.stream()
            .map( book -> this.bookMapper.toResponse(book))
            .toList();
    }

    @GetMapping("/{isbn}")
    public BookResponseDetailsDto getBook( @PathVariable("isbn") String isbn ) {
        BookEntity findOne = this.bookService.findOne( isbn );
        return this.bookMapper.toDetailsResponse(findOne);
    }

    @PutMapping( "/{isbn}" ) 
    public BookResponseDetailsDto updateBook( 
        @PathVariable( "isbn" ) String isbn, 
        @Valid @RequestBody UpdateBookRequest updateBook ) {

        // check book is exists
        Boolean bookExists = this.bookService.isExists( isbn );
        if ( !bookExists ) throw new NotFoundException( "Book not found" );
        // check author if exist
        Boolean authorExists = this.authorService.isExists( updateBook.getAuthorId() );
        if ( !authorExists ) throw new NotFoundException( "Author not found" );
        // update
        BookEntity book = this.bookService.update( this.bookMapper.toUpdateCommand( updateBook, isbn ) );
        // return new payload data
        return this.bookMapper.toDetailsResponse( book );
    }
 }
