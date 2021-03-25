package pl.java.blog.integrationtest.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookService {
    private final AuthorEntityRepository authorEntityRepository;
    private final BookEntityRepository bookEntityRepository;
    private final BookValidator bookValidator;

    public Book addBook(CreateBookModel book) {
        bookValidator.validate(book);

        String authorName = book.getAuthorName();
        AuthorEntity author = getOrCreateAuthor(authorName);

        BookEntity bookEntity = bookEntityRepository.save(new BookEntity(book.getTitle(), author));

        return mapToModel(bookEntity);
    }

    private Book mapToModel(BookEntity bookEntity) {
        return Book.builder()
                .id(bookEntity.getId())
                .authorName(bookEntity.getAuthor().getName())
                .title(bookEntity.getTitle())
                .build();
    }

    private AuthorEntity getOrCreateAuthor(String authorName) {
        if (authorEntityRepository.existsByName(authorName)) {
            return authorEntityRepository.save(new AuthorEntity(authorName));
        }

        return authorEntityRepository.findByName(authorName)
                .orElseThrow(() -> new RuntimeException("Author=" + authorName + " does not exist."));
    }

    public List<Book> getBooks() {
        return bookEntityRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }
}
