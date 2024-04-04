package healthChecker;

import java.util.HashMap;
import java.util.Map;
import data.reqres.PostData;
import in.reqres.TestPostRequests;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class HealthCheckerController {

    private static final String HEALTH_ENDPOINT = "api/health";

    @Test // Add the Test annotation
    public void checkHealth() {
        // Define client site URL
        // s along with their base URLs

        System.out.println("helloooooooooooooooooo");
        Map<String, String> clientSites = new HashMap<>();

        clientSites.put("@@fresh_hrm@@", "https://quickreview.app/hrm");
        clientSites.put("@@production_hrm@@", "https://prod.hrm-backend.interactivehr.co.uk");
        clientSites.put("@@demo_hrm@@", "https://hrm-backend.interactivehr.co.uk");
        clientSites.put("@@demo_hrm@@", "https://invoicebackend.quickreview.app");
        
        



        // Perform health check for each client site
        for (Map.Entry<String, String> entry : clientSites.entrySet()) {
            System.out.println("helloooooooooooooooooo");
            String clientName = entry.getKey();
            String baseUrl = entry.getValue();
            testHealthCheckAPI(clientName, baseUrl);
        }

    }

    public static void testHealthCheckAPI(String clientName, String baseUrl) {
        System.out.println("dddddddddd");
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        

        performRequest(clientName, baseUrl, "GET", requestBody);
        


        // Perform a POST request
        performRequest(clientName, baseUrl, "POST", requestBody);



        // Perform a PUT request
        performRequest(clientName, baseUrl, "PUT", requestBody);


        // Perform a DELETE request
        performRequest(clientName, baseUrl, "DELETE", requestBody);


        // Perform a PATCH request
        performRequest(clientName, baseUrl, "PATCH", requestBody);

    }

    private static void performRequest(String clientName, String baseUrl, String method, Map<String, Object> requestBody) {
        System.out.println("aaaaaaaaaa");

        String response2 =  given().contentType(ContentType.JSON)
                .body("{\"email\": \"rifat"  + "@gmail.com\", \"password\": \"yourPassword\"}")
                .log().headers() // log only the headers
                .log().body() // log only the request body
                .when()
                .post(baseUrl + "/api/auth/register")
                .then()
                .statusCode(201)
                .body("message", equalTo("You have successfully registered"))

                .extract()
                .response()
                .body()
                .asString();

        // Print the response
        System.out.println("Health Check for " + clientName + " at " + baseUrl + " using " + method + ":\n" + response2);
        // Make the API request
        String response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .request(method, HEALTH_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();




        // Print the response
        System.out.println("Health Check for " + clientName + " at " + baseUrl + " using " + method + ":\n" + response);
    }
    
    
    private static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Your other methods go here

}
