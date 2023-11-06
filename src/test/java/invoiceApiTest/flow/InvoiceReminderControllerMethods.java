package invoiceApiTest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;


public class InvoiceReminderControllerMethods {
    @Test
    public String testCreateInvoiceReminderNumberDateConvertAPI(String businessOwnerToken,Integer invoiceId) throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("send_reminder", 1);
        requestBody.put("reminder_date_amount", 14);
        requestBody.put("invoice_id", invoiceId);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/invoice-reminders/number-todate-convert")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }

    @Test
    public String testUpdateInvoiceReminderAPI(String businessOwnerToken,Integer invoiceReminderId) throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", invoiceReminderId);
        requestBody.put("send_reminder", "1");
        requestBody.put("reminder_date", "2019-06-29");

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoice-reminders")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }


    @Test
    public String testGetInvoiceRemindersAPI(String businessOwnerToken) throws JsonProcessingException {
        // Define the query parameters
        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("tenant_id", 1);
//        queryParams.put("landlord_id", 1);
        queryParams.put("start_date", "2019-06-29");
        queryParams.put("end_date", "2030-06-29");
        queryParams.put("order_by", "ASC");
        queryParams.put("search_key", "");

        // Define the perPage value (from the path)
        int perPage = 6;

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/invoice-reminders/" + perPage)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }


    @Test
    public String testGetInvoiceReminderByIdAPI(String businessOwnerToken,Integer invoiceReminderId) throws JsonProcessingException {
        // Define the path parameter


        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .when()
                .get(URL + "/api/v1.0/invoice-reminders/get/single/" + invoiceReminderId)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }
    @Test
    public String testDeleteInvoiceReminderByIdAPI(String businessOwnerToken,Integer invoiceReminderId) throws JsonProcessingException {
        // Define the path parameter


        // Perform the API request
        String response   =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .header("pin", "1234")
                .pathParam("id", invoiceReminderId)
                .when()
                .delete(URL + "/api/v1.0/invoice-reminders/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }



}
