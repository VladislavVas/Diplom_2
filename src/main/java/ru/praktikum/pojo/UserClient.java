package ru.praktikum.pojo;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.model.user.CreateUserDto;
import ru.praktikum.model.user.LoginUserDto;

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

    @Step("DELETE request. Delete user")
    public ValidatableResponse deleteUser(LoginUserDto loginUserDto) {
        return deleteRequest(BASE_URL + UPDATE_USER, getToken(loginUserDto));
    }

    @Step("PATCH request. Update user data with token.")
    public ValidatableResponse updateUser(LoginUserDto loginUserDto, CreateUserDto updateData) {
        return patchRequest(BASE_URL + UPDATE_USER, getToken(loginUserDto), updateData);
    }

    @Step("PATCH request. Update user data without token.")
    public ValidatableResponse updateUser(CreateUserDto userDto) {
        return patchRequest(BASE_URL + UPDATE_USER, userDto);
    }


    @Step("Get authorization token")
    public String getToken(LoginUserDto loginUserDto) {
        return loginUser(loginUserDto)
                .extract()
                .body()
                .path("accessToken");
    }


}
