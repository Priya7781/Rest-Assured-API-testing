package com.priya.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class ConduitApiTest {
    private static final String BASE_URI = "https://conduit-api.bondaracademy.com";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void simpleGetRequestReturnsTagsContainingTest() {
        given()
                .when()
                .get("/api/tags")
                .then()
                .log().body()
                .statusCode(200)
                .body("tags", hasItem("Test"));
    }

    @Test
    public void simplePostRequestCreatesArticle() {
        String title = "AQCX TEST " + System.currentTimeMillis();
        String articlePayload = """
                {
                  "article": {
                    "title": "%s",
                    "description": "jhghjghj",
                    "body": "jhfjhfjf",
                    "tagList": []
                  }
                }
                """.formatted(title);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo1MTQwMH0sImlhdCI6MTc4MjA0Nzg5MiwiZXhwIjoxNzg3MjMxODkyfQ.5yI7nmsgeimrcqw8wJb5WFAIeTd4MDTQjjE0npAiiIk")
                .body(articlePayload)
                .when()
                .post("/api/articles/")
                .then()
                .log().body()
                .statusCode(201)
                .body("article.title", equalTo(title))
                .body("article.slug", notNullValue());
    }
}


