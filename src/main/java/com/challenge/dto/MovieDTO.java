package com.challenge.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MovieDTO implements Serializable {

    private Long id;
    private String name;
    private Integer releaseYear;
    private String synopsis;
    private String category;
    private String imageUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double averageRating;

}
