package com.challenge.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public abstract class AbstractUserDTO implements Serializable {

    @Email(message = "invalid email format")
    private String email;

    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
            message = "Minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character")
    private String password;
}
