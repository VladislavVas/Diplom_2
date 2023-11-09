import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.model.order.IngredientListDto;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.emptyArray;

public class GetOrderTest extends BaseTest {

    @Before
    public void setUp() {
        userDto = userData.getUserData();
        loginUserDto = mapper.toLoginUserDto(userDto);
        userClient.createUser(userDto);
        token = userClient.getToken(loginUserDto);
    }

    @After
    public void clear() {
        userClient.deleteUser(mapper.toLoginUserDto(userDto));
    }

    @Test
    @DisplayName("Getting a list of orders by an unauthorized user.")
    @Description("An unauthorized user cannot get a list of orders.")
    public void getOrderListForUnauthorizedUser() {
        var response = orderClient.getOrder("");
        response.assertThat().statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo(UNAUTHORIZED));
    }

    @Test
    @DisplayName("Getting a list of orders by an authorized user.")
    @Description("The authorized user successfully receives the list of orders.")
    public void getOrderListForAuthorizedUser() {
        ingredientListDto = new IngredientListDto(generateIngredients(1));
        orderClient.createOrder(ingredientListDto, token);
        var response = orderClient.getOrder(token);
        response.assertThat().statusCode(200)
                .body("success", equalTo(true))
                .body("orders", not(emptyArray()));
    }

}
