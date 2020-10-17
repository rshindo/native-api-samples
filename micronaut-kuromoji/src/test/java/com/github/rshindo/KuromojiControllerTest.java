package com.github.rshindo;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class KuromojiControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void testKuromoji() {
        Input input = new Input();
        input.setText("僕はクマ");
        MutableHttpRequest<Input> request = HttpRequest.POST("/kuromoji", input)
                .contentEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON);
        Output response = client.toBlocking().retrieve(request, Output.class);

        assertNotNull(response);
        assertEquals(List.of("僕", "は", "クマ"), response.getTokens());
    }
}
