package ru.praktikum.pojo;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    public ValidatableResponse postRequest(String uri, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.post(uri).then();
    }

    public ValidatableResponse putRequest(String uri, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.put(uri).then();
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
