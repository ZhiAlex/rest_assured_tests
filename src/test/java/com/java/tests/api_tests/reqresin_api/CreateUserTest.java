package com.java.tests.api_tests.reqresin_api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.java.tests.api_tests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest extends BaseTest {

    @Test
    @DisplayName("user creation check")
    void createUserTest() {

        JsonObject userBody = new JsonObject();

        try {
            JsonElement element = JsonParser.parseReader(new FileReader("src/test/resources/person.json"));
            userBody = element.getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        userBody.addProperty("name", "Kek");
        userBody.addProperty("job", "clown");

        given()
                .contentType(JSON)
                .body(userBody.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is(userBody.get("name").getAsString()))
                .body("job", is(userBody.get("job").getAsString()))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }
}
