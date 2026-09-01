package com.marouane.book_library_api.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.dtos.author.AuthorResponseDto;
import com.marouane.book_library_api.dtos.author.CreateAuthorDto;
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
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody CreateAuthorDto authorDto ) {
        AuthorEntity auther = this.authorMapper.toEntity( authorDto );
        AuthorEntity savedAutherEntity = this.authorService.createAuthor( auther );
        return ResponseEntity.status(HttpStatus.CREATED).body(authorMapper.toDto(savedAutherEntity));
    }
}
