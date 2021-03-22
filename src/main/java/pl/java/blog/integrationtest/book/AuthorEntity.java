package pl.java.blog.integrationtest.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;

    AuthorEntity(String name) {
        this.name = name;
    }
}
