package com.challenge.service.impl;

import com.challenge.dto.request.LoginDTO;
import com.challenge.dto.response.CustomResponseDTO;
import com.challenge.dto.response.LoginErrorDTO;
import com.challenge.service.AuthService;
import com.challenge.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public CustomResponseDTO login(LoginDTO loginDTO) {
        CustomResponseDTO responseDTO = new CustomResponseDTO();
        responseDTO.setMessage(CustomResponseDTO.ERROR_CODE);
        responseDTO.setMessage(CustomResponseDTO.ERROR_MESSAGE);

        if (StringUtils.isEmpty(loginDTO.getEmail()) || StringUtils.isEmpty(loginDTO.getPassword())) {
            return responseDTO;
        }

        try {
            authenticate(responseDTO, loginDTO.getEmail(), loginDTO.getPassword());
        } catch (Exception e) {
            LOGGER.debug(loginDTO.getEmail());
            LOGGER.error(e.getMessage());
            return responseDTO;
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginDTO.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        responseDTO.setCode(CustomResponseDTO.OK_CODE);
        responseDTO.setMessage(CustomResponseDTO.OK_MESSAGE);
        responseDTO.setResponse(token);
        return responseDTO;
    }

    private void authenticate(CustomResponseDTO responseDTO, String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            responseDTO.setCode(LoginErrorDTO.ERROR_DISABLED_CODE);
            responseDTO.setMessage(LoginErrorDTO.ERROR_DISABLED_MESSAGE);
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            responseDTO.setCode(LoginErrorDTO.ERROR_INVALID_CODE);
            responseDTO.setMessage(LoginErrorDTO.ERROR_INVALID_MESSAGE);
            throw new Exception("BAD_CREDENTIALS", e);
        }
    }
}
