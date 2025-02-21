package com.challenge.dto.request;

import com.challenge.dto.AbstractUserDTO;
import com.challenge.model.entities.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegisterDTO extends AbstractUserDTO {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "role must be assigned")
    private RoleEnum role;
}
