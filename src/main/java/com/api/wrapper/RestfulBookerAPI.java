package com.api.wrapper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import com.api.config.ConfigManager;
import java.util.Map;
import io.restassured.http.ContentType;

public class RestfulBookerAPI {
    private static final String BASE_URL = ConfigManager.getBaseUrl();

    // Define a reusable RequestSpecification
    private static final RequestSpecification REQUEST_SPEC = given()
   
            .baseUri(BASE_URL)
            .contentType(JSON)
            .log().all();


        public static Response healthCheck() {
                return given()
                    .spec(REQUEST_SPEC)
                .when()
                    .get("/ping/")
                .then()
                  
                    .statusCode(201)  
                    .extract().response();
            }      

    public static Response createToken(Map<String, String> credentials) {
        return given()
            .spec(REQUEST_SPEC)
            .body(credentials)
        .when()
            .post("/auth")
        .then()
          
            .statusCode(200) 
            .extract().response();
    }

    public static Response createBooking(Object bookingDetails) {
        return given()
            .spec(REQUEST_SPEC)
            .body(bookingDetails)
        .when()
            .post("/booking")
        .then()
            
            .statusCode(200)  
            .extract().response();
    }

    public static Response getBookingById(int bookingId) {
        return given()
            .spec(REQUEST_SPEC)
        .when()
            .get("/booking/" + bookingId)
        .then()
          
            .statusCode(200)  
            .extract().response();
    }

    public static Response updateBooking(int bookingId, String token, Object updatedDetails) {
        return given()
            .spec(REQUEST_SPEC)
            .header("Cookie", "token=" + token)
            .body(updatedDetails)
        .when()
            .put("/booking/" + bookingId)
        .then()
           
            .statusCode(200)
            .extract().response();
    }

    public static Response partialUpdateBooking(int bookingId, String token, Object partialUpdate) {
        return given()
            .spec(REQUEST_SPEC)
            .header("Cookie", "token=" + token)
            .body(partialUpdate)
        .when()
            .patch("/booking/" + bookingId)
        .then()
            
            .statusCode(200)
            .extract().response();
    }

    public static Response deleteBooking(int bookingId, String token) {
        return given()
            .spec(REQUEST_SPEC)
            .header("Cookie", "token=" + token)
        .when()
            .delete("/booking/" + bookingId)
        .then()
            
            .statusCode(201) 
            .extract().response();
    }
}
