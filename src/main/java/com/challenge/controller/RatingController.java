package com.challenge.controller;

import com.challenge.dto.response.UserMovieRatingDTO;
import com.challenge.dto.request.RateMovieDTO;
import com.challenge.dto.response.CustomResponseDTO;
import com.challenge.service.RatingService;
import com.challenge.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @ApiOperation(
            value = "rate a movie",
            authorizations = { @Authorization(value= JwtTokenUtil.AUTHORIZATION_HEADER) })
    @PostMapping
    public ResponseEntity<CustomResponseDTO> rateMovie(HttpServletRequest request,
                                                       @Valid @RequestBody RateMovieDTO rateMovieDTO) {
        CustomResponseDTO responseDTO = null;
        try {
            responseDTO = ratingService.rateMovie(request, rateMovieDTO);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "delete your rating of a movie",
            authorizations = { @Authorization(value= JwtTokenUtil.AUTHORIZATION_HEADER) })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponseDTO> removeRating(HttpServletRequest request,
                                                          @PathVariable("id") Long movieId) {
        try {
            ratingService.removeRating(request, movieId);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "get all your movies ratings",
            authorizations = { @Authorization(value= JwtTokenUtil.AUTHORIZATION_HEADER) })
    @GetMapping("/all")
    public ResponseEntity<List<UserMovieRatingDTO>> getAll(HttpServletRequest request) {
        return new ResponseEntity<>(ratingService.findAllRating(request), HttpStatus.OK);
    }
}
