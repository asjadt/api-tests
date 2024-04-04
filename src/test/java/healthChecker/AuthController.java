package healthChecker;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AuthController {


    private static final String HEALTH_ENDPOINT = "/api/health";





    @Test

    public void checkHealth() {
        Map<String, String> clientSites = new HashMap<>();

        clientSites.put("@@fresh_hrm@@", "https://quickreview.app/hrm");
        clientSites.put("@@production_hrm@@", "https://prod.hrm-backend.interactivehr.co.uk");
        clientSites.put("@@demo_hrm@@", "https://hrm-backend.interactivehr.co.uk");
//        clientSites.put("@@demo_hrm@@", "https://invoicebackend.quickreview.app");


        // Perform health check for each client site
        for (Map.Entry<String, String> entry : clientSites.entrySet()) {
            System.out.println("helloooooooooooooooooo");
            String clientName = entry.getKey();
            String baseUrl = entry.getValue();
            testHealthCheckAPI(clientName, baseUrl);
        }


    }
    public static void testHealthCheckAPI(String clientName, String baseUrl) {
        performRequest(clientName, baseUrl, "GET");



        // Perform a POST request
        performRequest(clientName, baseUrl, "POST");



        // Perform a PUT request
        performRequest(clientName, baseUrl, "PUT");


        // Perform a DELETE request
        performRequest(clientName, baseUrl, "DELETE");


        // Perform a PATCH request
        performRequest(clientName, baseUrl, "PATCH");


    }

    private static void performRequest(String clientName, String baseUrl, String method) {
        final String validEmailResponse = given().contentType(ContentType.JSON)

                .when()
                .post(baseUrl + HEALTH_ENDPOINT)
                .then()
                .statusCode(200)
                .body("message", equalTo("please check email"))
                .extract()
                .response()
                .body()
                .asString();
        System.out.println("Valid email response: " + validEmailResponse);
        final String userNotFoundResponse = given().contentType(ContentType.JSON)
                .when()
                .post(baseUrl + HEALTH_ENDPOINT)
                .then()
                .statusCode(404)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println("User not found response: " + userNotFoundResponse);
    }




}
