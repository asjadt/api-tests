package invoiceApiTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static garageApiTest.Util.URL;
import static garageApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;


public class AuthController {
    @Test
    public void testLoginAPI() {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", "admin@gmail.com");
        requestBody.put("password", "12345678");

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .body(requestBody)
                .when()
                .post(URL +"/api/v1.0/login")
                .then()
                .statusCode(200)
             .extract()
             .response()
             .asString();

        System.out.println(response);
    }


    @Test
    public void testRegisterAPI() {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("first_Name", "Rifat");
        requestBody.put("last_Name", "Al");
        requestBody.put("email", ("rifat" + Math.random()  +  "@g.c" ));
        requestBody.put("password", "12345678");
        requestBody.put("password_confirmation", "12345678");
        requestBody.put("phone", "01771034383");
        requestBody.put("address_line_1", "dhaka");
        requestBody.put("address_line_2", "dinajpur");
        requestBody.put("country", "bangladesh");
        requestBody.put("city", "dhaka");
        requestBody.put("postcode", "1207");

       String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/register")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);

    }


    @Test
    public void testRegisterUserWithGarageClientAPI() {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();

        // User details
        Map<String, Object> user = new HashMap<>();
        user.put("first_Name", "Rifat");
        user.put("last_Name", "Al-Ashwad");
        user.put("email", ("rifatalashwad" + Math.random() + "@gmail.com"));
        user.put("password", "12345678");
        user.put("password_confirmation", "12345678");
        user.put("phone", "01771034383");
        user.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        user.put("address_line_1", "Dhaka");
        user.put("address_line_2", "Dinajpur");
        user.put("country", "Bangladesh");
        user.put("city", "Dhaka");
        user.put("postcode", "Dinajpur");



        // Garage details
        Map<String, Object> garage = new HashMap<>();
        garage.put("name", "ABCD Garage");
        garage.put("about", "Best Garage in Dhaka");
        garage.put("web_page", "https://www.facebook.com/");
        garage.put("phone", "01771034383");
        garage.put("email", ("rifatalashwad" + Math.random() + "@gmail.com"));
        garage.put("additional_information", "No Additional Information");
        garage.put("address_line_1", "Dhaka");
        garage.put("address_line_2", "Dinajpur");
        garage.put("lat", "23.704263332849386");
        garage.put("long", "90.44707059805279");
        garage.put("country", "Bangladesh");
        garage.put("city", "Dhaka");
        garage.put("currency", "BDT");
        garage.put("postcode", "Dinajpur");
        garage.put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        garage.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        garage.put("images", Arrays.asList("/a", "/b", "/c"));
        garage.put("is_mobile_garage", true);
        garage.put("wifi_available", true);
        garage.put("labour_rate", 500);

        requestBody.put("user", user);
        requestBody.put("garage", garage);
        requestBody.put("service", Arrays.asList(
                Map.of(
                        "automobile_category_id", 1,
                        "services", Arrays.asList(
                                Map.of(
                                        "id", 1,
                                        "checked", true,
                                        "sub_services", Arrays.asList(
                                                Map.of("id", 1, "checked", true),
                                                Map.of("id", 2, "checked", false)
                                        )
                                )
                        ),
                        "automobile_makes", Arrays.asList(
                                Map.of(
                                        "id", 1,
                                        "checked", true,
                                        "models", Arrays.asList(
                                                Map.of("id", 1, "checked", true),
                                                Map.of("id", 2, "checked", false)
                                        )
                                )
                        )
                )
        ));









       String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/auth/user-register-with-garage")
                .then()
               .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);

    }



    @Test
    public void testGetUserAPI() throws JsonProcessingException {
       String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",("Bearer" + getSuperadminToken())) // Replace {your_token_here} with the actual token
                .when()
                .get(URL + "/api/v1.0/user")
                .then()

                .extract()
                .response()
                .asString();
        System.out.println(response);


    }





    @Test
    public void testUpdateUserInfoAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("first_Name", "John");
        requestBody.put("last_Name", "Doe");
        requestBody.put("email", "admin@gmail.com");
        requestBody.put("password", "12345678");
        requestBody.put("password_confirmation", "12345678");
        requestBody.put("phone", "1234567890");
        requestBody.put("address_line_1", "123 Main St");
        requestBody.put("address_line_2", "Apt 4B");
        requestBody.put("country", "USA");
        requestBody.put("city", "New York");
        requestBody.put("postcode", "12345");



     String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/update-user-info")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println();
    }





}
