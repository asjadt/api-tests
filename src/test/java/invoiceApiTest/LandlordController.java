package invoiceApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;
public class LandlordController {
    @Test
    public void testCreateLandlordAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("image", "image.jpg");
        requestBody.put("first_Name", "Rifat");
        requestBody.put("last_Name", "Al");
        requestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        requestBody.put("phone", "01771034383");
        requestBody.put("address_line_1", "dhaka");
        requestBody.put("address_line_2", "dinajpur");
        requestBody.put("country", "Bangladesh");
        requestBody.put("city", "Dhaka");
        requestBody.put("postcode", "1207");
        requestBody.put("lat", "1207");
        requestBody.put("long", "1207");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Replace with your method to get the bearer token
                .body(requestBody)
                .when()
                .post(URL +"/api/v1.0/landlords") // Adjust the URL if needed
                .then()
                .statusCode(201) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUpdateLandlordAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 525);
        requestBody.put("image", "image.jpg");
        requestBody.put("first_Name", "Rifat");
        requestBody.put("last_Name", "Al");
        requestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        requestBody.put("phone", "01771034383");
        requestBody.put("address_line_1", "dhaka");
        requestBody.put("address_line_2", "dinajpur");
        requestBody.put("country", "Bangladesh");
        requestBody.put("city", "Dhaka");
        requestBody.put("postcode", "1207");
        requestBody.put("lat", "1207");
        requestBody.put("long", "1207");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/landlords") // Assuming you have the correct URL
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetLandlordsAPI() throws JsonProcessingException {
        // Prepare query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
//        queryParams.put("property_id", "1");
//        queryParams.put("property_ids[]", "1,2");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .queryParams(queryParams)
                .pathParam("perPage", "6")
                .when()
                .get(URL + "/api/v1.0/landlords/{perPage}")
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void getLandlordsOptimized() throws JsonProcessingException {
        // Prepare query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
//        queryParams.put("property_id", "1");
//        queryParams.put("property_ids[]", "1,2");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .queryParams(queryParams)
                .pathParam("perPage", "6")
                .when()
                .get(URL + "/api/v1.0/landlords/optimized/{perPage}")
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void getAllLandlords() throws JsonProcessingException {
        // Prepare query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
//        queryParams.put("property_id", "1");
//        queryParams.put("property_ids[]", "1,2");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .queryParams(queryParams)
                .when()
                .get(URL +"/api/v1.0/landlords/get/all")
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAllLandlordsOptimizedAPI() throws JsonProcessingException {
        // Prepare query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
//        queryParams.put("property_id", "1");
//        queryParams.put("property_ids[]", "1,2");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .queryParams(queryParams)
                .when()
                .get(URL +"/api/v1.0/landlords/get/all/optimized")
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetLandlordByIdAPI() throws JsonProcessingException {
        // Define the tenant ID you want to retrieve
        String landlordId = "fTlf525q8ZZ"; // Replace with the actual tenant ID you want to retrieve

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .pathParam("id", landlordId)
                .when()
                .get(URL + "/api/v1.0/landlords/get/single/{id}")
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testDeleteLandlordByIdAPI() throws JsonProcessingException {
        // Prepare path parameter
        String landlordId = "525"; // Replace with the actual tenant ID you want to delete

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken()) // Assuming you have a method to get the bearer token
                .header("pin", "1234")
                .pathParam("id", landlordId)
                .when()
                .delete(URL +"/api/v1.0/landlords/{id}")
                .then()
                .statusCode(200) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
}
