package com.ijona.bookmanagement.Service;

import com.ijona.bookmanagement.Constants.ExceptionConstants;
import com.ijona.bookmanagement.Repository.BookRepo;
import com.ijona.bookmanagement.Entities.Book;
import com.ijona.bookmanagement.Enums.BookGenre;
import com.ijona.bookmanagement.Exceptions.AlreadyExistException;
import com.ijona.bookmanagement.Exceptions.EmptyListException;
import com.ijona.bookmanagement.Exceptions.NotFoundException;
import com.ijona.bookmanagement.RTO.UpdateBookRto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    /**
     * Adds a new book to the system.
     *
     * @param book The book object to be added.
     * @return The newly added book.
     * @throws AlreadyExistException if a book with the same ISBN already exists.
     * @throws NotFoundException     if the book genre is not found in the system.
     */
    @Override
    public Book addBook(Book book) throws AlreadyExistException, NotFoundException {
        // Check if a book with the same ISBN already exists
        Optional<Book> bookByIsbn = bookRepo.findByBookIsbn(book.getBookIsbn());
        if (bookByIsbn.isPresent()) {
            throw new AlreadyExistException(ExceptionConstants.BOOK_ALREADY_EXIST, book.getBookIsbn());
        }

        boolean isGenreExist = false;
        // Check if the book genre is valid
        for (BookGenre genre : BookGenre.values()) {
            if (genre.name().equals(book.getBookGenre())) {
                isGenreExist = true;
                break;
            }
        }
        if (!isGenreExist) {
            throw new NotFoundException(ExceptionConstants.INVALID_GENRE, book.getBookGenre());
        }

        // Save the newly added book in the repository
        Book newlyAddedBook = bookRepo.save(book);
        return newlyAddedBook;
    }

    /**
     * Updates an existing book in the system.
     *
     * @param book   The updated book information.
     * @param bookId The ID of the book to be updated.
     * @return The updated book.
     * @throws NotFoundException if the book to be updated is not found in the system or the book genre is not found.
     */
    @Override
    public Book updateBook(UpdateBookRto book, Integer bookId) throws NotFoundException {
        // Find the book to be updated by its ID
        Book bookToBeUpdated = bookRepo.findById(bookId).orElseThrow(() -> new NotFoundException(ExceptionConstants.BOOK_DONT_EXIST, bookId));

        boolean isGenreExist = false;
        // Check if the updated book genre is valid
        for (BookGenre genre : BookGenre.values()) {
            if (genre.name().equals(book.getBookGenre())) {
                isGenreExist = true;
                break;
            }
        }
        if (!isGenreExist) {
            throw new NotFoundException(ExceptionConstants.INVALID_GENRE, book.getBookGenre());
        }

        // Update the book information
        bookToBeUpdated.setBookName(book.getBookName());
        bookToBeUpdated.setBookGenre(book.getBookGenre());
        bookToBeUpdated.setAuthorName(book.getAuthorName());
        bookToBeUpdated.setPublisher(book.getPublisher());
        bookToBeUpdated.setBookQuantity(book.getBookQuantity());

        // Save the updated book in the repository
        Book updatedBook = bookRepo.save(bookToBeUpdated);
        return updatedBook;
    }

    /**
     * Deletes a book from the system.
     *
     * @param bookId The ID of the book to be deleted.
     * @return The ID of the deleted book.
     * @throws NotFoundException if the book to be deleted is not found in the system.
     */
    @Override
    public Integer deleteBook(Integer bookId) throws NotFoundException {
        // Find the book to be deleted by its ID
        Book bookToBeDeleted = bookRepo.findById(bookId).orElseThrow(() -> new NotFoundException(ExceptionConstants.BOOK_DONT_EXIST, bookId));

        // Delete the book from the repository
        bookRepo.delete(bookToBeDeleted);
        return bookId;
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param bookId The ID of the book to be retrieved.
     * @return The book with the specified ID.
     * @throws NotFoundException if the book with the specified ID is not found in the system.
     */
    @Override
    public Book getBookById(Integer bookId) throws NotFoundException {
        // Find the book by its ID
        Book bookById = bookRepo.findById(bookId).orElseThrow(() -> new NotFoundException(ExceptionConstants.BOOK_DONT_EXIST + bookId, bookId));
        return bookById;
    }

    /**
     * Retrieves all books in the system.
     *
     * @return A list of all books.
     * @throws EmptyListException if there are no books in the system.
     */
    @Override
    public List<Book> getAllBooks() throws EmptyListException {
        // Retrieve all books from the repository
        List<Book> allBooks = bookRepo.findAll();

        // Check if the list of books is empty
        if (allBooks.isEmpty()) {
            throw new EmptyListException(ExceptionConstants.EMPTY_LIST);
        }
        return allBooks;
    }
}

