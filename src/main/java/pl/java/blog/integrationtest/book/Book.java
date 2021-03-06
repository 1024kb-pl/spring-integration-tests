package pl.java.blog.integrationtest.book;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
class Book {
    private final Long id;
    private final String title;
    private final String authorName;
}
