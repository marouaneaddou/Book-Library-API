package com.marouane.book_library_api.dtos.author;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;

import com.marouane.book_library_api.dtos.book.BookResponseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDetailsDto {
    private Long id;
    private String name;
    private List<BookResponseDto> books;
}