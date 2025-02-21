package com.challenge.service.impl;

import com.challenge.dto.request.LoginDTO;
import com.challenge.dto.request.RegisterDTO;
import com.challenge.dto.response.CustomResponseDTO;
import com.challenge.dto.response.UserErrorDTO;
import com.challenge.model.entities.UserEntity;
import com.challenge.model.repositories.UserRepository;
import com.challenge.service.AuthService;
import com.challenge.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public CustomResponseDTO register(RegisterDTO registerDTO) {
        CustomResponseDTO responseDTO = new CustomResponseDTO();
        responseDTO.setCode(CustomResponseDTO.ERROR_CODE);
        responseDTO.setMessage(CustomResponseDTO.ERROR_MESSAGE);
        try {
            UserEntity user = new UserEntity();
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setRoleEnum(registerDTO.getRole());
            userRepository.save(user);

            responseDTO.setCode(CustomResponseDTO.OK_CODE);
            responseDTO.setMessage(CustomResponseDTO.OK_MESSAGE);
            responseDTO.setResponse(user.getEmail());
        }
        catch (DataIntegrityViolationException e) {
            LOGGER.error(e.getMessage());
            responseDTO.setCode(UserErrorDTO.ERROR_REGISTER_CODE);
            responseDTO.setMessage(UserErrorDTO.ERROR_REGISTER_MESSAGE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return responseDTO;

    }

    private void authenticate(CustomResponseDTO responseDTO, String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            responseDTO.setCode(UserErrorDTO.ERROR_DISABLED_CODE);
            responseDTO.setMessage(UserErrorDTO.ERROR_DISABLED_MESSAGE);
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            responseDTO.setCode(UserErrorDTO.ERROR_INVALID_CODE);
            responseDTO.setMessage(UserErrorDTO.ERROR_INVALID_MESSAGE);
            throw new Exception("BAD_CREDENTIALS", e);
        }
    }
}
