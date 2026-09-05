package com.marouane.library.mappers;


import org.springframework.stereotype.Component;

import com.marouane.library.dtos.author.AuthorResponseDto;
import com.marouane.library.dtos.book.BookResponseDetailsDto;
import com.marouane.library.dtos.book.BookResponseDto;
import com.marouane.library.dtos.book.CreateBookRequest;
import com.marouane.library.dtos.book.PartialUpdateRequest;
import com.marouane.library.dtos.book.UpdateBookRequest;
import com.marouane.library.projections.BookSummary;
import com.marouane.library.commands.book.CreateBookCommand;
import com.marouane.library.commands.book.PartialUpdateCommand;
import com.marouane.library.commands.book.UpdateBookCommand;
import com.marouane.library.entity.BookEntity;

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
    
    public PartialUpdateCommand toCommand( String isbn, PartialUpdateRequest request ) {
        return new PartialUpdateCommand(
            isbn,
            request.getTitle(),
            request.getAuthorId()
        );
    }

    public UpdateBookCommand toCommand( UpdateBookRequest updateBookRequest, String isbn ) {
        return new UpdateBookCommand(
            isbn,
            updateBookRequest.getTitle(),
            updateBookRequest.getAuthorId()
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
