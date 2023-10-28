package ru.praktikum.model;

public class UserMapper {

    public LoginUserDto toLoginUserDto(CreateUserDto createUserDto) {
        return new LoginUserDto(createUserDto.getPassword(), createUserDto.getEmail());
    }

}
