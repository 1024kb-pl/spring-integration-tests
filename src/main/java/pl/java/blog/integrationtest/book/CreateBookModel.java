package pl.java.blog.integrationtest.book;


import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CreateBookModel {
    private String title;
    private String authorName;
}
