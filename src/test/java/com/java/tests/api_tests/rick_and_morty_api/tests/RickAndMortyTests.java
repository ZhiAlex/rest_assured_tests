package com.java.tests.api_tests.rick_and_morty_api.tests;

import com.java.tests.api_tests.rick_and_morty_api.RandMBaseTest;
import com.java.tests.api_tests.rick_and_morty_api.model.Character;
import com.java.tests.api_tests.rick_and_morty_api.model.Episode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RickAndMortyTests extends RandMBaseTest {

    private String name = "Morty Smith";

    @Test
    @DisplayName("get Morty info")
    void getMortyInfoTest() {

        Character morty = getCharactersByName(name);

        step("Проверка что получен Морти", () -> assertEquals(name, morty.getName()));
    }

    @Test
    @DisplayName("get last episode with Morty")
    void getLastEpisodeWithMortyTest() {

        Character morty = getCharactersByName(name);
        String lastEpisode = getCharactersEpisode(morty, morty.getEpisodes().size() - 1);

        step("Проверка, что получен последний эпизод", () ->
                assertEquals(Integer.parseInt(lastEpisode.split("https://rickandmortyapi.com/api/episode/")[1]),
                        morty.getEpisodes().size()));
    }


    //лень было придумывать как вытащить id персонажа, поэтому захардкодил 825
    @Test
    @DisplayName("get last character in last episode")
    void getLastCharacterInLastEpisode() {

        int episodeAmount = getEpisodeAmount();
        Episode lastEpisode = getEpisodeById(episodeAmount);
        Character lastCharacter = getCharacter(lastEpisode, lastEpisode.getCharacter().size() - 1);

        step("Проверка что получен последний персонаж", () -> assertEquals(lastCharacter.getId(), 825));
    }
}
