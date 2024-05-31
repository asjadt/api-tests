package hrmApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;

public class PropertyController {
    @Test
    public void testCreatePropertyAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Rifat");
        requestBody.put("description", "Sample description");
        requestBody.put("logo", "https://example.com/logo.jpg");
        requestBody.put("image", "https://example.com/image.jpg");
        requestBody.put("address", "Sample Address");
        requestBody.put("country", "Sample Country");
        requestBody.put("city", "Dhaka");
        requestBody.put("postcode", "1207");
        requestBody.put("town", "Sample Town");
        requestBody.put("lat", "23.704263332849386");
        requestBody.put("long", "90.44707059805279");
        requestBody.put("type", "sample_type");
        requestBody.put("reference_no", ("aaa") + Math.random() +("zzz"));
        requestBody.put("landlord_id", "528");
        List<Integer> tenantIds = Arrays.asList(530,531);
        requestBody.put("tenant_ids", tenantIds);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/properties")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUpdatePropertyAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1032);
        requestBody.put("name", "Updated Property Name");
        requestBody.put("description", "Updated description");
        requestBody.put("logo", "https://example.com/updated_logo.jpg");
        requestBody.put("image", "https://example.com/updated_image.jpg");
        requestBody.put("address", "Updated Address");
        requestBody.put("country", "Updated Country");
        requestBody.put("city", "Updated City");
        requestBody.put("postcode", "12345");
        requestBody.put("town", "Updated Town");
        requestBody.put("lat", "12.3456");
        requestBody.put("long", "78.9012");
        requestBody.put("type", "Updated Type");
        requestBody.put("reference_no", ("aaa") + Math.random() +("zzz"));
        requestBody.put("landlord_id", "527");
        List<Integer> tenantIds = Arrays.asList(530,531);
        requestBody.put("tenant_ids", tenantIds);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/properties")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetPropertiesAPI() throws JsonProcessingException {
        // Define query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
        queryParams.put("address", "");
        queryParams.put("landlord_id", "527");
        queryParams.put("tenant_id", "530");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/properties/{perPage}", 6)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetAllPropertiesAPI() throws JsonProcessingException {
        // Define query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
        queryParams.put("address", "");
        queryParams.put("landlord_id", "527");
        queryParams.put("tenant_id", "530");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/properties/get/all")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetAllPropertiesOptimizedAPI() throws JsonProcessingException {
        // Define query parameters
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
        queryParams.put("address", "");
        queryParams.put("landlord_id", "527");
        queryParams.put("tenant_id", "530");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/properties/get/all/optimized")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetPropertyByIdAPI() throws JsonProcessingException {
        // Define the ID parameter
        String id = "FwFi1030RxK4"; // Replace with the desired property ID

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .when()
                .get(URL + "/api/v1.0/properties/get/single/{id}", id)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeletePropertyByIdAPI() throws JsonProcessingException {
        // Define the ID parameter
        Integer id = 1030; // Replace with the desired property ID

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .header("pin", "1234")
                .when()
                .delete(URL + "/api/v1.0/properties/{id}", id)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGeneratePropertyReferenceNumberAPI() throws JsonProcessingException {
        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .when()
                .get(URL + "/api/v1.0/properties/generate/property-reference_no")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testValidatePropertyReferenceNumberAPI() throws JsonProcessingException {
        // Define the reference number parameter
        String referenceNo = "1"; // Replace with the desired reference number

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .when()
                .get(URL + "/api/v1.0/properties/validate/property-reference_no/{reference_no}", referenceNo)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }



}
