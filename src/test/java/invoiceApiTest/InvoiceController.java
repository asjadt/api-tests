package invoiceApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;
public class InvoiceController {
    @Test
    public void testCreateInvoiceAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Invoice Name");
        requestBody.put("description", "Invoice Description");
        requestBody.put("logo", "https://example.com/invoice_logo.jpg");
        requestBody.put("invoice_title", "Invoice Title");
        requestBody.put("invoice_summary", "Invoice Summary");

        List<String> reminderDates = Arrays.asList("0", "15", "30");
        requestBody.put("reminder_dates", reminderDates);

        requestBody.put("send_reminder", 0);
        requestBody.put("business_name", "Business Name");
        requestBody.put("business_address", "Business Address");
        requestBody.put("sub_total", 900);
        requestBody.put("total_amount", 900);
        requestBody.put("invoice_date", "2019-06-29");
        requestBody.put("invoice_reference",("aaa") + Math.random() +("zzz"));

        requestBody.put("discount_description", "Discount Description");
        requestBody.put("discount_type", "fixed");
        requestBody.put("discount_amount", 10);
        requestBody.put("due_date", "2019-06-29");
        requestBody.put("status", "draft");
        requestBody.put("footer_text", "Footer Text");
        requestBody.put("shareable_link", "Shareable Link");
        requestBody.put("note", "Invoice Note");
        requestBody.put("business_type", "property_dealer");
        requestBody.put("property_id", 1034);
        requestBody.put("landlord_id", 528);
//        requestBody.put("tenant_id", 1);
//        requestBody.put("client_id", 1);

        List<Map<String, Object>> invoiceItems = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Item 1");
        item1.put("description", "Item 1 Description");
        item1.put("quantity", 1);
        item1.put("price", 1.1);
        item1.put("tax", 20);
        item1.put("amount", 300);
        invoiceItems.add(item1);

//        Map<String, Object> item2 = new HashMap<>();
//        item2.put("name", "Item 2");
//        item2.put("description", "Item 2 Description");
//        item2.put("quantity", 1);
//        item2.put("price", 1.1);
//        item2.put("tax", 20);
//        item2.put("amount", 300);
//        invoiceItems.add(item2);
//
//        Map<String, Object> item3 = new HashMap<>();
//        item3.put("name", "Item 3");
//        item3.put("description", "Item 3 Description");
//        item3.put("quantity", 1);
//        item3.put("price", 1.1);
//        item3.put("tax", 20);
//        item3.put("amount", 300);
//        item3.put("repair_id", 1);
//        invoiceItems.add(item3);

        requestBody.put("invoice_items", invoiceItems);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/invoices")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);


    }
    @Test
    public void testUpdateInvoiceAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 3146);
        requestBody.put("name", "Invoice Name");
        requestBody.put("description", "Invoice Description");
        requestBody.put("logo", "https://example.com/invoice_logo.jpg");
        requestBody.put("invoice_title", "Invoice Title");
        requestBody.put("invoice_summary", "Invoice Summary");

        List<String> reminderDates = Arrays.asList("0", "15", "30");
        requestBody.put("reminder_dates", reminderDates);

        requestBody.put("send_reminder", 0);
        requestBody.put("business_name", "Business Name");
        requestBody.put("business_address", "Business Address");
        requestBody.put("sub_total", 900);
        requestBody.put("total_amount", 900);
        requestBody.put("invoice_date", "2019-06-29");
        requestBody.put("invoice_reference", ("aaa") + Math.random() +("zzz"));

        requestBody.put("discount_description", "Discount Description");
        requestBody.put("discount_type", "fixed");
        requestBody.put("discount_amount", 10);
        requestBody.put("due_date", "2019-06-29");
        requestBody.put("status", "draft");
        requestBody.put("footer_text", "Footer Text");
        requestBody.put("shareable_link", "Shareable Link");
        requestBody.put("note", "Invoice Note");
        requestBody.put("business_type", "property_dealer");
        requestBody.put("property_id", 1034);
        requestBody.put("landlord_id", 528);
//        requestBody.put("tenant_id", 1);
//        requestBody.put("client_id", 1);

        List<Map<String, Object>> invoiceItems = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "Item 1");
        item1.put("description", "Item 1 Description");
        item1.put("quantity", 1);
        item1.put("price", 1.1);
        item1.put("tax", 20);
        item1.put("amount", 300);
        invoiceItems.add(item1);

