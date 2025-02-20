package com.challenge.dto;


import com.challenge.model.entities.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private Long id;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private RoleEnum roleEnum;
    private List<RatingDTO> ratings;

}
