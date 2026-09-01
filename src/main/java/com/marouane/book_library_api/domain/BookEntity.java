package com.marouane.book_library_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="books")
public class BookEntity {
    @Id
    private String isbn;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private  AuthorEntity author;
}
