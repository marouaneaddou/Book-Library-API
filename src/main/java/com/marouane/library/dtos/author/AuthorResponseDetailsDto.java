package com.marouane.library.dtos.author;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;

import com.marouane.library.dtos.book.BookResponseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDetailsDto {
    private Long id;
    private String name;
    private List<BookResponseDto> books;
}