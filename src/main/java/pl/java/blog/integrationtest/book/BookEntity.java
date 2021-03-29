package pl.java.blog.integrationtest.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "books")
class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    BookEntity(String title, AuthorEntity author) {
        this.title = title;
        this.author = author;
    }
}
