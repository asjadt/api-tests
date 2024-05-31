package hrmApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;
public class SaleItemController {
    @Test
    public void testCreateSaleItemAPI() throws JsonProcessingException {
        // Define the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Sample Sale Item Name"); // Replace with the desired name
        requestBody.put("description", "Sample Sale Item Description"); // Replace with the desired description
        requestBody.put("price", 10.10); // Replace with the desired price

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/sale-items")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testUpdateSaleItemAPI() throws JsonProcessingException {
        // Define the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 34); // Replace with the ID of the sale item you want to update
        requestBody.put("name", "Updated Sale Item Name"); // Replace with the desired name
        requestBody.put("description", "Updated Sale Item Description"); // Replace with the desired description
        requestBody.put("price", 15.50); // Replace with the desired price

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/sale-items")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetSaleItemsAPI() throws JsonProcessingException {
        // Define query parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29"); // Replace with the desired start date
        queryParams.put("end_date", "2030-06-29"); // Replace with the desired end date
        queryParams.put("order_by", "ASC"); // Replace with the desired order_by value
        queryParams.put("search_key", ""); // Replace with the desired search_key

        // Define the path parameter
        int perPage = 6; // Replace with the desired perPage value

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/sale-items/{perPage}", perPage)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetSaleItemByIdAPI() throws JsonProcessingException {
        // Define the path parameter
        String id = "bCpQ33K1Ic"; // Replace with the desired sale item ID

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParam("id", id)
                .when()
                .get(URL + "/api/v1.0/sale-items/get/single/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testDeleteSaleItemByIdAPI() throws JsonProcessingException {
        // Define the path parameter
        Integer id = 34; // Replace with the desired sale item ID

        // Perform the API request
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .header("pin", "1234")
                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/sale-items/{id}")
                .then()
                .statusCode(200) // Assuming a successful deletion should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


}

