package com.marouane.library.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.marouane.library.commands.author.UpdateAuthorCommand;
import com.marouane.library.entity.AuthorEntity;
import com.marouane.library.dtos.author.AuthorResponseDto;
import com.marouane.library.dtos.book.BookResponseDto;
import com.marouane.library.dtos.author.CreateAuthorDto;
import com.marouane.library.dtos.author.UpdateAuthorRequest;
import com.marouane.library.dtos.author.AuthorResponseDetailsDto;

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
            .map( book -> new BookResponseDto( book.getIsbn(), book.getTitle(), author.getId() )).toList();
        
        return new AuthorResponseDetailsDto( 
            author.getId(),
            author.getName(),
            books
        );
    }

    public UpdateAuthorCommand toCommand( UpdateAuthorRequest update ) {
        return new UpdateAuthorCommand( update.getName() );
    }
}
