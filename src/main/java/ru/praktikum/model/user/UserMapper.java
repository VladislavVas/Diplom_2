package ru.praktikum.model.user;

import ru.praktikum.model.user.CreateUserDto;
import ru.praktikum.model.user.LoginUserDto;

public class UserMapper {

    public LoginUserDto toLoginUserDto(CreateUserDto createUserDto) {
        return new LoginUserDto(createUserDto.getPassword(), createUserDto.getEmail());
    }

}
