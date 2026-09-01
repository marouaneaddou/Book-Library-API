package com.marouane.book_library_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.marouane.book_library_api.dtos.book.CreateBookDto;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PostMapping
    public void createBook(@Valid @RequestBody CreateBookDto book ) {
        
    }
}
