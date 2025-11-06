package com.mohamedFarag.store.mappers;

import com.mohamedFarag.store.dtos.RegisterUserRequest;
import com.mohamedFarag.store.dtos.UpdateUserRequest;
import com.mohamedFarag.store.dtos.UserDto;
import com.mohamedFarag.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user); // maps a user to userDto
    User registerUserToEntity(RegisterUserRequest request); //maps registerDto to a user entity
    void updateUser(UpdateUserRequest request, @MappingTarget User user); // directly update a user

}
