package com.challenge.model.repositories;

import com.challenge.dto.response.UserMovieRatingDTO;
import com.challenge.model.entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    @Query("SELECT r FROM RatingEntity r WHERE r.user.id = :userId AND r.movie.id = :movieId")
    Optional<RatingEntity> findByUserAndMovie(@Param("userId") Long userId,
                                             @Param("movieId") Long movieId);


    @Query("SELECT new com.challenge.dto.response.UserMovieRatingDTO(m.id, m.name, m.releaseYear, m.synopsis, m.category, m.imageUrl, m.createdDate, m.averageRating, r.rating) " +
            "FROM RatingEntity r " +
            "RIGHT JOIN MovieEntity m ON m.id = r.movie.id " +
            "WHERE r.user.id = :userId")
    List<UserMovieRatingDTO> findAllRatings(@Param("userId") Long userId);
}
