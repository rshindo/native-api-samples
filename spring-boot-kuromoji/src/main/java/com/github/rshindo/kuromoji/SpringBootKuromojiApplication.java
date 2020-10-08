package com.github.rshindo.kuromoji;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@SpringBootApplication(proxyBeanMethods = false)
public class SpringBootKuromojiApplication {

    private Tokenizer tokenizer = new Tokenizer.Builder().build();

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKuromojiApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .POST("/kuromoji", accept(MediaType.TEXT_PLAIN),
                        this::handleKuromojiRequest)
                .build();
    }

    Mono<ServerResponse> handleKuromojiRequest(ServerRequest request) {
        Mono<Output> body = request.bodyToMono(Input.class)
                .map(in -> tokenizer.tokenize(in.getText()))
                .map(tokens -> new Output(tokens.stream()
                        .map(Token::getSurface)
                        .collect(Collectors.toList())));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(body, new ParameterizedTypeReference<Output>() {
                });
    }
}
