package com.github.rshindo.kuromoji;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class KuromojiController {

    private Tokenizer tokenizer = new Tokenizer.Builder().build();

    @PostMapping(path = "/kuromoji",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Output kuromoji(@RequestBody Input input) {
        List<String> tokens = tokenizer.tokenize(input.getText())
                .stream()
                .map(Token::getSurface)
                .collect(Collectors.toList());
        return new Output(tokens);
    }
}
