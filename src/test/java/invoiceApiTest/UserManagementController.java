package invoiceApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.URL;
import static invoiceApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;
public class UserManagementController {
    @Test
    public void testRegisterUserWithBusinessAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user", new HashMap<>() {{
            put("first_Name", "Rifat");
            put("last_Name", "Al-Ashwad");
            put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
            put("password", "12345678We");
            put("password_confirmation", "12345678We");
            put("phone", "01771034383");
            put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        }});

        requestBody.put("business", new HashMap<>() {{
            put("name", "ABCD business");
            put("about", "Best business in Dhaka");
            put("web_page", "https://www.facebook.com/");
            put("phone", "01771034383");
            put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
            put("phone", "01771034383");
            put("additional_information", "No Additional Information");
            put("address_line_1", "Dhaka");
            put("address_line_2", "Dinajpur");
            put("lat", "23.704263332849386");
            put("long", "90.44707059805279");
            put("country", "Bangladesh");
            put("city", "Dhaka");
            put("currency", "BDT");
            put("postcode", "Dinajpur");
            put("invoice_title", "invoice_title");
            put("footer_text", "footer_text");
            put("is_reference_manual", "1");
            put("receipt_footer", "t srt stgh st h");
            put("account_name", "thdht rth s");
            put("account_number", "fdghdgh");
            put("sort_code", "sort_coderthdrfth");
            put("pin", "1234");
//            put("type", "other");
            put("type", "property_dealer");
            put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        }});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/auth/register-with-business")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);

    }


    @Test
    public void testUpdateUserWithBusinessAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> userRequestBody = new HashMap<>();
        userRequestBody.put("id", 2);
        userRequestBody.put("first_Name", "Rifat");
        userRequestBody.put("last_Name", "Al-Ashwad");
        userRequestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        userRequestBody.put("password", "12345678");
        userRequestBody.put("password_confirmation", "12345678");
        userRequestBody.put("phone", "01771034383");
        userRequestBody.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");

        Map<String, Object> businessRequestBody = new HashMap<>();
        businessRequestBody.put("id", 1);
        businessRequestBody.put("name", "ABCD business");
        businessRequestBody.put("about", "Best business in Dhaka");
        businessRequestBody.put("web_page", "https://www.facebook.com/");
        businessRequestBody.put("phone", "01771034383");
        businessRequestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        businessRequestBody.put("additional_information", "No Additional Information");
        businessRequestBody.put("address_line_1", "Dhaka");
        businessRequestBody.put("address_line_2", "Dinajpur");
        businessRequestBody.put("lat", "23.704263332849386");
        businessRequestBody.put("long", "90.44707059805279");
        businessRequestBody.put("country", "Bangladesh");
        businessRequestBody.put("city", "Dhaka");
        businessRequestBody.put("postcode", "Dinajpur");
        businessRequestBody.put("invoice_title", "invoice_title");
        businessRequestBody.put("footer_text", "footer_text");
        businessRequestBody.put("is_reference_manual", "1");
        businessRequestBody.put("receipt_footer", "receipt_footer");
        businessRequestBody.put("account_name", "thdht rth s");
        businessRequestBody.put("account_number", "fdghdgh");
        businessRequestBody.put("sort_code", "sort_coderthdrfth");
        businessRequestBody.put("pin", "1234");
        businessRequestBody.put("type", "other");
        businessRequestBody.put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        businessRequestBody.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user", userRequestBody);
        requestBody.put("business", businessRequestBody);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/auth/update-user-with-business") // Assuming you have the correct URL
                .then()
                .statusCode(201) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUserToggleActiveAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 2);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/users/toggle-active")
                .then()
                .statusCode(200) // Ensure a successful response code
                .extract()
                .response().asString();



        System.out.println(response);
    }

    @Test
    public void testDeleteUserByIdAPI() throws JsonProcessingException {
        // Prepare the request
        String id = "2";

        // Make the request
        String  response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .header("password", "12345678We")
                .when()
                .delete(URL + "/api/v1.0/users/" + id)
                .then()
                .statusCode(200) // Ensure a successful response code
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }




}
