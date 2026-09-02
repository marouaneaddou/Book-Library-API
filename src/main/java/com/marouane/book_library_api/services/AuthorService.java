package com.marouane.book_library_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marouane.book_library_api.commands.author.UpdateAuthorCommand;
import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.exceptions.NotFoundException;
import com.marouane.book_library_api.repositories.AuthorRepository;

@Service
public class AuthorService {

    AuthorRepository authorRepository;
    public AuthorService( AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        
        author.setName( update.getName());
        return this.authorRepository.save( author );
    }
}