package com.marouane.library.dtos.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
// import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartialUpdateRequest {
    // @NotBlank
    @Size(min = 2, max = 100)
    private String title;
    @Positive
    private Long authorId;
}
