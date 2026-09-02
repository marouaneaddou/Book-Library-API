package com.marouane.book_library_api.mappers;


import org.springframework.stereotype.Component;

import com.marouane.book_library_api.dtos.author.AuthorResponseDto;
import com.marouane.book_library_api.dtos.book.BookResponseDetailsDto;
import com.marouane.book_library_api.dtos.book.BookResponseDto;
import com.marouane.book_library_api.dtos.book.CreateBookRequest;
import com.marouane.book_library_api.projections.BookSummary;
import com.marouane.book_library_api.commands.book.CreateBookCommand;
import com.marouane.book_library_api.domain.BookEntity;

@Component
public class BookMapper {

    private AuthorMapper autherMapper;

    public BookMapper( AuthorMapper autherMapper ) {
        this.autherMapper = autherMapper;
    }
    public CreateBookCommand toCommand( CreateBookRequest bookRequest ) {
        return new CreateBookCommand( bookRequest.getIsbn(), 
            bookRequest.getTitle(), 
            bookRequest.getAuthorId()
        );
    }

    public BookResponseDetailsDto toDetailsResponse( BookEntity book ) {
        AuthorResponseDto author = this.autherMapper.toResponse( book.getAuthor() );

        return new BookResponseDetailsDto( 
            book.getIsbn(),
            book.getTitle(),
            author
        );
    }

    public BookResponseDto toResponse( BookSummary bookSummary ) {
        return new BookResponseDto( 
            bookSummary.getIsbn(),
            bookSummary.getTitle(),
            bookSummary.getAuthorId()
        );
    }
}
