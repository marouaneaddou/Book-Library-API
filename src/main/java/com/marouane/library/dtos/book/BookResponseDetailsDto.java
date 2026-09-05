package com.marouane.library.dtos.book;

import com.marouane.library.dtos.author.AuthorResponseDto;

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
