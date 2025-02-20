package com.challenge.service;

import com.challenge.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

    public MovieDTO addMovie(MovieDTO movieDTO);

    public void deleteMovie(Long id);

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    public Page<MovieDTO> searchMovies(String search,
                                    String category,
                                    Integer releaseYear,
                                    Pageable pageable);
}
