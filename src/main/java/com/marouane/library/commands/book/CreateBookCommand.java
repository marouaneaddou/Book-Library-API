package com.marouane.library.commands.book;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CreateBookCommand {
    private String isbn;
    private String title;
    private Long authorId;
}
