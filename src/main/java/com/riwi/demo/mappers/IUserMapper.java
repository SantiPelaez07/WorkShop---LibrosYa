package com.riwi.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.riwi.demo.api.request.UserRequest;
import com.riwi.demo.api.response.UserResponse;
import com.riwi.demo.domain.entities.UserEntity;

/* Configuración inicial para el mapeo */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "loans", ignore = true)
    UserResponse userToDto(UserEntity user);

    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserEntity RequestToEntity(UserRequest user);

    @Mapping(target = "password", ignore = true)
    UserEntity toEntity(UserResponse userResponse);

    List<UserResponse> toUserResponseList(List<UserEntity> userList);

    List<UserEntity> toUserEntityList(List<UserResponse> userResponseList);
}
