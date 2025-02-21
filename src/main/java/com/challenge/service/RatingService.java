package com.challenge.service;

import com.challenge.dto.response.UserMovieRatingDTO;
import com.challenge.dto.request.RateMovieDTO;
import com.challenge.dto.response.CustomResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RatingService {
    public CustomResponseDTO rateMovie(HttpServletRequest request, RateMovieDTO rateMovieDTO);

    public void removeRating(HttpServletRequest request, Long movieId);
    public List<UserMovieRatingDTO> findAllRating(HttpServletRequest request);

}
