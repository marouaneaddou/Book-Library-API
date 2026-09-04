package com.marouane.book_library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marouane.book_library_api.commands.book.CreateBookCommand;
import com.marouane.book_library_api.commands.book.PartialUpdateCommand;
import com.marouane.book_library_api.commands.book.UpdateBookCommand;
import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.domain.BookEntity;
import com.marouane.book_library_api.services.AuthorService;
import com.marouane.book_library_api.repositories.AuthorRepository;
import com.marouane.book_library_api.repositories.BookRepository;
import com.marouane.book_library_api.exceptions.ConflictException;
import com.marouane.book_library_api.exceptions.NotFoundException;
import com.marouane.book_library_api.projections.BookSummary;

@Service
public class BookService {
    private final BookRepository  bookRepository;
    private final AuthorService    authorService;

    public BookService( BookRepository bookRepository,
        AuthorRepository authorRepository,
        AuthorService authorService ) {
        this.bookRepository     = bookRepository;
        this.authorService      = authorService;
    }

    public BookEntity create( CreateBookCommand bookCommand ) {
        // find author
        AuthorEntity author = this.authorService.findOne( bookCommand.getAuthorId() );
        
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

    public BookEntity findOne( String isbn ) {
        BookEntity book = this.bookRepository.findById( isbn )
            .orElseThrow(() -> new NotFoundException( "book not found"));
        return book;
    }

    public boolean isExists( String isbn ) {
        return this.bookRepository.existsById( isbn );
    }

    public BookEntity update( UpdateBookCommand command ) {
        BookEntity book =
                        bookRepository.getReferenceById( command.getIsbn() );
        AuthorEntity author =
                        this.authorService.findOne( command.getAuthorId() );
        book.setTitle( command.getTitle() );
        book.setAuthor( author );
        return this.bookRepository.save( book );
    }

    public BookEntity partialUpdate( PartialUpdateCommand command ) {
        // get book reference
        BookEntity book = this.bookRepository.getReferenceById(command.getIsbn());
        // check if title and authorId exists
        if ( command.getTitle() != null ) book.setTitle( command.getTitle() );
        if ( command.getAuthorId() != null ) {
            AuthorEntity author = this.authorService.findOne( command.getAuthorId() );
            book.setAuthor( author );
        };
        // save new data and returned
        return this.bookRepository.save( book );
    }
}
