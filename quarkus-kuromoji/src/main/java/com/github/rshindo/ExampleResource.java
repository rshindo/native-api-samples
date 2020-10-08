package com.github.rshindo;

import com.atilika.kuromoji.ipadic.Tokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class ExampleResource {

    private Tokenizer tokenizer = new Tokenizer.Builder().build();

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @POST
    @Path("/kuromoji")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Output kuromoji(Input input) {
        List<String> tokens = tokenizer.tokenize(input.getText()).stream()
                .map(token -> token.getSurface())
                .collect(Collectors.toList());
        return new Output(tokens);
    }
}