package invoiceApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;

public class ReceiptController {
    @Test
    public void testCreateReceiptAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("tenant_id", 532);
        requestBody.put("tenant_name", "tenant_name");
        requestBody.put("property_address", "Sample Address");
        requestBody.put("amount", 100);
        requestBody.put("receipt_by", "receipt_by");
        requestBody.put("receipt_date", "2019-06-29");
        requestBody.put("notes", "notes");
        requestBody.put("payment_method", "payment_method");

        List<Map<String, Object>> saleItems = new ArrayList<>();
        Map<String, Object> saleItem1 = new HashMap<>();
        saleItem1.put("sale_id", "38");
        saleItem1.put("item", "item");
        saleItem1.put("description", "description");
        saleItem1.put("amount", 10.1);

        Map<String, Object> saleItem2 = new HashMap<>();
        saleItem2.put("sale_id", "39");
        saleItem2.put("item", "item");
        saleItem2.put("description", "description");
        saleItem2.put("amount", 10.1);

        saleItems.add(saleItem1);
        saleItems.add(saleItem2);

        requestBody.put("sale_items", saleItems);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/receipts")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUpdateReceiptAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 37);
        requestBody.put("tenant_id", 532);
        requestBody.put("tenant_name", "tenant_name");
        requestBody.put("property_address", "Sample Address");
        requestBody.put("amount", 100);
        requestBody.put("receipt_by", "receipt_by");
        requestBody.put("receipt_date", "2019-06-29");
        requestBody.put("notes", "notes");
        requestBody.put("payment_method", "payment_method");

        List<Map<String, Object>> saleItems = new ArrayList<>();
        Map<String, Object> saleItem1 = new HashMap<>();
        saleItem1.put("sale_id", "38");
        saleItem1.put("item", "item");
        saleItem1.put("description", "description");
        saleItem1.put("amount", 10.1);

        Map<String, Object> saleItem2 = new HashMap<>();
        saleItem2.put("sale_id", "39");
        saleItem2.put("item", "item");
        saleItem2.put("description", "description");
        saleItem2.put("amount", 10.1);

        saleItems.add(saleItem1);
        saleItems.add(saleItem2);

        requestBody.put("sale_items", saleItems);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/receipts")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetReceiptsAPI() throws JsonProcessingException {
        // Define the path parameter
        int perPage = 6; // Replace with the perPage value you want

        // Define the query parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
//        queryParams.put("min_amount", 10);
//        queryParams.put("max_amount", 10);
//        queryParams.put("property_ids[]", "1,2"); // Replace with property IDs

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/receipts/" + perPage)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetReceiptByIdAPI() throws JsonProcessingException {
        // Define the path parameter
        String id = "3WOJ36bYuq"; // Replace with the ID you want to retrieve

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .when()
                .get(URL + "/api/v1.0/receipts/get/single/" + id)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeleteReceiptByIdAPI() throws JsonProcessingException {
        // Define the path parameter
        Integer id = 36; // Replace with the ID you want to delete

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .header("pin", "1234")
                .when()
                .delete(URL + "/api/v1.0/receipts/" + id)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


}
