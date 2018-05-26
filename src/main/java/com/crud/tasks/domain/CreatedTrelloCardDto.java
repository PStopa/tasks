package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUlr;

    @JsonProperty("badges")
    private BadgesDto badgesDto;

    public CreatedTrelloCardDto(String id, String name, String shortUlr) {
        this.id = id;
        this.name = name;
        this.shortUlr = shortUlr;
    }

    public CreatedTrelloCardDto(String id, String name, String shortUlr, BadgesDto badgesDto) {
        this.id = id;
        this.name = name;
        this.shortUlr = shortUlr;
        this.badgesDto = badgesDto;
    }

    public CreatedTrelloCardDto() {
    }
}
