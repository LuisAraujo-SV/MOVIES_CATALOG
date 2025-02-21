package com.challenge.controller;

import com.challenge.dto.request.LoginDTO;
import com.challenge.dto.request.RegisterDTO;
import com.challenge.dto.response.AbstractResponseDTO;
import com.challenge.dto.response.CustomResponseDTO;
import com.challenge.dto.response.UserErrorDTO;
import com.challenge.service.AuthService;
import com.challenge.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "log in")
    @PostMapping("/login")
    public ResponseEntity<? extends AbstractResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @ApiOperation(
            value = "register a new user",
            authorizations = { @Authorization(value= JwtTokenUtil.AUTHORIZATION_HEADER) })
    @PostMapping("/register")
    public ResponseEntity<? extends AbstractResponseDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        CustomResponseDTO responseDTO = authService.register(registerDTO);

        if (responseDTO.getCode().equals(UserErrorDTO.ERROR_REGISTER_CODE)) {
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }

        if (responseDTO.getCode().equals(UserErrorDTO.ERROR_CODE)) {
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(responseDTO);
    }
}
