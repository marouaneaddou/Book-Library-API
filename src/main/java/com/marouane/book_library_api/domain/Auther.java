package com.marouane.book_library_api.domain;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name= "authors")
public class Auther {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn( name = "author" )
    private List<Book> books = new ArrayList<>();
}