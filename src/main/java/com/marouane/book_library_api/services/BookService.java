package com.marouane.book_library_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marouane.book_library_api.commands.book.CreateBookCommand;
import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.domain.BookEntity;
import com.marouane.book_library_api.dtos.book.BookResponseDto;
import com.marouane.book_library_api.repositories.AuthorRepository;
import com.marouane.book_library_api.repositories.BookRepository;
import com.marouane.book_library_api.exceptions.ConflictException;
import com.marouane.book_library_api.exceptions.NotFoundException;
import com.marouane.book_library_api.projections.BookSummary;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService( BookRepository bookRepository, AuthorRepository authorRepository ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookEntity create( CreateBookCommand bookCommand ) {
        // find author
        AuthorEntity author = this.authorRepository.findById( bookCommand.getAuthorId() )
            .orElseThrow( () -> new NotFoundException( "Author not found") );
        
        // check duplication
        if ( this.bookRepository.existsById( bookCommand.getIsbn() ) ) {
            throw new ConflictException(
                "Book with ISBN already exists"
            );
        }
        // create new book entity
        BookEntity bookEntity = new BookEntity(
            bookCommand.getIsbn(),
            bookCommand.getTitle(),
            author
        );
        // save book
        return this.bookRepository.save( bookEntity );
    };

    public List<BookSummary> findAll( ) {
        return this.bookRepository.findAllBook();
    } 
}
