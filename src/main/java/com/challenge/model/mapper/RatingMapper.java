package com.challenge.model.mapper;

import com.challenge.dto.RatingDTO;
import com.challenge.model.entities.RatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private UserMapper userMapper;

    public RatingDTO toDTO(RatingEntity ratingEntity) {
        if (ratingEntity == null) return null;
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(ratingEntity.getId());
        ratingDTO.setRating(ratingEntity.getRating());
        return ratingDTO;
    }

    public RatingEntity toEntity(RatingDTO ratingDTO) {
        if (ratingDTO == null) return null;
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setId(ratingEntity.getId());
        ratingEntity.setRating(ratingEntity.getRating());
        return ratingEntity;
    }
}
