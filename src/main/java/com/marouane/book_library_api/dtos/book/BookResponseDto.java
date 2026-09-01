package com.marouane.book_library_api.dtos.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private String isbn;
    private String title;
}

