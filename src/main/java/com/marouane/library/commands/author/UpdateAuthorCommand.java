package com.marouane.library.commands.author;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UpdateAuthorCommand {
    private String name;
}
