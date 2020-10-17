package com.github.rshindo.kuromoji;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringBootKuromojiApplicationTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient webTestClient;

    @PostConstruct
    public void init() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void testKuromojiApi() {
        Input input = new Input();
        input.setText("僕はクマ");
        Output output = new Output(List.of("僕", "は", "クマ"));
        webTestClient.post().uri("/kuromoji")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(input)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Output.class).isEqualTo(output);
    }

}
