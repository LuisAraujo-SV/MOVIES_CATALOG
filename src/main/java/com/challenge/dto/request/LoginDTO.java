package com.challenge.dto.request;

import com.challenge.dto.AbstractUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginDTO extends AbstractUserDTO {
    private static final long serialVersionUID = 1L;

}
