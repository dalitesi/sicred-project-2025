package com.api.test.technical.challenge;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import routes.Endpoints.HomeEndpoint;

import payload.Product;
import payload.Auth;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

public class SicrediTest {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;
    public static ResponseSpecification resSpec201;

    @BeforeClass
    public static void setup() {
        baseURI = HomeEndpoint.baseUri;

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqSpec = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        resSpec = resBuilder.build();

        ResponseSpecBuilder resBuilder201 = new ResponseSpecBuilder();
        resBuilder201.expectStatusCode(201);
        resSpec201 = resBuilder201.build();

        requestSpecification = reqSpec;

    }

    @Test
    public void firstTest() {
        given()
                .spec(reqSpec)
                .when()
                .get("/test")
                .then()
                .spec(resSpec)
                .body("status", equalTo("ok"))
                .body("method", equalTo("GET"));
    }

    @Test
    public void getUsers() {

        given()
                .spec(reqSpec)
                .when()
                .get("/users")
                .then()
                .spec(resSpec)
                .body("users", not(empty()))
                .body("users[0]", hasKey("id"));
    }

    @Test
    public void findASpecificUser() {
        given()
                .spec(reqSpec)
                .when()
                .get("/users/20")
                .then()
                .spec(resSpec)
                .body("id", is(20))
                .body("firstName", is("Jackson"));
    }

    @Test
    public void successfullyAuthenticated() {
        Auth login = new Auth("emilys", "emilyspass");

        String token =
                given()
                        .spec(reqSpec)
                        .contentType(ContentType.JSON)
                        .body(login)
                        .when()
                        .post("/auth/login")
                        .then()
                        .spec(resSpec)
                        .extract()
                        .path("accessToken");
    }

    @Test
    public void seeAllProductsWhenIsAuthenticated() {

        Auth login = new Auth("emilys", "emilyspass");

        String token =
                given()
                        .spec(reqSpec)
                        .contentType(ContentType.JSON)
                        .body(login)
                        .when()
                        .post("/auth/login")
                        .then()
                        .spec(resSpec)
                        .extract()
                        .path("accessToken");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("/auth/products")
                .then()
                .statusCode(200)
                .body("products", not(empty()))
                .body("products[0]", hasKey("id"));
    }

    @Test
    public void seeAllProductsWhenIsNotAuthenticated() {

        given()
                .spec(reqSpec)
                .when()
                .get("/product")
                .then()
                .spec(resSpec)
                .body("products", not(empty()))
                .body("products[0]", hasKey("id"));
    }

    @Test
    public void findASpecificProduct() {

        given()
                .spec(reqSpec)
                .when()
                .get("/product/10")
                .then()
                .spec(resSpec)
                .body("products", not(empty()))
                .body("id", equalTo(10));
    }

    @Test
    public void addProduct() {
        Product product = Product.createRandomProduct();

        given()
                .spec(reqSpec)
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("products/add")
                .then()
                .spec(resSpec201)
                .body("id", equalTo(195))
                .body("title", equalTo(product.getTitle()));
    }


}
