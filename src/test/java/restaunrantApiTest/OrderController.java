package restaunrantApiTest;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.reqres.PostData;
import groovyjarjarasm.asm.TypeReference;
import in.reqres.TestPostRequests;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getSuperadminToken;

public class OrderController {
    @Test
    public void testStoreOrderAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        int restaurantId = 1; // Specify the ID of the restaurant
        String requestBody = "{\"amount\": 50, \"customer_id\": 1, \"customer_name\": \"test\", \"remarks\": \"test\", \"table_number\": \"5\", \"type\": \"test\", \"phone\": \"0111\", \"address\": \"test\", \"post_code\": \"post_code\", \"discount\": \"10\", \"cash\": \"10\", \"card\": \"10\", \"dishes\": [{\"qty\": 10, \"dish_id\": 1, \"variation_ids\": [1, 2]}, {\"qty\": 10, \"meal_id\": 1, \"meal_dishes\": [{\"dish_id\": \"1\", \"variation_ids\": [1, 2]}]}], \"request_object\": \"{}\"}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/order/" + restaurantId)
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();



        System.out.println(response);
    }

    @Test
    public void testStoreByUserAPI() throws JsonProcessingException {

this.testEditOrderAPI();
        String token = getSuperadminToken();
        int restaurantId = 1; // Specify the ID of the restaurant
        String requestBody = "{\n" +
                "  \"amount\": 50,\n" +
                "  \"customer_id\": 1,\n" +
                "  \"customer_name\": \"test\",\n" +
                "  \"remarks\": \"test\",\n" +
                "  \"table_number\": \"5\",\n" +
                "  \"type\": \"test\",\n" +
                "  \"phone\": \"0111\",\n" +
                "  \"address\": \"test\",\n" +
                "  \"post_code\": \"post_code\",\n" +
                "  \"discount\": \"10\",\n" +
                "  \"cash\": \"10\",\n" +
                "  \"card\": \"10\",\n" +
                "  \"dishes\": [\n" +
                "    {\n" +
                "      \"qty\": 10,\n" +
                "      \"dish_id\": 1,\n" +
                "      \"variation_ids\": [1, 2]\n" +
                "    },\n" +
                "    {\n" +
                "      \"qty\": 10,\n" +
                "      \"meal_id\": 1,\n" +
                "      \"meal_dishes\": [\n" +
                "        {\n" +
                "          \"dish_id\": \"1\",\n" +
                "          \"variation_ids\": [1, 2]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"request_object\": \"{}\"\n" +
                "}";

      String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL +"/api/order/orderbyuser/" + restaurantId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



        System.out.println(response);
    }


    @Test
    public void testOrderCompleteAPI() throws JsonProcessingException {
       // Replace with the base URL of your API

        String token = getSuperadminToken();
        int orderId = 2; // Specify the ID of the order
        String requestBody = "{\n" +
                "  \"card\": 50,\n" +
                "  \"cash\": 200\n" +
                "}";

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL + "/api/order/" + orderId)
                .then()
                .statusCode(200)
             .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testUpdateStatusAPI() throws JsonProcessingException {
         // Replace with the base URL of your API

        String token = getSuperadminToken();
        int orderId = 2; // Specify the ID of the order
        String requestBody = "{\n" +
                "  \"status\": \"active\"\n" +
                "}";

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL +"/api/order/updatestatus/" + orderId)
                .then()
                .statusCode(200) .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }


    @Test
    public void testEditOrderAPI() throws JsonProcessingException {


        String token = getSuperadminToken();
        int orderId = 2; // Specify the ID of the order
        String requestBody = "{\n" +
                "  \"amount\": 50,\n" +
                "  \"customer_name\": \"test\",\n" +
                "  \"remarks\": \"test\",\n" +
                "  \"table_number\": \"5\",\n" +
                "  \"type\": \"test\",\n" +
                "  \"phone\": \"0111\",\n" +
                "  \"address\": \"test\",\n" +
                "  \"post_code\": \"post_code\",\n" +
                "  \"discount\": \"10\",\n" +
                "  \"cash\": \"10\",\n" +
                "  \"card\": \"10\",\n" +
                "  \"request_object\": \"{}\",\n" +
                "  \"dishes\": [\n" +
                "    {\n" +
                "      \"qty\": 10,\n" +
                "      \"dish_id\": 1,\n" +
                "      \"variation_ids\": [1, 2]\n" +
                "    },\n" +
                "    {\n" +
                "      \"qty\": 10,\n" +
                "      \"meal_id\": 1,\n" +
                "      \"meal_dishes\": [\n" +
                "        {\n" +
                "          \"dish_id\": \"1\",\n" +
                "          \"variation_ids\": [1, 2]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL +"/api/order/edit/order/" + orderId)
                .then()

                .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetOrderByIdAPI() throws JsonProcessingException {


        String token = getSuperadminToken();
        int orderId = 2; // Specify the ID of the order

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/" + orderId)
                .then()
                .statusCode(200)  .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }
    @Test
    public void testGetOrderById2API() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost:8000"; // Replace with the base URL of your API

        String token = getSuperadminToken();
        int orderId = 1; // Specify the ID of the order
        int restaurantId = 1; // Specify the ID of the restaurant

     String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/by-restaurant/" + orderId + "/" + restaurantId)
                .then()
                .statusCode(200) .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetOrderByCustomerIdAPI() throws JsonProcessingException {


        String token = getSuperadminToken();
        int customerId = 1; // Specify the ID of the customer

    String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/getorderby/customerid/" + customerId)
                .then()
                .statusCode(200) .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }
    @Test
    public void testGetTodaysOrderByStatusAPI() throws JsonProcessingException {
       // Replace with the base URL of your API

        String token = getSuperadminToken();
        int status = 1; // Specify the status

      String  response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/orderlist/today/" + status)
                .then()
                .statusCode(200) .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetAllOrderAPI() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost:8000"; // Replace with the base URL of your API

        String token = getSuperadminToken();

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/All/order")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }
    @Test
    public void testGetAllOrderTodayAPI() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost:8000"; // Replace with the base URL of your API
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String token = getSuperadminToken();

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/All/order/today/" + restaurantId)
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetAllOrderEveryDayAPI() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost:8000"; // Replace with the base URL of your API
        String perPage = "10"; // Replace with the desired value
        String restaurantId = "1"; // Replace with the actual restaurant ID
        String fromDate = "2019-06-29"; // Replace with the desired from_date value
        String toDate = "2019-06-29"; // Replace with the desired to_date value
        String minAmount = "10"; // Replace with the desired min_amount value
        String maxAmount = "100"; // Replace with the desired max_amount value
        String tableNumber = "100"; // Replace with the desired table_number value
        String customerName = "John Doe"; // Replace with the desired customer_name value
        String customerPhone = "1234567890"; // Replace with the desired customer_phone value
        String types = "type1,type2,type3"; // Replace with the desired types (comma-separated)
        String statuses = "status1,status2,status3"; // Replace with the desired statuses (comma-separated)

        String token = getSuperadminToken();

    String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .queryParam("from_date", fromDate)
                .queryParam("to_date", toDate)
                .queryParam("min_amount", minAmount)
                .queryParam("max_amount", maxAmount)
                .queryParam("table_number", tableNumber)
                .queryParam("customer_name", customerName)
                .queryParam("customer_phone", customerPhone)
                .queryParam("type[]", types)
                .queryParam("status[]", statuses)
                .when()
                .get(URL +"/api/order/All/order/every/" + perPage + "/" + restaurantId)
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }



































    @Test
    public void testGetOrderByTypeAPI() throws JsonProcessingException {

        String type = "eat-----in"; // Replace with the actual type

        String token = getSuperadminToken();

        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("type", type)
                .when()
                .get(URL +"/api/order/All/order-by-type/{type}")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }


    @Test
    public void testGetOrderByType2API() throws JsonProcessingException {

        String type = "eat-----in"; // Replace with the actual type
        int restaurantId = 1; // Replace with the actual restaurant ID

        String token = getSuperadminToken();

        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("type", type)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/order/All/order-by-type/by-restaurant/{type}/{restaurantId}")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetAllPendingOrderAPI() throws JsonProcessingException {

        int restaurantId = 1; // Replace with the actual restaurant ID

        String token = getSuperadminToken();

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/order/All/pending/order/{restaurantId}")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetAllPendingOrderWithPaginationAPI() throws JsonProcessingException {

        int restaurantId = 1; // Replace with the actual restaurant ID
        int perPage = 10; // Replace with the desired number of items per page

        String token = getSuperadminToken();

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("perPage", perPage)
                .when()
                .get(URL +"/api/order/All/pending/order/{restaurantId}/{perPage}")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetAllAutoPrintOrderAPI() throws JsonProcessingException {

        int restaurantId = 1; // Replace with the actual restaurant ID

        String token = getSuperadminToken();

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/order/All/autoprint/order/{restaurantId}")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }
    @Test
    public void testGetDailyOrderReportAPI() throws JsonProcessingException {


        String token = getSuperadminToken();

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/get/daily/order/report")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }


    @Test
    public void testGetOrderReportAPI() throws JsonProcessingException {


        String token = getSuperadminToken();
        String min = "0";
        String max = "99";
        String fromDate = "2019-06-29";
        String toDate = "2023-06-29";
        String status = "pending";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("min", min)
                .pathParam("max", max)
                .pathParam("fromdate", fromDate)
                .pathParam("todate", toDate)
                .pathParam("status", status)
                .when()
                .get(URL +"/api/order/oderreporting/{min}/{max}/{fromdate}/{todate}/{status}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetOrderReportByRestaurantIdAPI() throws JsonProcessingException {


        String token = getSuperadminToken();
        String restaurantId = "1";
        String min = "0";
        String max = "99";
        String fromDate = "2019-06-29";
        String toDate = "2023-06-29";
        String status = "pending";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("min", min)
                .pathParam("max", max)
                .pathParam("fromdate", fromDate)
                .pathParam("todate", toDate)
                .pathParam("status", status)
                .when()
                .get(URL +"/api/order/oderreporting/{restaurantId}/{min}/{max}/{fromdate}/{todate}/{status}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetOrderReportByRestaurantId2API() throws JsonProcessingException {


        String token = getSuperadminToken();
        String restaurantId = "1";
        String fromDate = "2019-06-29";
        String toDate = "2023-06-29";
        String status = "pending";

        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("fromdate", fromDate)
                .pathParam("todate", toDate)
                .pathParam("status", status)
                .when()
                .get(URL +"/api/order/oderreporting/{restaurantId}/{fromdate}/{todate}/{status}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }

    @Test
    public void testGetOrderByUserAPI() throws JsonProcessingException {

        String token = getSuperadminToken();

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/order/byuser/all/order")
                .then()
                .statusCode(200).extract()
                .response()
                .body()
                .asString();



        System.out.println("response" + response);
    }
    @Test
    public void testDeleteOrderAPI() throws JsonProcessingException {
        String orderId = "1"; // Replace with the actual order ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .pathParam("orderId", orderId)
                .when()
                .delete(URL +"/api/order/{orderId}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }

}
