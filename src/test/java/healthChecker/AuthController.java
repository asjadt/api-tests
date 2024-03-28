package healthChecker;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import static invoiceApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;


public class AuthController {

    public static final String LOGIN_ENDPOINT = "/api/v1.0/login";

    @Test // Add the Test annotation
    public void testLoginAPI() {
        // Define client site URLs along with their base URLs
        Map<String, String> clientSites = new HashMap<>();

        clientSites.put("@@fresh_hrm@@", "https://quickreview.app/hrm");
        clientSites.put("@@production_hrm@@", "https://prod.hrm-backend.interactivehr.co.uk");
        clientSites.put("@@demo_hrm@@", "https://hrm-backend.interactivehr.co.uk");

        clientSites.put("@@property_management_client@@", "https://invoicebackend.quickreview.app");

//        clientSites.put("Client2", "https://example.com");
        // Add more clients along with their base URLs here...

        // Perform health check for each client site
        for (Map.Entry<String, String> entry : clientSites.entrySet()) {
            String clientName = entry.getKey();
            String baseUrl = entry.getValue();
            testLoginAPI(clientName, baseUrl);
        }

    }

    public static void testLoginAPI(String clientName, String baseUrl) {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", "asjadtariq@gmail.com");
        requestBody.put("password", "12345678@We");

        // Make the API request
        String response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        // Print the response
        System.out.println("Health Check for " + clientName + " at " + baseUrl + ":\n" + response);
    }



}
