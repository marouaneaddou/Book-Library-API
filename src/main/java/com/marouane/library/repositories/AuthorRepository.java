package com.marouane.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marouane.library.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{

}
