package com.marouane.library.commands.book;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UpdateBookCommand {
    private String isbn;
    private String title;
    private Long authorId;
}
