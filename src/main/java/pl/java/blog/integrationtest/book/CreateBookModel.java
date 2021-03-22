package pl.java.blog.integrationtest.book;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CreateBookModel {
    private String title;
    private String authorName;
}
