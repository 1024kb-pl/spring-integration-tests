package pl.java.blog.integrationtest.book;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookEntityRepository extends JpaRepository<BookEntity, String> {
}
