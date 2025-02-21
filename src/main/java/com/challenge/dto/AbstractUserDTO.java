package com.challenge.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
public abstract class AbstractUserDTO implements Serializable {

    @Email(message = "invalid email format")
    private String email;

    private String password;
}
