package com.challenge.controller;

import com.challenge.dto.request.LoginDTO;
import com.challenge.dto.request.RegisterDTO;
import com.challenge.dto.response.AbstractResponseDTO;
import com.challenge.dto.response.CustomResponseDTO;
import com.challenge.dto.response.UserErrorDTO;
import com.challenge.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<? extends AbstractResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

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
