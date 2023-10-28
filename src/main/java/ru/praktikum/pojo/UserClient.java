package ru.praktikum.pojo;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.model.CreateUserDto;
import ru.praktikum.model.LoginUserDto;

import static ru.praktikum.pojo.Constant.*;

public class UserClient extends BaseClient {

    @Step("POST request. Create user.")
    public ValidatableResponse createUser(CreateUserDto createUserDto) {
        return postRequest(BASE_URL + CREATE_USER, createUserDto);
    }

    @Step("POST request. Log in user")
    public ValidatableResponse loginUser(LoginUserDto loginUserDto) {
        return postRequest(BASE_URL + LOGIN_USER, loginUserDto);
    }

    @Step("Delete request. Delete user")
    public ValidatableResponse deleteUser(LoginUserDto loginUserDto) {
        return deleteRequest(BASE_URL + UPDATE_USER, getToken(loginUserDto));
    }

    @Step("Get authorization token")
    private String getToken(LoginUserDto loginUserDto) {
        return loginUser(loginUserDto)
                .extract()
                .body()
                .path("accessToken");
    }


}
