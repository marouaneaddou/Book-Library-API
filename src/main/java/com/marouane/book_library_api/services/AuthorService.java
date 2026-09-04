package com.marouane.book_library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marouane.book_library_api.commands.author.UpdateAuthorCommand;
import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.exceptions.AuthorHasBooksException;
import com.marouane.book_library_api.exceptions.NotFoundException;
import com.marouane.book_library_api.repositories.AuthorRepository;
import com.marouane.book_library_api.repositories.BookRepository;

@Service
public class AuthorService {

    AuthorRepository authorRepository;
    BookRepository     bookRepository;
    public AuthorService( AuthorRepository authorRepository,
        BookRepository bookRepository
    ) {
        this.authorRepository   = authorRepository;
        this.bookRepository        = bookRepository;
    }
    
    public AuthorEntity createAuthor( AuthorEntity authorEntity ) {
        AuthorEntity author = this.authorRepository.save( authorEntity );
        return author;
    };

    public List<AuthorEntity> findAll( ) {
        return this.authorRepository.findAll(  );
    }

    public AuthorEntity findOne( Long id ) {
        // Optional<AuthorEntity> auther = this.authorRepository.findById( id );
        // return author;
        AuthorEntity author = this.authorRepository.findById( id )
            .orElseThrow(() -> new NotFoundException( "Author not found"));
        return author;
    }

    public AuthorEntity updateOne( Long id, UpdateAuthorCommand update  ) {
        AuthorEntity author = this.authorRepository.findById( id )
            .orElseThrow(() -> new NotFoundException( "Author not found"));
        
        author.setName( update.getName() );
        return this.authorRepository.save( author );
    }

    public boolean isExists( Long id ) {
        return this.authorRepository.existsById( id );
    }

    public void delete( Long id ) {
        // find author
        AuthorEntity author = this.findOne( id );
        // if ( !exists ) throw new NotFoundException( "Author does not exists" );
        // check author has books
        boolean hasBooks = this.bookRepository.existsByAuthorId( id );
        if ( hasBooks ) throw new AuthorHasBooksException( );
        // delete author
        this.authorRepository.delete( author );
    }
}