package com.api.test.technical.challenge;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import payload.Auth;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import routes.Endpoints;

public class ExceptionScenarios {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec400;
    public static ResponseSpecification resSpec401;
    public static ResponseSpecification resSpec403;
    public static ResponseSpecification resSpec404;

    @BeforeClass
    public static void setup() {
        baseURI = Endpoints.HomeEndpoint.baseUri;

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqSpec = reqBuilder.build();

        ResponseSpecBuilder resError400Builder = new ResponseSpecBuilder();
        resError400Builder.expectStatusCode(400);
        resSpec400 = resError400Builder.build();

        ResponseSpecBuilder resError401Builder = new ResponseSpecBuilder();
        resError401Builder.expectStatusCode(401);
        resSpec401 = resError401Builder.build();

        ResponseSpecBuilder resError403Builder = new ResponseSpecBuilder();
        resError403Builder.expectStatusCode(403);
        resSpec403 = resError403Builder.build();

        ResponseSpecBuilder resError404Builder = new ResponseSpecBuilder();
        resError403Builder.expectStatusCode(404);
        resSpec404 = resError404Builder.build();
    }

    @Test
    public void nonExistentUser() {
        given().spec(reqSpec).when().get("/users/999").then().spec(resSpec404).body("message", is("User with id '999' not found"));
    }

    @Test
    public void nonExistentProduct() {

        given().spec(reqSpec).when().get("/product/10").then().spec(resSpec404) // 400 - Product with id '1000' not found
                .body("products", not(empty())).body("id", equalTo(10));
    }

    @Test
    public void unsuccessfulAuthenticationWhenUserOrPasswordAreIncorrect() {
        Auth login = new Auth("kminchelle1", "123");

        given().spec(reqSpec).contentType(ContentType.JSON).body(login).when().post("/auth/login").then().spec(resSpec400).extract().response().then().body(containsString("Invalid credentials"));
    }

    @Test
    public void unsuccessfulSeeProductsWhenTokenIsNull() {

        given().spec(reqSpec).contentType(ContentType.JSON).header("Authorization", " ").when().get("/auth/products").then().spec(resSpec401).body("message", equalTo("Access Token is required"));
    }

    @Test
    public void unsuccessfulSeeProductsWhenTokenIsExpired() {

        given().spec(reqSpec).contentType(ContentType.JSON).header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6").when().get("/auth/products").then().spec(resSpec401).body("message", equalTo("Invalid/Expired Token!"));
    }
}
