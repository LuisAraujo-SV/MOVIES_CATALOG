package com.challenge.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class RatingDTO implements Serializable {

    private Long id;
    private UserDTO user;
    private MovieDTO movie;
    private int rating;

}
