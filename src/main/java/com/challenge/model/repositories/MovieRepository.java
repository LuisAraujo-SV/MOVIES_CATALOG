package com.challenge.model.repositories;

import com.challenge.model.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, PagingAndSortingRepository<MovieEntity, Long> {

    @Query("SELECT m FROM MovieEntity m " +
            "WHERE (:search IS NULL OR LOWER(m.name) LIKE CONCAT('%', LOWER(:search), '%') " +
            "OR LOWER(m.synopsis) LIKE CONCAT('%', LOWER(:search), '%')) " +
            "AND (:category IS NULL OR m.category = :category) " +
            "AND (:releaseYear IS NULL OR m.releaseYear = :releaseYear)")
    Page<MovieEntity> searchMovies(String search, String category, Integer releaseYear, Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE MovieEntity m WHERE m.id = :id")
    Integer deleteMovieById(Long id);

}
