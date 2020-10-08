package com.github.rshindo;

import com.atilika.kuromoji.ipadic.Token;
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
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> kuromoji(String text) {
        return tokenizer.tokenize(text).stream()
                .map(token -> token.getSurface())
                .collect(Collectors.toList());
    }
}