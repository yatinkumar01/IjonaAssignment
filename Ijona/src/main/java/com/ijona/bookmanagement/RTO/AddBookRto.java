package com.ijona.bookmanagement.RTO;

import com.ijona.bookmanagement.Constants.ExceptionConstants;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRto {

    // Represents the ISBN of the book
    @NotEmpty
    @Size(min = 3, max = 5, message = ExceptionConstants.MAXIMUM_CHARACTER)
    private String bookIsbn;

    // Represents the name of the book
    @NotEmpty
    @Size(min = 3, message = ExceptionConstants.MINIMUM_CHARACTER)
    private String bookName;

    // Represents the genre of the book
    @NotEmpty
    private String bookGenre;

    // Represents the name of the author of the book
    @NotEmpty
    @Size(min = 3, message = ExceptionConstants.MINIMUM_CHARACTER)
    private String authorName;

    // Represents the publisher of the book
    @NotEmpty
    @Size(min = 3, message = ExceptionConstants.MINIMUM_CHARACTER)
    private String publisher;

    // Represents the quantity of the book
    @Digits(integer = 2, fraction = 0, message = ExceptionConstants.MAXIMUM_QUANTITY)
    private Integer bookQuantity;
}

