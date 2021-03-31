package pl.java.blog.integrationtest.book;


import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
class CreateBookModel {
    private String title;
    private String authorName;
}
