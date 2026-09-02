package com.marouane.book_library_api.commands.author;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UpdateAuthorCommand {
    private String name;
}
