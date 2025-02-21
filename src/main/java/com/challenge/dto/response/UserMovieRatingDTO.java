package com.challenge.dto.response;

import com.challenge.dto.MovieDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserMovieRatingDTO extends MovieDTO {

    private Integer personalRating;

    public UserMovieRatingDTO(Long id, String name, Integer releaseYear, String synopsis,
                              String category, String imageUrl, LocalDateTime createdDate,
                              Double averageRating, Integer personalRating) {
        super(id, name, releaseYear, synopsis, category, imageUrl, createdDate, averageRating);
        this.personalRating = personalRating;
    }
}
