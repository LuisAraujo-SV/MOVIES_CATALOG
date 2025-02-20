package com.challenge.model.repositories;

import com.challenge.model.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, PagingAndSortingRepository<MovieEntity, Long> {


}
