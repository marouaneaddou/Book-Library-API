package com.marouane.book_library_api.mappers;

import com.marouane.book_library_api.dtos.book.CreateBookDto;
import com.marouane.book_library_api.commands.book.CreateBookCommand;

public class BookMapper {

    public CreateBookCommand toCommand( CreateBookDto bookDto ) {
        // AuthorEntity auther = new AuthorEntity( authorDto.getName() );
        // return auther;
        return new CreateBookCommand();
    }
}
