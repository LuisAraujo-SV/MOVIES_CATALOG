package com.challenge.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "movie", schema = "movies_schema")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "synopsis", length = 1000)
    private String synopsis;

    @Column(name = "category")
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @Column(name = "average_rating")
    private Double averageRating;


}
