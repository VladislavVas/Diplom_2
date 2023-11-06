import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LogInUserTest extends BaseTest {

    @Before
    public void setUp() {
        userDto = userData.getUserData();
        loginUserDto = mapper.toLoginUserDto(userDto);
    }

    @After
    public void clear() {
        userClient.deleteUser(loginUserDto);
    }

    @Test
    @DisplayName("Log in user with valid data.")
    @Description("Successful login of a user.")
    public void UserLoginSuccessfulTest() {
        userClient.createUser(userDto);
        var response = userClient.loginUser(loginUserDto);
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Log in user with incorrect data.")
    @Description("It is impossible to log in with an incorrect email and password")
    public void LoginUserWithIncorrectData() {
        var response = userClient.loginUser(loginUserDto);
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo(INCORRECT_FIELDS));
    }

    @Test
    @DisplayName("Log in user with incorrect email.")
    @Description("It is impossible to log in with an incorrect email")
    public void LoginUserWithIncorrectEmail() {
        loginUserDto.setEmail("incorrect_email@qwerty.com");
        var response = userClient.loginUser(loginUserDto);
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo(INCORRECT_FIELDS));
    }

    @Test
    @DisplayName("Log in user with invalid email.")
    @Description("The service should return error 401, not 5xx")
    public void LoginUserWithInvalidEmail() {
        loginUserDto.setEmail("invalid_email");
        var response = userClient.loginUser(loginUserDto);
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo(INCORRECT_FIELDS));
    }

    @Test
    @DisplayName("Log in user with incorrect email.")
    @Description("It is impossible to log in with an incorrect email")
    public void LoginUserWithIncorrectPassword() {
        loginUserDto.setPassword("incorrect_password");
        var response = userClient.loginUser(loginUserDto);
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo(INCORRECT_FIELDS));
    }
    
}
