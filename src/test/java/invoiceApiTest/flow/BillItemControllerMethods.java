package invoiceApiTest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static invoiceApiTest.Util.URL;
import static invoiceApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;

public class BillItemControllerMethods {



    @Test
    public String testCreateBillItemAPI(String superAdminToken) throws JsonProcessingException {
// Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "New Bill Item");
        requestBody.put("description", "This is a new bill item.");
        requestBody.put("price", 10.10);

// Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + superAdminToken))
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/bill-items")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
        return response;


    }

    @Test
    public void testUpdateBillItemAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 14);
        requestBody.put("name", "name");
        requestBody.put("description", "description");
        requestBody.put("price", 10.10);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/bill-items")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);


    }
    @Test
    public void testGetBillItemsAPI() throws JsonProcessingException {
        // Prepare the request parameters
        String perPage = "6";
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String orderBy = "ASC";
        String searchKey = "";

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token
                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("order_by", orderBy)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/bill-items/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);

        // Validate the response
        // TODO: Implement validations
    }

    @Test
    public void testGetBillItemByIdAPI() throws JsonProcessingException {
        // Specify the ID for the bill item you want to retrieve
        String billItemId = "c5jY14a5EV";  // Change this to the desired bill item ID

        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken()) // Replace {your_token} with the actual token
                .when()
                .get(URL + "/api/v1.0/bill-items/get/single/{id}",billItemId)
                .then()
                .statusCode(200) // Assert that the response code is 200 (Successful operation)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public String testDeleteBillItemByIdAPI(String superAdminToken,Integer id) throws JsonProcessingException {
        // Prepare the request path
        String path = URL + "/api/v1.0/bill-items/{id}";

        // Set the bill item ID


        // Perform the API request
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + superAdminToken)) // Replace {your_token} with the actual token
                .header("password", "12345678We")
                .pathParam("id", id)
                .when()
                .delete(path)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }



}
