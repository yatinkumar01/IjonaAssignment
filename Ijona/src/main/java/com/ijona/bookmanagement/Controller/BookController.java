package com.ijona.bookmanagement.Controller;

import com.ijona.bookmanagement.Constants.BookConstants;
import com.ijona.bookmanagement.DTO.AllBooksDto;
import com.ijona.bookmanagement.DTO.BookDto;
import com.ijona.bookmanagement.Entities.Book;
import com.ijona.bookmanagement.Exceptions.AlreadyExistException;
import com.ijona.bookmanagement.Exceptions.EmptyListException;
import com.ijona.bookmanagement.Exceptions.NotFoundException;
import com.ijona.bookmanagement.RTO.AddBookRto;
import com.ijona.bookmanagement.RTO.UpdateBookRto;
import com.ijona.bookmanagement.Service.BookService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    /**
     * Add a new book.
     *
     * @param bookRto The request object containing book data.
     * @return ResponseEntity containing the created book and HTTP status CREATED.
     * @throws NotFoundException     if the book is not found.
     * @throws AlreadyExistException if the book already exists.
     */
    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody AddBookRto bookRto) throws NotFoundException, AlreadyExistException {
        // Map the request data to a Book object
        Book bookToBeAdded = modelMapper.map(bookRto, Book.class);
        // Call the addBook method of bookService to add the book
        Book book = bookService.addBook(bookToBeAdded);
        // Map the created book to BookDto and return it with HTTP status CREATED
        return new ResponseEntity<>(modelMapper.map(book, BookDto.class), HttpStatus.CREATED);
    }

    /**
     * Update an existing book.
     *
     * @param updateBookRto The request object containing updated book data.
     * @param bookId        The ID of the book to be updated.
     * @return ResponseEntity containing the updated book and HTTP status OK.
     * @throws NotFoundException if the book is not found.
     */
    @PutMapping("/update/{bookId}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody UpdateBookRto updateBookRto, @PathVariable("bookId") Integer bookId) throws NotFoundException {
        // Map the request data to a Book object
        Book bookToBeUpdated = modelMapper.map(updateBookRto, Book.class);
        // Call the updateBook method of bookService to update the book
        Book updatedBook = bookService.updateBook(updateBookRto, bookId);
        // Map the updated book to BookDto and return it with HTTP status OK
        return new ResponseEntity<>(modelMapper.map(updatedBook, BookDto.class), HttpStatus.OK);
    }

    /**
     * Delete a book by its ID.
     *
     * @param bookId The ID of the book to be deleted.
     * @return ResponseEntity with a success message and HTTP status OK.
     * @throws NotFoundException if the book is not found.
     */
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") Integer bookId) throws NotFoundException {
        // Call the deleteBook method of bookService to delete the book
        Integer id = bookService.deleteBook(bookId);
        // Return a success message with the deleted book's ID and HTTP status OK
        return new ResponseEntity<>(BookConstants.BOOK_DELETED_SUCCESS + id, HttpStatus.OK);
    }

    /**
     * Get a book by its ID.
     *
     * @param bookId The ID of the book to retrieve.
     * @return ResponseEntity containing the book and HTTP status OK.
     * @throws NotFoundException if the book is not found.
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("bookId") Integer bookId) throws NotFoundException {
        // Call the getBookById method of bookService to retrieve the book
        Book bookById = bookService.getBookById(bookId);
        // Map the book to BookDto and return it with HTTP status OK
        return ResponseEntity.ok(modelMapper.map(bookById, BookDto.class));
    }

    /**
     * Get all books.
     *
     * @return ResponseEntity containing a list of all books and HTTP status OK.
     * @throws EmptyListException if the book list is empty.
     */
    @GetMapping("/allbooks")
    public ResponseEntity<List<AllBooksDto>> getAllBooks() throws EmptyListException {
        // Call the getAllBooks method of bookService to retrieve all books
        List<Book> bookList = bookService.getAllBooks();
        // Create a list to hold AllBooksDto objects
        List<AllBooksDto> allBooksDto = new ArrayList<>();
        // Iterate over the bookList and map each book to AllBooksDto
        for (Book book : bookList) {
            AllBooksDto allBooks = new AllBooksDto();
            AllBooksDto bookDto = modelMapper.map(book, AllBooksDto.class);
            allBooksDto.add(bookDto);
        }
        // Return the list of AllBooksDto with HTTP status OK
        return ResponseEntity.ok(allBooksDto);
    }
}
