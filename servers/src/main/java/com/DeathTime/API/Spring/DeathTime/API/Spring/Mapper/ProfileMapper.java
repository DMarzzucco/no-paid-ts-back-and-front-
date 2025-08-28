package com.DeathTime.API.Spring.DeathTime.API.Spring.Mapper;

import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.CreateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.UpdateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    UserModel createUseDTOModel(CreateUserDTO createUserDTO);

    void updateUserDTOModel(UpdateUserDTO updateUserDTO, @MappingTarget UserModel userModel);
}
