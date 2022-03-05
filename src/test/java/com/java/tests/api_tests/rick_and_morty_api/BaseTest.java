package com.java.tests.api_tests.rick_and_morty_api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.java.tests.api_tests.rick_and_morty_api.model.Character;
import com.java.tests.api_tests.rick_and_morty_api.model.Episode;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.when;

public class BaseTest {

    private String baseUrl = "https://rickandmortyapi.com/api";

    public List<Character> getCharacters() {
        JsonElement charactersJsonElement = JsonParser.parseString(
                when()
                        .get(baseUrl + "/character")
                        .then()
                        .statusCode(200)
                        .extract().response().asString());

        JsonArray charactersJson = charactersJsonElement.getAsJsonObject().get("results").getAsJsonArray();
        List<Character> characters = new ArrayList<>();
        for (JsonElement character : charactersJson) {
            characters.add(new Gson().fromJson(character, Character.class));
        }
        return characters;
    }

    public Character getCharactersByName(String name) {
        return getCharacters().stream().filter(character -> character.getName().equals(name))
                .findAny().orElse(null);
    }

    public String getCharactersEpisode(Character character, int series) {
        return character.getEpisodes().get(series);
    }

    public int getEpisodeAmount() {
        Response episodes =
                when()
                        .get(baseUrl + "/episode")
                        .then()
                        .statusCode(200)
                        .extract().response();
        return episodes.path("info.count");
    }

    public Episode getEpisodeById(int id) {

        JsonElement episodeJsonElement = JsonParser.parseString(
                when()
                        .get(baseUrl + "/episode/" + id)
                        .then()
                        .statusCode(200)
                        .extract().response().asString());
        return new Gson().fromJson(episodeJsonElement, Episode.class);
    }

    public Character getCharacter(Episode episode, int count) {
        JsonElement characte = JsonParser.parseString(
                when()
                        .get(episode.getCharacter().get(count))
                        .then()
                        .statusCode(200)
                        .extract().response().asString());
        return new Gson().fromJson(characte, Character.class);
    }
}
