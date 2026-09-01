package com.marouane.book_library_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marouane.book_library_api.domain.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{

}
