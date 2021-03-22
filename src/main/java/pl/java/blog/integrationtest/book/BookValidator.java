package pl.java.blog.integrationtest.book;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.java.blog.integrationtest.exception.ValidationException;

@Component
class BookValidator {
    void validate(CreateBookModel bookModel) {
        if (!StringUtils.hasLength(bookModel.getAuthorName())) {
            throw new ValidationException("Book author can not be empty.");
        }

        if (!StringUtils.hasLength(bookModel.getTitle())) {
            throw new ValidationException("Book has to have a title.");
        }
    }
}
