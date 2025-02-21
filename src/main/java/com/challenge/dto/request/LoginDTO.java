package com.challenge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private String password;
}
