package com.ijona.bookmanagement.Service;

import com.ijona.bookmanagement.Entities.Book;
import com.ijona.bookmanagement.Exceptions.AlreadyExistException;
import com.ijona.bookmanagement.Exceptions.EmptyListException;
import com.ijona.bookmanagement.Exceptions.NotFoundException;
import com.ijona.bookmanagement.RTO.UpdateBookRto;

import java.util.List;
public interface BookService {

    // Adds a new book
    public Book addBook(Book book) throws AlreadyExistException, NotFoundException;

    // Updates an existing book
    public Book updateBook(UpdateBookRto book, Integer bookId) throws NotFoundException;

    // Deletes a book by its ID
    public Integer deleteBook(Integer bookId) throws NotFoundException;

    // Retrieves a book by its ID
    public Book getBookById(Integer bookId) throws NotFoundException;

    // Retrieves all books
    public List<Book> getAllBooks() throws EmptyListException;
}

