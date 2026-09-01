package com.marouane.book_library_api.mappers;

import org.springframework.stereotype.Component;

import com.marouane.book_library_api.domain.AuthorEntity;
import com.marouane.book_library_api.dtos.author.AuthorResponseDto;
import com.marouane.book_library_api.dtos.author.CreateAuthorDto;

@Component
public class AuthorMapper {
    public AuthorEntity toEntity( CreateAuthorDto authorDto ) {
        AuthorEntity auther = new AuthorEntity( authorDto.getName() );
        return auther;
    }

    public AuthorResponseDto toDto( AuthorEntity auther ) {
        AuthorResponseDto authorResponse = new AuthorResponseDto( auther.getId(), auther.getName() );
        return authorResponse;
    }
}
