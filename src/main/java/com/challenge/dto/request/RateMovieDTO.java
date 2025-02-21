package com.challenge.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RateMovieDTO implements Serializable {

    @NotNull
    private Long movieId;

    @Min(1) @Max(5)
    private Integer rating;
}
