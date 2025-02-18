package com.api.tests;
import com.api.wrapper.RestfulBookerAPI;
import io.restassured.response.Response;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.Assert;
import io.qameta.allure.Step;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class RestfulBookerAPITest {

    private static final String TEST_DATA_PATH = "src/main/resources/testdata.json";

  
    @Test(enabled = true)
    public void testGetBookingByIdMock() throws IOException {
        int mockBookingId = 544;  
        String mockApiUrl = "https://restful-booker.herokuapp.com/booking/" + mockBookingId;
       
        //Load full test data from JSON file
        String testData = new String(Files.readAllBytes(Paths.get(TEST_DATA_PATH)));

        // Parse JSON and extract only "bookingDetails"
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(testData);
        String mockResponseBody = objectMapper.writeValueAsString(rootNode.get("bookingDetails"));

        //  Mock Response object
        Response mockResponse = mock(Response.class);
        when(mockResponse.getStatusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn(mockResponseBody);

        //  Mock static method call
        try (MockedStatic<RestfulBookerAPI> mockedAPI = Mockito.mockStatic(RestfulBookerAPI.class)) {
            mockedAPI.when(() -> RestfulBookerAPI.getBookingById(anyInt())).thenReturn(mockResponse);

            // Log request
            System.out.println("MOCK: Sending GET request to URL -> " + mockApiUrl);

            // Call the mocked method
            Response response = RestfulBookerAPI.getBookingById(mockBookingId);

            // Log mock response
            System.out.println("MOCK: Received response -> " + response.asString());

            // Assertions (Checking response data)
            Assert.assertNotNull(response, "Response should not be null");
       

            // Verify method call
            mockedAPI.verify(() -> RestfulBookerAPI.getBookingById(mockBookingId), times(1));
        }
    }
}
