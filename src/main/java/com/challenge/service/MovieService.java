package com.challenge.service;

import com.challenge.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface MovieService {

    public MovieDTO addMovie(HttpServletRequest request, MovieDTO movieDTO);

    public void deleteMovie(Long id);

    public MovieDTO updateMovie(HttpServletRequest request, Long id, MovieDTO movieDTO);

    public Page<MovieDTO> searchMovies(String search,
                                    String category,
                                    Integer releaseYear,
                                    Pageable pageable);
}
