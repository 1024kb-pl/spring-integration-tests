package pl.java.blog.integrationtest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.java.blog.integrationtest.book.Book;
import pl.java.blog.integrationtest.book.BookService;
import pl.java.blog.integrationtest.book.CreateBookModel;
import pl.java.blog.integrationtest.exception.ValidationException;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody CreateBookModel createBookModel) {
        try {
            Book book = bookService.addBook(createBookModel);
            return ResponseEntity.ok(book);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }
}
