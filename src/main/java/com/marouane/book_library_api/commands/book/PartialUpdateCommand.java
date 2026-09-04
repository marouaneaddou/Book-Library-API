package com.marouane.book_library_api.commands.book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartialUpdateCommand {
    private String isbn;
    private String title;
    private Long authorId;
}
