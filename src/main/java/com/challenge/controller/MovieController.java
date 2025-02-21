package com.challenge.controller;

import com.challenge.dto.MovieDTO;
import com.challenge.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> searchMovies(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer releaseYear,
            Pageable pageable
    ) {
        return ResponseEntity.ok(movieService.searchMovies(search, category, releaseYear, pageable));
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(HttpServletRequest request,
                                                @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.addMovie(request, movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(HttpServletRequest request,
                                                @PathVariable("id") Long id,
                                                @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.updateMovie(request, id, movieDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDTO> deleteMovie(HttpServletRequest request,
                                                @PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
