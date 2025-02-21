package com.challenge.utils;

import com.challenge.dto.MovieDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovieValidator {
    public void validate(MovieDTO movieDTO) {

        if (movieDTO == null) {
            throw new NullPointerException("Movie cannot be null");
        }
        if (StringUtils.isEmpty(movieDTO.getName())) {
            throw new IllegalArgumentException("Movie name cannot be empty");
        }
        if (movieDTO.getReleaseYear() < 1900) {
            throw new IllegalArgumentException("Invalid release year");
        }
        if (movieDTO.getReleaseYear() > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Invalid release year");
        }
        if (StringUtils.isEmpty(movieDTO.getSynopsis())) {
            throw new IllegalArgumentException("Movie synopsis cannot be null");
        }
        if (StringUtils.isEmpty(movieDTO.getCategory())) {
            throw new IllegalArgumentException("Movie category cannot be null");
        }
    }
}
