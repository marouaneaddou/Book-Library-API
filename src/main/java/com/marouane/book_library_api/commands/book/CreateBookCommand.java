package com.marouane.book_library_api.commands.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookCommand {
    private String isbn;
    private String title;
    private Long authorId;

}
