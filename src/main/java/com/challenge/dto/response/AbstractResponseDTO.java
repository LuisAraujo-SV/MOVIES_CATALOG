package com.challenge.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractResponseDTO implements Serializable {
    public static final String OK_CODE = "00";
    public static final String OK_MESSAGE = "Operation Success!";

    public static final String ERROR_CODE = "99";
    public static final String ERROR_MESSAGE = "Operation Failed!";

    private String code;
    private String message;
    private Object response;
}
