package com.challenge.service;

import com.challenge.dto.request.LoginDTO;
import com.challenge.dto.request.RegisterDTO;
import com.challenge.dto.response.CustomResponseDTO;

public interface AuthService {

    CustomResponseDTO login(LoginDTO loginDTO);

    CustomResponseDTO register(RegisterDTO registerDTO);
}
