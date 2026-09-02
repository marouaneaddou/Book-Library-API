package com.marouane.book_library_api.mappers;


import org.springframework.stereotype.Component;

import com.marouane.book_library_api.dtos.book.BookResponseDto;
import com.marouane.book_library_api.dtos.book.CreateBookRequest;
import com.marouane.book_library_api.commands.book.CreateBookCommand;
import com.marouane.book_library_api.domain.BookEntity;

@Component
public class BookMapper {

    public CreateBookCommand toCommand( CreateBookRequest bookDto ) {
        return new CreateBookCommand( bookDto.getIsbn(), 
            bookDto.getTitle(), 
            bookDto.getAuthorId()
        );
    }

    public BookResponseDto toResponse( BookEntity bookEntity ) {
        return new BookResponseDto( 
            bookEntity.getIsbn(),
            bookEntity.getTitle()
        );
    }
}
