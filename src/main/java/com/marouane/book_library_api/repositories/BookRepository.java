package com.marouane.book_library_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marouane.book_library_api.domain.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long>{

    
}
