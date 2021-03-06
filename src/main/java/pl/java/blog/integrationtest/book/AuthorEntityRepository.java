package pl.java.blog.integrationtest.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AuthorEntityRepository extends JpaRepository<AuthorEntity, Long> {
    boolean existsByName(String name);

    Optional<AuthorEntity> findByName(String name);
}
