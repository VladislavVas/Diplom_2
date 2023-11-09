package ru.praktikum.pojo;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    @Description("Method overload. There is no token in the signature.")
    public ValidatableResponse postRequest(String uri, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.post(uri).then();
    }

    @Description("Method overload. The signature uses a token.")
    public ValidatableResponse postRequest(String uri, Object body, String token) {
        RequestSpecification request = given(baseRequest());
        request.header("Authorization", token)
                .body(body);
        return request.post(uri).then();
    }


    public ValidatableResponse putRequest(String uri, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.put(uri).then();
    }

    @Description("Method overload. The signature uses a token.")
    public ValidatableResponse patchRequest(String uri, String token, Object body) {
        RequestSpecification request = given(baseRequest());
        if (token != null) {
            return request.header("Authorization", token)
                    .body(body)
                    .patch(uri).then();
        } else {
            return null;
        }
    }

    @Description("Method overload. There is no token in the signature.")
    public ValidatableResponse patchRequest(String uri, Object body) {
        RequestSpecification request = given(baseRequest());
        request
                .body(body);
        return request.patch(uri).then();
    }

    public ValidatableResponse getRequest(String uri, String token) {
        RequestSpecification request = given(baseRequest());
        return request
                .header("Authorization", token)
                .get(uri).then();
    }

    public ValidatableResponse getRequest(String uri) {
        RequestSpecification request = given(baseRequest());
        return request.get(uri).then();
    }

    public ValidatableResponse deleteRequest(String uri, String token) {
        RequestSpecification request = given(baseRequest());
        if (token != null) {
            return request.header("Authorization", token).
                    delete(uri).then();
        } else {
            return null;
        }
    }


    private RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

}
