package ru.praktikum.pojo;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.model.order.IngredientListDto;

import static ru.praktikum.pojo.Constant.*;

public class OrderClient extends BaseClient {

    @Step("POST request. Create order with token.")
    public ValidatableResponse createOrder(IngredientListDto ingredientListDto, String token) {
        return postRequest(BASE_URL + ORDERS, ingredientListDto, token);
    }

    @Step("POST request. Create order without token.")
    public ValidatableResponse createOrder(IngredientListDto ingredientListDto) {
        return postRequest(BASE_URL + ORDERS, ingredientListDto);
    }

    @Step("GET request. Get order.")
    public ValidatableResponse getOrder(String token) {
        return getRequest(BASE_URL + ORDERS, token);
    }

//    @Step("GET request. Get ingredients info.")
//    public ValidatableResponse getIngredient() {
//        return getRequest(BASE_URL + INGREDIENTS);
//    }

    @Step
    public String getIngredientId(int i) {
        var response = getRequest(BASE_URL + INGREDIENTS);
        return response.extract().path("data[%s]._id", String.valueOf(i));
    }
}
