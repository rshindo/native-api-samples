package com.github.rshindo;

import com.atilika.kuromoji.ipadic.Tokenizer;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/kuromoji")
public class KuromojiController {

    private Tokenizer tokenizer = new Tokenizer.Builder().build();

    @Post
    public Single<Output> tokens(@Body Input input) {
        List<String> tokens = tokenizer.tokenize(input.getText())
                .stream()
                .map(token -> token.getSurface())
                .collect(Collectors.toList());
        return Single.just(new Output(tokens));
    }
}
