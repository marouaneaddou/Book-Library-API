package com.marouane.book_library_api.dtos.author;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponseDto {
    private Long id;
    private String name;
}
