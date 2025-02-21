package com.challenge.controller;

import com.challenge.dto.MovieDTO;
import com.challenge.service.MovieService;
import com.challenge.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ApiOperation(value = "search movies", notes = "swagger doesn't generate well the Pageable param <br>" +
            "Example params -> <strong>?page=1&size=1&sort=averageRating,asc</strong> <br>" +
            "It looks like this swagger version doesn't have support. Better test this on postman")
    @GetMapping
    public ResponseEntity<Page<MovieDTO>> searchMovies(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer releaseYear,
            Pageable pageable
    ) {
        return ResponseEntity.ok(movieService.searchMovies(search, category, releaseYear, pageable));
    }

    @ApiOperation(
            value = "register a new movie",
            authorizations = { @Authorization(value= JwtTokenUtil.AUTHORIZATION_HEADER) })
    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(HttpServletRequest request,
                                                @Valid @RequestBody MovieDTO movieDTO) {
        try {
            return new ResponseEntity<>(movieService.addMovie(request, movieDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(
            value = "update the values of movie /{id}",
            authorizations = { @Authorization(value=JwtTokenUtil.AUTHORIZATION_HEADER) })
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(HttpServletRequest request,
                                                @PathVariable("id") Long id,
                                                @Valid @RequestBody MovieDTO movieDTO) {
        try {
            return new ResponseEntity<>(movieService.updateMovie(request, id, movieDTO), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(movieDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(
            value = "delete the specific movie /{id}",
            authorizations = { @Authorization(value=JwtTokenUtil.AUTHORIZATION_HEADER) })
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDTO> deleteMovie(HttpServletRequest request,
                                                @PathVariable("id") Long id) {
        try {
            movieService.deleteMovie(id);
        } catch (IllegalArgumentException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
