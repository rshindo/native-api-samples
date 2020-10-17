package com.github.rshindo;

import com.atilika.kuromoji.ipadic.Tokenizer;
import io.smallrye.mutiny.Uni;
import io.vertx.reactivex.ext.web.RoutingContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
//@ApplicationScoped
public class KuromojiResource {

    private Tokenizer tokenizer = new Tokenizer.Builder().build();

    @POST
    @Path("/kuromoji")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Route(methods = HttpMethod.POST, path = "/kuromoji",
//            consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Uni<Output> kuromoji(Input input) {
//        Input input = ctx.getBodyAsJson().mapTo(Input.class);
        List<String> tokens = tokenizer.tokenize(input.getText())
                .stream()
                .map(token -> token.getSurface())
                .collect(Collectors.toList());
        return Uni.createFrom().item(new Output(tokens));
    }
}