//        Map<String, Object> item2 = new HashMap<>();
//        item2.put("name", "Item 2");
//        item2.put("description", "Item 2 Description");
//        item2.put("quantity", 1);
//        item2.put("price", 1.1);
//        item2.put("tax", 20);
//        item2.put("amount", 300);
//        invoiceItems.add(item2);
//
//        Map<String, Object> item3 = new HashMap<>();
//        item3.put("name", "Item 3");
//        item3.put("description", "Item 3 Description");
//        item3.put("quantity", 1);
//        item3.put("price", 1.1);
//        item3.put("tax", 20);
//        item3.put("amount", 300);
//        item3.put("repair_id", 1);
//        invoiceItems.add(item3);

        requestBody.put("invoice_items", invoiceItems);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoices")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testUpdateInvoiceStatusAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 3146); // Replace with the actual invoice ID you want to update
        requestBody.put("status", "draft"); // Replace with the desired status

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoices/change/status")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testMarkInvoiceSendAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 3146); // Replace with the actual invoice ID you want to mark as sent

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoices/mark/send")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testSendInvoiceAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 3146); // Replace with the actual invoice ID you want to send
        requestBody.put("from", "test@gmail.com");
        List<String> recipients = Arrays.asList("test1@gmail.com", "test2@gmail.com");
        requestBody.put("to", recipients);
        requestBody.put("subject", "Sample Subject");
        requestBody.put("message", "This is a sample message.");
        requestBody.put("copy_to_myself", 0); // 0 for not copying to oneself, 1 to copy
        requestBody.put("attach_pdf", 1); // 1 to attach PDF, 0 not to attach

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/invoices/send")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetInvoicesAPI() throws JsonProcessingException {
        // Define the path parameter values
        String perPage = "6";

        // Define query parameters
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String orderBy = "ASC";
        String searchKey = "";
        String status = "";
//        String invoiceReference = "1374";
//        String landlordId = "1";
//        String tenantId = "1";
//        String clientId = "1";
//        String propertyId = "1";
//        String minTotalDue = "1";
//        String maxTotalDue = "1";
//        String propertyIds = "1,2"; // Comma-separated list of property IDs

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("order_by", orderBy)
                .queryParam("search_key", searchKey)
                .queryParam("status", status)
//                .queryParam("invoice_reference", invoiceReference)
//                .queryParam("landlord_id", landlordId)
//                .queryParam("tenant_id", tenantId)
//                .queryParam("client_id", clientId)
//                .queryParam("property_id", propertyId)
//                .queryParam("min_total_due", minTotalDue)
//                .queryParam("max_total_due", maxTotalDue)
//                .queryParam("property_ids[]", propertyIds)
                .when()
                .get(URL + "/api/v1.0/invoices/{perPage}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetAllInvoicesAPI() throws JsonProcessingException {
        // Define query parameters
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String orderBy = "ASC";
        String searchKey = "";
        String status = "";
//        String invoiceReference = "1374";
//        String landlordId = "1";
//        String tenantId = "1";
//        String clientId = "1";
//        String propertyId = "1";
//        String minTotalDue = "1";
//        String totalDueMax = "1";
//        String propertyIds = "1,2"; // Comma-separated list of property IDs

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("order_by", orderBy)
                .queryParam("search_key", searchKey)
                .queryParam("status", status)
//                .queryParam("invoice_reference", invoiceReference)
//                .queryParam("landlord_id", landlordId)
//                .queryParam("tenant_id", tenantId)
//                .queryParam("client_id", clientId)
//                .queryParam("property_id", propertyId)
//                .queryParam("min_total_due", minTotalDue)
//                .queryParam("total_due_max", totalDueMax)
//                .queryParam("property_ids[]", propertyIds)
                .when()
                .get(URL + "/api/v1.0/invoices/get/all")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);

    }

    @Test
    public void testGetInvoiceById() throws Exception {
        // Define the request parameters
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", "6EIU3143kOnY"); // Replace with the actual invoice ID

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParams(pathParams)
                .when()
                .get(URL + "/api/v1.0/invoices/get/single/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


    @Test
    public void testGetInvoiceByReferenceAPI() throws JsonProcessingException {
        // Define the request path
        String reference = "0002"; // Replace with the desired reference
        String requestPath = URL + "/api/v1.0/invoices/get/single-by-reference/" + reference;

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .when()
                .get(requestPath)
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testDeleteInvoiceById() throws JsonProcessingException {


        // Set the invoice ID
        String invoiceId = "3143";

        // Perform the API request
     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
             .header("pin", "1234")
                .pathParam("id", invoiceId)
                .when()
                .delete(URL + "/api/v1.0/invoices/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }



}
