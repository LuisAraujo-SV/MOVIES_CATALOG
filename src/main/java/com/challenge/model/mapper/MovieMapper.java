package com.challenge.model.mapper;

import com.challenge.dto.MovieDTO;
import com.challenge.model.entities.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    @Autowired
    private UserMapper userMapper;

    public MovieDTO toDTO(MovieEntity movieEntity) {
        if (movieEntity == null) return null;
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setName(movieEntity.getName());
        movieDTO.setReleaseYear(movieEntity.getReleaseYear());
        movieDTO.setSynopsis(movieEntity.getSynopsis());
        movieDTO.setCategory(movieEntity.getCategory());
        movieDTO.setImageUrl(movieEntity.getImageUrl());
        movieDTO.setCreatedDate(movieEntity.getCreatedDate());
        movieDTO.setAverageRating(movieEntity.getAverageRating());
        return movieDTO;
    }

    public MovieEntity toEntity(MovieDTO movieDTO) {
        if (movieDTO == null) return null;
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movieDTO.getId());
        movieEntity.setName(movieDTO.getName());
        movieEntity.setReleaseYear(movieDTO.getReleaseYear());
        movieEntity.setSynopsis(movieDTO.getSynopsis());
        movieEntity.setCategory(movieDTO.getCategory());
        movieEntity.setImageUrl(movieDTO.getImageUrl());
        movieEntity.setCreatedDate(movieDTO.getCreatedDate());
        return movieEntity;
    }

    public Page<MovieDTO> toDTOPage(Page<MovieEntity> movieEntityPage) {
        List<MovieDTO> movieDTOList = movieEntityPage.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(movieDTOList, movieEntityPage.getPageable(), movieEntityPage.getTotalPages());
    }
}
