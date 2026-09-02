package com.marouane.book_library_api.dtos.book;

import com.marouane.book_library_api.dtos.author.AuthorResponseDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDetailsDto {
    private String isbn;
    private String title;
    private AuthorResponseDto author;
}
