package com.marouane.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marouane.library.entity.BookEntity;
import com.marouane.library.projections.BookSummary;

public interface BookRepository extends JpaRepository<BookEntity, String>{

    @Query("""
        SELECT  b.isbn AS isbn,
                b.title AS title,
                b.author.id AS authorId
        FROM BookEntity b
    """
    )
    List<BookSummary> findAllBook();

    boolean existsByAuthorId(Long authorId);
}
