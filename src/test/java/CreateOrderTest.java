import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.praktikum.model.order.IngredientListDto;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(JUnitParamsRunner.class)
public class CreateOrderTest extends BaseTest {


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
    @DisplayName("Creating an order for authorized users. Order without ingredients.")
    @Description("It is not possible to create a order without an ingredients")
    public void createOrderWithoutIngredientsTest() {
        ingredientListDto = new IngredientListDto(new String[]{});
        var response = orderClient.createOrder(ingredientListDto, token);
        response.assertThat()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo(NO_INGREDIENT));
    }

    @Test
    @DisplayName("Creating an order for unauthorized users.")
    @Description("Successful creation of a order with an ingredients by authorized user")
    public void createOrderWithoutAuthorizationTest() {
        ingredientListDto = new IngredientListDto(generateIngredients(1));
        var response = orderClient.createOrder(ingredientListDto);
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Creating an order for authorized users. Order with ingredients.")
    @Description("Successful creation of a order with an ingredients by authorized user")
    @Parameters({
            "1",
            "3",
            "6"})
    public void createOrderWithIngredientsTest(int n) {
        ingredientListDto = new IngredientListDto(generateIngredients(n));
        var response = orderClient.createOrder(ingredientListDto, token);
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }


}
