package com.marouane.book_library_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.repositories.AuthorRepository;

@Service
public class AuthorService {

    AuthorRepository authorRepository;
    public AuthorService( AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public AuthorEntity createAuthor( AuthorEntity authorEntity ) {
        AuthorEntity auther = this.authorRepository.save( authorEntity );
        return auther;
    };

    public List<AuthorEntity> findAll( ) {
        return this.authorRepository.findAll(  );
    }
}