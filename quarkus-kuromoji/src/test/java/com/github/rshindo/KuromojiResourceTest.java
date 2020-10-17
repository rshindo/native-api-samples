package com.github.rshindo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class KuromojiResourceTest {

    @Test
    public void testHelloEndpoint() {
        Input input = new Input();
        input.setText("僕はクマ");
        given()
                .contentType(ContentType.JSON)
                .body(input)
                .when()
                .post("/kuromoji")
                .then()
                .statusCode(200)
                .body("tokens", equalTo(List.of("僕", "は", "クマ")));
    }

}