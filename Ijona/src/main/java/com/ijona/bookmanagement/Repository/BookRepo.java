package com.ijona.bookmanagement.Repository;

import com.ijona.bookmanagement.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    // Retrieves a book by its ISBN
    Optional<Book> findByBookIsbn(String bookIsbn);
}

