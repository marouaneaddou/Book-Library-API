package com.marouane.book_library_api.dtos.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {
    @NotBlank(message = "Name cannot be empty or just spaces")
    private String title;
    @NotNull
    private Long authorId;
}
