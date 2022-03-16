package com.java.tests.api_tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.java.tests.api_tests.helpers.CustomerAllureListener.withCustomTemplates;

public class BaseTest {

    @BeforeAll
    public static void addRestAssured() {
        RestAssured.filters(withCustomTemplates());
    }
}
