# REST API for Book Management System.

The Book Management System is a Java-based web application that provides functionality for managing books. It allows users to perform operations such as adding new books, updating existing books, deleting books, and retrieving book information.

## Features

- **Add Book**: Users can add new books to the system by providing details such as ISBN, book name, genre, author name, publisher, and quantity.
- **Update Book**: Existing books can be updated with new information, including book name, genre, author name, publisher, and quantity.
- **Delete Book**: Books can be deleted from the system by specifying the book ID.
- **Get Book by ID**: Users can retrieve detailed information about a book by providing its ID.
- **Get All Books**: Users can retrieve a list of all books available in the system.


# Tech Stack

- Java
- Spring Boot
- Spring MVC
- Hibernate
- ModelMapper
- Spring Data JPA
- MySQL
- Maven
- Git

# API Endpoints
The Book Management System provides the following endpoints:

### Add Book

Adds a new book to the system.

- Method: POST
- Endpoint: /book/add
- Request Body: AddBookRto

### Update Book

Updates an existing book with the specified book ID.

- Method: PUT
- Endpoint: /book/update/{bookId}
- Request Body: UpdateBookRto
- Path Variable: bookId (Integer)

### Delete Book

Deletes a book with the specified book ID from the system.

- Method: DELETE
- Endpoint: /book/delete/{bookId}
- Path Variable: bookId (Integer)

### Get Book by ID

Retrieves detailed information about a book with the specified book ID.

- Method: GET
- Endpoint: /book/{bookId}
- Path Variable: bookId (Integer)

### Get All Books

Retrieves a list of all books available in the system.

- Method: GET
- Endpoint: /book/allbooks

## Data Transfer Objects (DTO) and Request Transfer Objects (RTO)

**DTO (Data Transfer Object):**
DTO stands for Data Transfer Object. It is a design pattern used to transfer data between different layers of an application, such as between the controller and service layers. In this project, DTOs are used to encapsulate data related to books in a structured format. They are used for communication between the controller and the client.

The following DTOs are used in the project:
- `BookDto`: Represents the book data to be transferred to the client. It contains information such as the book's ID, name, genre, author name, publisher, and quantity.
- `AllBooksDto`: Represents the book data to be transferred to the client when retrieving a list of all books. It contains a subset of information from the `BookDto` to provide a concise representation of all books.

**RTO (Request Transfer Object):**
RTO stands for Request Transfer Object. It is a type of DTO that is specifically used to encapsulate data received from client requests. In this project, RTOs are used to represent the data received from the client when making requests to add or update books.

The following RTOs are used in the project:
- `AddBookRto`: Represents the data received from the client when adding a new book. It contains information such as the book's ISBN, name, genre, author name, publisher, and quantity.
- `UpdateBookRto`: Represents the data received from the client when updating an existing book. It contains information such as the book's name, genre, author name, publisher, and quantity. Additionally, it includes the book ID as a path variable in the corresponding request.

Using DTOs and RTOs helps in decoupling the internal representation of data from the external representation and provides a structured way to transfer data between different layers of the application.

# Error Handling
The Book Management System includes a global exception handler that handles various exceptions thrown during the execution of the API endpoints. The exceptions are handled and appropriate error responses are returned to the client.

# Contributors
- [Yatin Kumar](https://github.com/yatinkumar01)
