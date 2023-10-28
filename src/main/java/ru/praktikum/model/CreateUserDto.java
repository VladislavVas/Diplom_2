package ru.praktikum.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserDto {

    private String password;
    private String name;
    private String email;

}
