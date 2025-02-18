package com.api.tests;

import com.api.wrapper.RestfulBookerAPI;
import com.api.config.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import io.qameta.allure.Step;

import org.testng.annotations.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BookingTest {
    private static String token;
    private static int bookingId;
    private static Map<String, Object> bookingDetails;
    private static Map<String, Object> updatedBookingDetails;
    private static Map<String, Object> partialUpdateDetails;

    @BeforeClass
    public void setUp() throws Exception {
        // Load test data from JSON
        String jsonData = new String(Files.readAllBytes(Paths.get("src/main/resources/testdata.json")));
        Map<String, Object> testData = new com.fasterxml.jackson.databind.ObjectMapper().readValue(jsonData, Map.class);
        
        bookingDetails = (Map<String, Object>) testData.get("bookingDetails");
        updatedBookingDetails = (Map<String, Object>) testData.get("updatedBookingDetails");
        partialUpdateDetails = (Map<String, Object>) testData.get("partialUpdateDetails");
    }



    @Test(priority = 1, enabled = true)
    public void testHealthCheckPing() {
        Response response = RestfulBookerAPI.healthCheck();
        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code is 201");
        
    }

    
    @Test(priority = 2, enabled = true)
    public void testGenerateAuthToken() {
         
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", ConfigManager.getProperty("username"));
        credentials.put("password", ConfigManager.getProperty("password"));

        Response response = RestfulBookerAPI.createToken(credentials);

        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token should not be null");
    
    }

    @Test(priority = 3, enabled = true)
    public void testCreateBooking() {
        Response response = RestfulBookerAPI.createBooking(bookingDetails);
        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, "Booking ID should be greater than 0");
    }

    @Test(priority = 4, enabled = true)
    public void testGetBookingById() {
        Response response = RestfulBookerAPI.getBookingById(bookingId);
        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
        Assert.assertNotNull(response.getBody().asString(), "Response body should not be null");
    }

    @Test(priority = 5, enabled = true)
    public void testUpdateBooking() {
        Response response = RestfulBookerAPI.updateBooking(bookingId, token, updatedBookingDetails);
        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
        
        Assert.assertEquals(response.jsonPath().getString("firstname"), updatedBookingDetails.get("firstname"), "First name should be updated");
    }

    @Test(priority = 6, enabled = true)
    public void testPartialUpdateBooking() {
        Response response = RestfulBookerAPI.partialUpdateBooking(bookingId, token, partialUpdateDetails);
        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
        
        Assert.assertEquals(response.jsonPath().getString("firstname"), partialUpdateDetails.get("firstname"), "First name should be updated");
    }

    @Test(priority = 7, enabled = true)
    public void testDeleteBooking() {
        Response response = RestfulBookerAPI.deleteBooking(bookingId, token);
        System.out.println("\n===== RESPONSE RECEIVED =====\n"  + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code is 201");
    }
}
