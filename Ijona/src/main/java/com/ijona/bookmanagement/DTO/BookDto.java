package com.ijona.bookmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer bookId;
    private String bookIsbn;
    private String bookName;
    private String bookGenre;
    private String authorName;
    private String publisher;
    private Integer bookQuantity;
}
