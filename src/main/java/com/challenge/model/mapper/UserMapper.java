package com.challenge.model.mapper;

import com.challenge.dto.UserDTO;
import com.challenge.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRoleEnum(userEntity.getRoleEnum());
        userDTO.setRatings(new ArrayList<>());
        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRoleEnum(userDTO.getRoleEnum());
        userEntity.setRatings(new ArrayList<>());
        return userEntity;
    }
}
