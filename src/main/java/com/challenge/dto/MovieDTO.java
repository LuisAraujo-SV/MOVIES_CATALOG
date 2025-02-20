package com.challenge.dto;


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
    private LocalDateTime createdDate;
    private Double averageRating;

}
