package com.marouane.book_library_api.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marouane.book_library_api.commands.author.UpdateAuthorCommand;
import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.dtos.author.AuthorResponseDetailsDto;
import com.marouane.book_library_api.dtos.author.AuthorResponseDto;
import com.marouane.book_library_api.dtos.author.CreateAuthorDto;
import com.marouane.book_library_api.dtos.author.UpdateAuthorRequest;
import com.marouane.book_library_api.mappers.AuthorMapper;
import com.marouane.book_library_api.services.AuthorService;
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
    public ResponseEntity<AuthorResponseDetailsDto> createAuthor(@Valid @RequestBody CreateAuthorDto authorDto ) {
        AuthorEntity author = this.authorMapper.toEntity( authorDto );
        AuthorEntity savedAutherEntity = this.authorService.createAuthor( author );
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body( this.authorMapper.toDetailsResponse( savedAutherEntity ) );
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
    public ResponseEntity<AuthorResponseDto> updateAuthor( @Valid @RequestBody UpdateAuthorRequest updateRequest, @PathVariable("id") Long id ) {
        UpdateAuthorCommand command = this.authorMapper.toCommand( updateRequest );
        AuthorEntity updateAuthor = this.authorService.updateOne( id, command );
        return ResponseEntity
            .status(200)
            .body( this.authorMapper.toResponse( updateAuthor ) );
    }
}
