package invoiceApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;

public class BillController {
    @Test
    public void testCreateBillAPI() throws JsonProcessingException {
        // Define the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("create_date", "2023-11-01");
        requestBody.put("payment_date", "2023-11-01");
        requestBody.put("property_id", 1034);
        requestBody.put("landlord_id", 528);
        requestBody.put("payment_mode", "card");
        requestBody.put("payabble_amount", 10.10);
        requestBody.put("deduction", 10.10);
        requestBody.put("remarks", "remarks");

        // Define the bill items
        List<Map<String, Object>> billItems = new ArrayList<>();
        Map<String, Object> billItem1 = new HashMap<>();
        billItem1.put("bill_item_id", 15);
        billItem1.put("item", "item");
        billItem1.put("description", "description");
        billItem1.put("amount", 10.1);
        Map<String, Object> billItem2 = new HashMap<>();
        billItem2.put("bill_item_id", 16);
        billItem2.put("item", "item");
        billItem2.put("description", "description");
        billItem2.put("amount", 10.1);
        billItems.add(billItem1);
        billItems.add(billItem2);
        requestBody.put("bill_items", billItems);




        // Define the bill items
        List<Map<String, Object>> saleItems = new ArrayList<>();
        Map<String, Object> saleItem1 = new HashMap<>();
        saleItem1.put("sale_id", 36);
        saleItem1.put("item", "item");
        saleItem1.put("description", "description");
        saleItem1.put("amount", 10.1);
        Map<String, Object> saleItem2 = new HashMap<>();
        saleItem2.put("sale_id", 37);
        saleItem2.put("item", "item");
        saleItem2.put("description", "description");
        saleItem2.put("amount", 10.1);
        saleItems.add(saleItem1);
        saleItems.add(saleItem2);
        requestBody.put("sale_items", saleItems);

        // Define the bill items
        List<Map<String, Object>> repairItems = new ArrayList<>();
//        Map<String, Object> repairItem1 = new HashMap<>();
//        repairItem1.put("repair_id", 62);
//        repairItem1.put("item", "item");
//        repairItem1.put("description", "description");
//        repairItem1.put("amount", 10.1);
//        Map<String, Object> repairItem2 = new HashMap<>();
//        repairItem2.put("repair_id", 63);
//        repairItem2.put("item", "item");
//        repairItem2.put("description", "description");
//        repairItem2.put("amount", 10.1);
//        repairItems.add(repairItem1);
//        repairItems.add(repairItem2);
        requestBody.put("repair_items", repairItems);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/bills")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUpdateBillAPI() throws JsonProcessingException {
        // Define the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 76); // Replace with the actual bill ID
        requestBody.put("name", "Updated Bill Name"); // Replace with the desired name
        requestBody.put("description", "Updated Bill Description"); // Replace with the desired description
        requestBody.put("create_date", "2023-11-01");
        requestBody.put("payment_date", "2023-11-01");
        requestBody.put("property_id", 1034);
        requestBody.put("landlord_id", 528);
        requestBody.put("payment_mode", "card");
        requestBody.put("payabble_amount", 10.10); // Replace with the desired payable amount
        requestBody.put("deduction", 10.10); // Replace with the desired deduction amount
        requestBody.put("remarks", "Updated Remarks"); // Replace with the desired remarks


        // Define the bill items
        List<Map<String, Object>> billItems = new ArrayList<>();
        Map<String, Object> billItem1 = new HashMap<>();
        billItem1.put("bill_item_id", 15);
        billItem1.put("item", "item");
        billItem1.put("description", "description");
        billItem1.put("amount", 10.1);
        Map<String, Object> billItem2 = new HashMap<>();
        billItem2.put("bill_item_id", 16);
        billItem2.put("item", "item");
        billItem2.put("description", "description");
        billItem2.put("amount", 10.1);
        billItems.add(billItem1);
        billItems.add(billItem2);
        requestBody.put("bill_items", billItems);




        // Define the bill items
        List<Map<String, Object>> saleItems = new ArrayList<>();
        Map<String, Object> saleItem1 = new HashMap<>();
        saleItem1.put("sale_id", 36);
        saleItem1.put("item", "item");
        saleItem1.put("description", "description");
        saleItem1.put("amount", 10.1);
        Map<String, Object> saleItem2 = new HashMap<>();
        saleItem2.put("sale_id", 37);
        saleItem2.put("item", "item");
        saleItem2.put("description", "description");
        saleItem2.put("amount", 10.1);
        saleItems.add(saleItem1);
        saleItems.add(saleItem2);
        requestBody.put("sale_items", saleItems);

        // Define the bill items
        List<Map<String, Object>> repairItems = new ArrayList<>();
//        Map<String, Object> repairItem1 = new HashMap<>();
//        repairItem1.put("repair_id", 62);
//        repairItem1.put("item", "item");
//        repairItem1.put("description", "description");
//        repairItem1.put("amount", 10.1);
//        Map<String, Object> repairItem2 = new HashMap<>();
//        repairItem2.put("repair_id", 63);
//        repairItem2.put("item", "item");
//        repairItem2.put("description", "description");
//        repairItem2.put("amount", 10.1);
//        repairItems.add(repairItem1);
//        repairItems.add(repairItem2);
        requestBody.put("repair_items", repairItems);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/bills")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testGetBillsAPI() throws JsonProcessingException {
        // Define the request parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
        queryParams.put("status", "");
        queryParams.put("invoice_reference", "");
//        queryParams.put("landlord_id", 1);
//        queryParams.put("tenant_id", 1);
//        queryParams.put("client_id", 1);
//        queryParams.put("property_id", 1);
//        queryParams.put("min_total_due", 1);
//        queryParams.put("max_total_due", 1);

//        List<Integer> propertyIds = Arrays.asList(1, 2);
//        queryParams.put("property_ids[]", propertyIds);

        // Perform the API request
        String response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                        .queryParams(queryParams)
                        .when()
                        .get(URL + "/api/v1.0/bills/{perPage}", 6) // Replace 6 with the desired perPage value
                        .then()
                        .statusCode(200) // Assuming a successful bill retrieval should return status code 200
                        .extract()
                        .response()
                        .asString();
        System.out.println(response);
        // Add more assertions and validations as needed for the response
    }


    @Test
    public void testGetAllBillsAPI() throws JsonProcessingException {
        // Define query parameters
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");
        queryParams.put("status", "");
        queryParams.put("invoice_reference", "");
//        queryParams.put("landlord_id", "1");
//        queryParams.put("min_amount", "1");
//        queryParams.put("max_amount", "1");
//        queryParams.put("tenant_id", "1");
//        queryParams.put("client_id", "1");
//        queryParams.put("property_id", "1");
//        queryParams.put("property_ids[]", Arrays.asList("1", "2"));
//        queryParams.put("min_total_due", "1");
//        queryParams.put("total_due_max", "1");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/bills/get/all")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetBillByIdAPI() throws JsonProcessingException {
        // Define the ID parameter for the path
        String billId = "ZSqh724ESi";

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParam("id", billId)
                .when()
                .get(URL + "/api/v1.0/bills/get/single/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeleteBillByIdAPI() throws JsonProcessingException {
        // Define the ID parameter for the path
        Integer billId = 72;

        // Perform the API request
 String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
         .header("pin", "1234")
                .pathParam("id", billId)
                .when()
                .delete(URL + "/api/v1.0/bills/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString(); // Assuming a successful response should return status code 200

        System.out.println(response);
    }


}
