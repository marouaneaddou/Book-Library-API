package com.marouane.library.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marouane.library.commands.author.UpdateAuthorCommand;
import com.marouane.library.entity.AuthorEntity;
import com.marouane.library.dtos.author.AuthorResponseDetailsDto;
import com.marouane.library.dtos.author.AuthorResponseDto;
import com.marouane.library.dtos.author.CreateAuthorDto;
import com.marouane.library.dtos.author.UpdateAuthorRequest;
import com.marouane.library.mappers.AuthorMapper;
import com.marouane.library.services.AuthorService;
import com.marouane.library.exceptions.NotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    AuthorController( AuthorService authorService, AuthorMapper  authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody CreateAuthorDto authorDto ) {
        AuthorEntity author = this.authorMapper.toEntity( authorDto );
        AuthorEntity savedAutherEntity = this.authorService.createAuthor( author );
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body( this.authorMapper.toResponse( savedAutherEntity ) );
    }

    @GetMapping
    public List<AuthorResponseDto> listAuthers( ) {

        List<AuthorEntity> authers = this.authorService.findAll();

        return authers.stream()
            .map( author -> authorMapper.toResponse( author ) )
            .toList();
    }

    @GetMapping("/{id}")
    public AuthorResponseDetailsDto getAuthor( @PathVariable("id") Long id) {
        // Optional<AuthorEntity> foundAuthor = this.authorService.findOne( id );
        // return foundAuthor
        //     .map(author -> new ResponseEntity<>(
        //         this.authorMapper.toDetailsResponse(author),
        //         HttpStatus.OK
        //     ))
        //     .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        AuthorEntity auther = this.authorService.findOne( id );
        return this.authorMapper.toDetailsResponse( auther );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor( @Valid 
        @RequestBody UpdateAuthorRequest updateRequest, 
        @PathVariable("id") Long id ) {
        UpdateAuthorCommand command = this.authorMapper.toCommand( updateRequest );
        AuthorEntity updateAuthor = this.authorService.updateOne( id, command );
        return ResponseEntity
            .status(200)
            .body( this.authorMapper.toResponse( updateAuthor ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor( @PathVariable("id") long id ) {
        // is exists
        // boolean exists = this.authorService.isExists( id );
        // if ( !exists ) throw new NotFoundException( "Author does not exists" );
        this.authorService.delete( id );
        return ResponseEntity.noContent().build();
    }
}
