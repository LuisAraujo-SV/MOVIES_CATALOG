package com.challenge.dto;


import com.challenge.model.entities.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO extends AbstractUserDTO {

    private Long id;
    private RoleEnum roleEnum;
    private List<RatingDTO> ratings;

}
