package com.marouane.library.repositories;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.marouane.library.entity.AuthorEntity;
import com.marouane.library.repositories.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    private AuthorEntity author;

    @BeforeEach
    void setUp() {
        AuthorEntity author = new AuthorEntity( 
            "Marouane"
        );
        this.author = authorRepository.save(author);
    }
    @Test
    public void createNewAuthorTest( ) {
        System.out.println("\u001B[33mRunning: Create a new author\u001B[0m");
        AuthorEntity savedAuthor = this.authorRepository.save( author );
        assertNotNull( savedAuthor, "The created author should not be null");
        assertEquals("Marouane", savedAuthor.getName());
        System.out.println("\u001B[32m✓ Create a new author - PASSED\u001B[0m");
    }

    @Test
    public void updateAuthorTest( ) {
        System.out.println("\u001B[33mRunning: Update author\u001B[0m");
        AuthorEntity savedAuthor = this.authorRepository.save( this.author );
        assertNotNull( savedAuthor, "The created author should not be null");

        // update
        savedAuthor.setName("Mohammed");
        AuthorEntity updatedAuthor = this.authorRepository.save( savedAuthor );
        System.out.println( updatedAuthor.getName() );

        // check
        assertEquals( "Mohammed", updatedAuthor.getName() );
        System.out.println("\u001B[32m✓ Update author - PASSED\u001B[0m");
    }

    @Test
    public void findAuthorByIdTest( ) {
        Optional<AuthorEntity> author = this.authorRepository.findById( this.author.getId() );
        assertTrue( author.isPresent() );
        assertEquals( "Marouane", author.get().getName() );
    }

    @Test
    public void deleteAuthorTest( ) {
        this.authorRepository.delete( this.author );

        Optional<AuthorEntity> author = this.authorRepository.findById( 1L );

        assertFalse( author.isPresent() );
    }
}
