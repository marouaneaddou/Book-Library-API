package com.marouane.book_library_api.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.dtos.author.AuthorResponseDto;
import com.marouane.book_library_api.dtos.book.BookResponseDto;
import com.marouane.book_library_api.dtos.author.CreateAuthorDto;

import com.marouane.book_library_api.dtos.author.AuthorResponseDetailsDto;

@Component
public class AuthorMapper {
    public AuthorEntity toEntity( CreateAuthorDto authorDto ) {
        AuthorEntity auther = new AuthorEntity( authorDto.getName() );
        return auther;
    }

    public AuthorResponseDto toResponse( AuthorEntity auther ) {
        AuthorResponseDto authorResponse = new AuthorResponseDto( auther.getId(), auther.getName() );
        return authorResponse;
    }

    public AuthorResponseDetailsDto toDetailsResponse( AuthorEntity author ) {
        List<BookResponseDto> books = author.getBooks().stream()
            .map( book -> new BookResponseDto( book.getIsbn(), book.getTitle() )).toList();
        
        return new AuthorResponseDetailsDto( 
            author.getId(),
            author.getName(),
            books
        );
    }
}
