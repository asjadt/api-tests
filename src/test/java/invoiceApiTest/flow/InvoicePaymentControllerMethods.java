package invoiceApiTest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;

public class InvoicePaymentControllerMethods {
    @Test
    public String testCreateInvoicePaymentAPI(String businessOwnerToken,Integer invoiceId) throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", 1); // Replace with the actual payment amount
        requestBody.put("payment_method", "bkash"); // Replace with the payment method
        requestBody.put("payment_date", "2019-06-29"); // Replace with the payment date
        requestBody.put("note", "note"); // Replace with the note
        requestBody.put("invoice_id", invoiceId); // Replace with the invoice ID
        requestBody.put("receipt_by", "receipt_by"); // Replace with the receipt by name

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/invoice-payments")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }

    @Test
    public String testUpdateInvoicePaymentAPI(String businessOwnerToken,Integer invoiceId,Integer invoicePaymentId) throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", invoicePaymentId); // Replace with the actual invoice payment ID you want to update
        requestBody.put("amount", 10); // Replace with the updated payment amount
        requestBody.put("payment_method", "bkash"); // Replace with the updated payment method
        requestBody.put("payment_date", "2019-06-29"); // Replace with the updated payment date
        requestBody.put("note", "note"); // Replace with the updated note
        requestBody.put("invoice_id", invoiceId); // Replace with the updated invoice ID
        requestBody.put("receipt_by", "receipt_by"); // Replace with the updated receipt by name

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoice-payments")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }


    @Test
    public String testGetInvoicePaymentsAPI(String businessOwnerToken) throws JsonProcessingException {
        // Define the path parameter value (perPage)
        String perPage = "6"; // Replace with the number of items per page you want

        // Define query parameters for filtering (replace with actual values)
        String invoiceId = "";
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String orderBy = "ASC";
        String searchKey = "";
//        String minAmount = "1";
//        String maxAmount = "1";
//        String paymentMethod = "1";

        // Perform the API request
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .pathParam("perPage", perPage)
                .queryParam("invoice_id", invoiceId)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("order_by", orderBy)
                .queryParam("search_key", searchKey)
//                .queryParam("min_amount", minAmount)
//                .queryParam("max_amount", maxAmount)
//                .queryParam("payment_method", paymentMethod)
                .when()
                .get(URL + "/api/v1.0/invoice-payments/{perPage}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }


    @Test
    public String testGetInvoicePaymentByIdV2API(String businessOwnerToken,String invoicePaymentId) throws JsonProcessingException {
        // Define the path parameter value (invoice payment ID)


        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .pathParam("id", invoicePaymentId)
                .when()
                .get(URL + "/api/v2.0/invoice-payments/get/single/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }

    @Test
    public String testDeleteInvoicePaymentByIdV2API(String businessOwnerToken,Integer invoicePaymentId) throws JsonProcessingException {
        // Define the path parameters (invoice payment ID and invoice ID)



        // Perform the API request
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .header("pin", "1234")
                .pathParam("id", invoicePaymentId)

                .when()
                .delete(URL + "/api/v1.0/invoice-payments/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }



}
