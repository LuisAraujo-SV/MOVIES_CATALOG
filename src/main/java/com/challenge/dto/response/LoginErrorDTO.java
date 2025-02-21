package com.challenge.dto.response;

public class LoginErrorDTO extends CustomResponseDTO {
    public static final String ERROR_INVALID_CODE = "01";
    public static final String ERROR_INVALID_MESSAGE = "invalid username or password";
    public static final String ERROR_DISABLED_CODE = "02";
    public static final String ERROR_DISABLED_MESSAGE = "user disabled";
}
