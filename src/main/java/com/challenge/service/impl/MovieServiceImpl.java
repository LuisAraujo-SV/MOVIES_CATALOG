package com.challenge.service.impl;

import com.challenge.dto.MovieDTO;
import com.challenge.model.entities.MovieEntity;
import com.challenge.model.entities.UserEntity;
import com.challenge.model.mapper.MovieMapper;
import com.challenge.model.repositories.MovieRepository;
import com.challenge.model.repositories.UserRepository;
import com.challenge.service.MovieService;
import com.challenge.utils.JwtTokenUtil;
import com.challenge.utils.MovieValidator;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    private static final Integer DEFAULT_RATING_COUNT = 0;
    private static final Double DEFAULT_AVERAGE_RATING = 0.0;


    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieValidator movieValidator;
    private final MovieMapper movieMapper;
    private final JwtTokenUtil jwtTokenUtil;

    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository, MovieValidator movieValidator, MovieMapper movieMapper, JwtTokenUtil jwtTokenUtil) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.movieValidator = movieValidator;
        this.movieMapper = movieMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public MovieDTO addMovie(HttpServletRequest request, MovieDTO movieDTO) {
        movieValidator.validate(movieDTO);
        MovieEntity movie = movieMapper.toEntity(movieDTO);

        //Default values
        movie.setCreatedDate(LocalDateTime.now());
        movie.setRatingCount(DEFAULT_RATING_COUNT);
        movie.setAverageRating(DEFAULT_AVERAGE_RATING);

        final String email = jwtTokenUtil.extractEmail(request);
        UserEntity user = userRepository.findByEmail(email);
        movie.setCreatedBy(user);

        try {
            movie = movieRepository.save(movie);
            return movieMapper.toDTO(movie);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            LOGGER.error(e.getMessage());
            throw new IllegalArgumentException("Input values violates constraints");
        }
    }

    @Override
    public void deleteMovie(Long id) {
        Integer deleted = movieRepository.deleteMovieById(id);
        if (deleted == 0) throw new IllegalArgumentException("Movie not found");
    }

    @Override
    public MovieDTO updateMovie(HttpServletRequest request, Long id, MovieDTO movieDTO) {
        movieValidator.validate(movieDTO);
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        updateEntity(movieEntity, movieDTO);
        final String email = jwtTokenUtil.extractEmail(request);
        UserEntity user = userRepository.findByEmail(email);
        movieEntity.setCreatedBy(user);

        return movieMapper.toDTO(movieRepository.save(movieEntity));
    }

    @Override
    public Page<MovieDTO> searchMovies(String search, String category, Integer releaseYear, Pageable pageable) {
        return movieMapper.toDTOPage(movieRepository.searchMovies(search, category, releaseYear, pageable));
    }

    /**
     * We don't want to update: createdBy, createdDate and id
     * For Security purpose only
     * @param movieDTO movie input object
     * @return
     */
    private void updateEntity(MovieEntity movieEntity, MovieDTO movieDTO) {
        movieEntity.setName(movieDTO.getName());
        movieEntity.setReleaseYear(movieDTO.getReleaseYear());
        movieEntity.setSynopsis(movieDTO.getSynopsis());
        movieEntity.setCategory(movieDTO.getCategory());
        movieEntity.setImageUrl(movieDTO.getImageUrl());
    }
}
