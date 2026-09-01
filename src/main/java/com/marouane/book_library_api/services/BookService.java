package src.main.java.com.marouane.book_library_api.services;

import org.springframework.stereotype.Service;
import com.marouane.book_library_api.repositories.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService( BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
    }

}
