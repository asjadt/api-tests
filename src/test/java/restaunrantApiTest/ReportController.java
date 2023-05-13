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
import static restaunrantApiTest.Util.getToken;


public class ReportController {
    @Test
    public void testCustomerDashboardReportAPI() throws JsonProcessingException {
        String token = getToken();
        String customerId = "0";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .queryParam("customer_id", customerId)
                .when()
                .get(URL + "/api/customer-report")
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
    public void testRestaurantDashboardReportAPI() throws JsonProcessingException {
        String token = getToken();
        String restaurantId = "0";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .queryParam("restaurant_id", restaurantId)
                .when()
                .get(URL + "/api/restaurant-report")
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
    public void testGetTableReportAPI() throws JsonProcessingException {
        String token = getToken();
        String restaurantId = "1";

    String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/dashboard-report/get/table-report/{restaurantId}")
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
    public void testGetDashboardReportAPI() throws JsonProcessingException {
        String token = getToken();
        String restaurantId = "0";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/dashboard-report/{restaurantId}")
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
    public void testGetRestaurantReportAPI() throws JsonProcessingException {
        String token = getToken();

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/dashboard-report/restaurant/get")
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
    public void testGetDashboardReport2API() throws JsonProcessingException {
        String token = getToken();
        String restaurantId = "1"; // Provide the desired restaurant ID

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .param("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/dashboard-report2")
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
    public void testCustomerDishReportAPI() throws JsonProcessingException {
        String token = getToken();
        String phone = "1234567890"; // Provide the desired phone number
        String restaurantId = "1"; // Provide the desired restaurant ID

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("phone", phone)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/customer-dish-report/by-phone-number/{phone}/{restaurantId}")
                .then()
                .statusCode(404)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testCustomerDishReportByCustomerIdAPI() throws JsonProcessingException {
        String token = getToken();
        String customerId = "1"; // Provide the desired customer ID
        String restaurantId = "0"; // Provide the desired restaurant ID

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("customer_id", customerId)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/customer-dish-report/by-customer-id/{customer_id}/{restaurantId}")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }
}
