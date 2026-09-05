package com.marouane.library.dtos.author;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorRequest {
    @NotBlank(message = "Name cannot be empty or just spaces")
    private String name;
}
