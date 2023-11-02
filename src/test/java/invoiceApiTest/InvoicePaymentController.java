package invoiceApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;

public class InvoicePaymentController {
    @Test
    public void testCreateInvoicePaymentAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", 1); // Replace with the actual payment amount
        requestBody.put("payment_method", "bkash"); // Replace with the payment method
        requestBody.put("payment_date", "2019-06-29"); // Replace with the payment date
        requestBody.put("note", "note"); // Replace with the note
        requestBody.put("invoice_id", 3147); // Replace with the invoice ID
        requestBody.put("receipt_by", "receipt_by"); // Replace with the receipt by name

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/invoice-payments")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testUpdateInvoicePaymentAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 170); // Replace with the actual invoice payment ID you want to update
        requestBody.put("amount", 10); // Replace with the updated payment amount
        requestBody.put("payment_method", "bkash"); // Replace with the updated payment method
        requestBody.put("payment_date", "2019-06-29"); // Replace with the updated payment date
        requestBody.put("note", "note"); // Replace with the updated note
        requestBody.put("invoice_id", 3147); // Replace with the updated invoice ID
        requestBody.put("receipt_by", "receipt_by"); // Replace with the updated receipt by name

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoice-payments")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


    @Test
    public void testGetInvoicePaymentsAPI() throws JsonProcessingException {
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
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
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
    }


    @Test
    public void testGetInvoicePaymentByIdV2API() throws JsonProcessingException {
        // Define the path parameter value (invoice payment ID)
        String invoicePaymentId = "pMOT1691RZO"; // Replace with the actual invoice payment ID you want to retrieve

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParam("id", invoicePaymentId)
                .when()
                .get(URL + "/api/v2.0/invoice-payments/get/single/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeleteInvoicePaymentByIdV2API() throws JsonProcessingException {
        // Define the path parameters (invoice payment ID and invoice ID)
        String invoicePaymentId = "169"; // Replace with the actual invoice payment ID you want to delete


        // Perform the API request
     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
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
    }



}
