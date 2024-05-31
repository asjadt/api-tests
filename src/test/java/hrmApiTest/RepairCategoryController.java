package hrmApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.URL;
import static invoiceApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;
public class RepairCategoryController {
    @Test
    public void testCreateRepairCategoryAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("icon", "image.jpg");
        requestBody.put("name", "aaaaaaaddad");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/repair-categories")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testUpdateRepairCategoryAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 38); // Replace with the ID of the category you want to update
        requestBody.put("name", "dfthth");
        requestBody.put("icon", "Al.jpg");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/repair-categories")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetRepairCategoriesAPI() throws JsonProcessingException {
        // Prepare the query parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/repair-categories/6")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetAllRepairCategoriesOptimizedAPI() throws JsonProcessingException {
        // Prepare the query parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/repair-categories/get/all/optimized")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }



    @Test
    public void testGetRepairCategoryByIdAPI() throws JsonProcessingException {
        // Replace with the ID of the category you want to retrieve
        String categoryId = "gn";

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL + "/api/v1.0/repair-categories/get/single/" + categoryId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testDeleteRepairCategoryByIdAPI() throws JsonProcessingException {
        // ID of the repair category to be deleted
        String categoryId = "41"; // Replace with the ID you want to delete

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .header("password", "12345678We")
                .when()
                .delete(URL + "/api/v1.0/repair-categories/" + categoryId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

}
