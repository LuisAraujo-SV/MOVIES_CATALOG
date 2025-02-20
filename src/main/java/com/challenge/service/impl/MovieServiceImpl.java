package com.challenge.service.impl;

import com.challenge.dto.MovieDTO;
import com.challenge.model.entities.MovieEntity;
import com.challenge.model.mapper.MovieMapper;
import com.challenge.model.repositories.MovieRepository;
import com.challenge.service.MovieService;
import com.challenge.utils.MovieValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieValidator movieValidator;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieValidator movieValidator, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieValidator = movieValidator;
        this.movieMapper = movieMapper;
    }


    @Override
    public MovieDTO addMovie(MovieDTO movieDTO) {
        movieValidator.validate(movieDTO);
        MovieEntity movie = movieMapper.toEntity(movieDTO);
        return movieMapper.toDTO(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        movieValidator.validate(movieDTO);
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        updateEntity(movieEntity, movieDTO);
        return movieMapper.toDTO(movieRepository.save(movieEntity));
    }

    @Override
    public Page<MovieDTO> getMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieMapper.toDTOPage(movieRepository.findAll(pageable));
    }

    /**
     * We don't want to update: createdBy, createdDate and id
     * For Security purpose only
     * @param movieDTO movie input object
     * @return
     */
    private void updateEntity(MovieEntity movieEntity, MovieDTO movieDTO) {
        movieEntity.setName(movieEntity.getName());
        movieEntity.setReleaseYear(movieEntity.getReleaseYear());
        movieEntity.setSynopsis(movieEntity.getSynopsis());
        movieEntity.setCategory(movieEntity.getCategory());
        movieEntity.setImageUrl(movieEntity.getImageUrl());
    }
}
