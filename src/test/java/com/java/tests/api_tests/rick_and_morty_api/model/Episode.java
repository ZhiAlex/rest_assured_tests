package com.java.tests.api_tests.rick_and_morty_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @SerializedName("characters")
    private ArrayList<String> characters;

    public ArrayList<String> getCharacter() {
        return characters;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characters=" + characters +
                '}';
    }
}
