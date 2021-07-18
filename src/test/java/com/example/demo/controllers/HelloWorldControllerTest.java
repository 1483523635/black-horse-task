package com.example.demo.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import org.junit.jupiter.api.Test;

class HelloWorldControllerTest {

    @Test
    void name() {
        given().contentType(APPLICATION_JSON_VALUE)
            .standaloneSetup(new HelloWorldController())
            .when()
            .get("/api/test/demo")
            .then()
            .statusCode(200);
    }
}