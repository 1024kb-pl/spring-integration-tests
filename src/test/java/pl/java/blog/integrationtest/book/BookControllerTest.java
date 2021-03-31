package pl.java.blog.integrationtest.book;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = "/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean_database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BookControllerTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void shouldReturn2xxWhenAddBookSuccessfully() throws Exception {
        // given:
        CreateBookModel createBookModel = new CreateBookModel("Great book", "Great author");

        // when:
        RequestEntity<CreateBookModel> request = RequestEntity
                .post(createServerAddress())
                .contentType(MediaType.APPLICATION_JSON)
                .body(createBookModel);

        ResponseEntity<Book> response = restTemplate.exchange(request, Book.class);

        // expect:
        Book body = response.getBody();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Great author", body.getAuthorName());
        assertEquals("Great book", body.getTitle());
    }

    @Test
    void shouldReturn4xxWhenBookTitleIsEmpty() throws Exception {
        // given:
        CreateBookModel createBookModel = new CreateBookModel("", "Great author");

        // when:
        RequestEntity<CreateBookModel> request = RequestEntity
                .post(createServerAddress())
                .contentType(MediaType.APPLICATION_JSON)
                .body(createBookModel);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        // expect:

        assertTrue(response.getStatusCode().is4xxClientError());
        assertEquals("Book has to have a title.", response.getBody());
    }

    @Test
    void shouldReturn4xxWhenBookAuthorIsEmpty() throws Exception {
        // given:
        CreateBookModel createBookModel = new CreateBookModel("Great book", "");

        // when:
        RequestEntity<CreateBookModel> request = RequestEntity
                .post(createServerAddress())
                .contentType(MediaType.APPLICATION_JSON)
                .body(createBookModel);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        // expect:

        assertTrue(response.getStatusCode().is4xxClientError());
        assertEquals("Book author can not be empty.", response.getBody());
    }


    @Test
    void shouldReturnAllExistingBooks() throws Exception {
        // when:
        RequestEntity<Void> request = RequestEntity
                .get(createServerAddress())
                .build();

        ResponseEntity<List<Book>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Book>>() {
        });
        // expect:
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(2, response.getBody().size());
    }

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/book");
    }
}
