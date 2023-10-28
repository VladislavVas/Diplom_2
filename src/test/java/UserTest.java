import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserTest extends BaseTest {


    @Before
    public void setUp() {
        userDto = data.getUserData();
    }

    @After
    public void clear() {
        userClient.deleteUser(mapper.toLoginUserDto(userDto));
    }

    @Test
    @DisplayName("Create a new user")
    @Description("Successful creation of a new user.")
    public void createUserTest() {
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create a duplicate user")
    @Description("It is not possible to create duplicate user.")
    public void createDuplicateUserTest() {
        userClient.createUser(userDto);
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(ALREADY_EXISTS));
    }

    @Test
    @DisplayName("Creating a user without email.")
    @Description("It is not possible to create a user without an email")
    public void createUserWithEmptyEmailTest() {
        userDto.setEmail(null);
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }

    @Test
    @DisplayName("Creating a user with blanc email.")
    @Description("It is not possible to create a user if email is blanc")
    public void createUserWithBlancEmailTest() {
        userDto.setEmail(" ");
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }

    @Test
    @DisplayName("Creating a user with invalid email.")
    @Description("It is not possible to create a user if email is invalid")
    public void createUserWithInvalidEmailTest() {
        userDto.setEmail("invalid_email");
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403) //Wow, 500!
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }


    @Test
    @DisplayName("Creating a user without name.")
    @Description("It is not possible to create a user without a name")
    public void createUserWithEmptyNameTest() {
        userDto.setName(null);
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }

    @Test
    @DisplayName("Creating a user with blanc name.")
    @Description("It is not possible to create a user with blanc name")
    public void createUserWithBlancNameTest() {
        userDto.setName(" ");
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }

    @Test
    @DisplayName("Creating a user with blanc password.")
    @Description("It is not possible to create a user with blanc password")
    public void createUserWithBlancPasswordTest() {
        userDto.setPassword(" ");
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }

    @Test
    @DisplayName("Creating a user without password.")
    @Description("It is not possible to create a user without password")
    public void createUserWithoutPasswordTest() {
        userDto.setPassword(null);
        var response = userClient.createUser(userDto);
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo(REQUIRED_FIELDS));
    }

}
