import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UpdateUserTest extends BaseTest {


    @Before
    public void setUp() {
        userDto = userData.getUserData();
        loginUserDto = mapper.toLoginUserDto(userDto);
        userClient.createUser(userDto);
    }

    @After
    public void clear() {
        userClient.deleteUser(mapper.toLoginUserDto(userDto));
    }

    @Test
    @DisplayName("Update the name of an authorized user.")
    @Description("The name of the authorized user must be successfully changed.")
    public void updateUserNameWithAuth() {
        String newName = userData.getNewUserName();
        userDto.setName(newName);
        var response = userClient.updateUser(loginUserDto, userDto);
        response.assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("user", notNullValue())
                .body("user.name", equalTo(newName));
    }

    @Test
    @DisplayName("Update the name of an unauthorized user.")
    @Description("It is not possible to update the name of an unauthorized user.")
    public void updateUserNameWithoutAuth() {
        String newName = userData.getNewUserName();
        userDto.setName(newName);
        var response = userClient.updateUser(userDto);
        response.assertThat().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Update the email of an unauthorized user.")
    @Description("It is not possible to update the email of an unauthorized user.")
    public void updateUserMailWithoutAuth() {
        String newMail = userData.getNewUserMail();
        userDto.setName(newMail);
        var response = userClient.updateUser(userDto);
        response.assertThat().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }


    @Test
    @DisplayName("Update the email of an authorized user.")
    @Description("The email of the authorized user must be successfully changed.")
    public void updateUserMailWithAuth() {
        String newMail = userData.getNewUserMail();
        userDto.setEmail(newMail);
        var response = userClient.updateUser(loginUserDto, userDto);
        response.assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("user", notNullValue())
                .body("user.email", equalTo(newMail));
    }

}
