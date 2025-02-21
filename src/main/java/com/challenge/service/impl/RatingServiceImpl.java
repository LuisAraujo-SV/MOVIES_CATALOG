package com.challenge.service.impl;

import com.challenge.dto.response.UserMovieRatingDTO;
import com.challenge.dto.request.RateMovieDTO;
import com.challenge.dto.response.CustomResponseDTO;
import com.challenge.model.entities.MovieEntity;
import com.challenge.model.entities.RatingEntity;
import com.challenge.model.entities.UserEntity;
import com.challenge.model.repositories.MovieRepository;
import com.challenge.model.repositories.RatingRepository;
import com.challenge.model.repositories.UserRepository;
import com.challenge.service.RatingService;
import com.challenge.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public RatingServiceImpl(MovieRepository movieRepository, RatingRepository ratingRepository, UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public CustomResponseDTO rateMovie(HttpServletRequest request, RateMovieDTO rateMovieDTO) {
        CustomResponseDTO responseDTO = new CustomResponseDTO();
        responseDTO.setCode(CustomResponseDTO.ERROR_CODE);
        responseDTO.setMessage(CustomResponseDTO.ERROR_MESSAGE);

        final String email = jwtTokenUtil.extractEmail(request);
        UserEntity user = userRepository.findByEmail(email);

        MovieEntity movieEntity = movieRepository.findById(rateMovieDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));


        RatingEntity ratingEntity = ratingRepository
                .findByUserAndMovie(user.getId(), rateMovieDTO.getMovieId())
                .orElse(new RatingEntity());

        ratingEntity.setUser(user);
        ratingEntity.setMovie(movieEntity);
        ratingEntity.setRating(rateMovieDTO.getRating());

        ratingRepository.save(ratingEntity);

        responseDTO.setCode(CustomResponseDTO.OK_CODE);
        responseDTO.setMessage(CustomResponseDTO.OK_MESSAGE);

        return responseDTO;
    }

    @Override
    public void removeRating(HttpServletRequest request, Long movieId) {

        final String email = jwtTokenUtil.extractEmail(request);
        UserEntity user = userRepository.findByEmail(email);

        RatingEntity ratingEntity = ratingRepository
                .findByUserAndMovie(user.getId(), movieId)
                .orElseThrow(() -> new IllegalArgumentException("Rating for movie not found"));

        if (ratingEntity != null) {
            ratingRepository.delete(ratingEntity);
        }
    }

    @Override
    public List<UserMovieRatingDTO> findAllRating(HttpServletRequest request) {

        final String email = jwtTokenUtil.extractEmail(request);
        UserEntity user = userRepository.findByEmail(email);

        return ratingRepository.findAllRatings(user.getId());
    }
}
