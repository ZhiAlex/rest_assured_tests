package com.java.tests.api_tests.rick_and_morty_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @SerializedName("episode")
    private ArrayList<String> episodes;

    public String getName() {
        return name;
    }

    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", episodes=" + episodes +
                '}';
    }
}
