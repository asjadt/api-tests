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


public class RestaurantController {
    @Test
    public void testStoreRestaurantAPI() throws JsonProcessingException {
        String token = getToken();

        String requestBody = "{\n" +
                "    \"Name\": \"Sample Restaurant\",\n" +
                "    \"Address\": \"Sample Address\",\n" +
                "    \"PostCode\": \"12345\",\n" +
                "    \"enable_question\": true,\n" +
                "    \"is_eat_in\": \"0\",\n" +
                "    \"is_delivery\": \"0\",\n" +
                "    \"is_take_away\": \"0\",\n" +
                "    \"is_customer_order\": \"0\"\n" +
                "}";

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/restaurant")
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
    public void testStoreRestaurantByOwnerIdAPI() throws JsonProcessingException {
        String token = getToken();

        String requestBody = "{\n" +
                "    \"Name\": \"Sample Restaurant\",\n" +
                "    \"Address\": \"Sample Address\",\n" +
                "    \"PostCode\": \"12345\",\n" +
                "    \"OwnerID\": \"123\",\n" +
                "    \"enable_question\": true,\n" +
                "    \"is_eat_in\": \"0\",\n" +
                "    \"is_delivery\": \"0\",\n" +
                "    \"is_take_away\": \"0\",\n" +
                "    \"is_customer_order\": \"0\"\n" +
                "}";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/restaurant/by-owner-id")
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
    public void testUpdateRestaurantDetailsAPI() throws JsonProcessingException {
        String token = getToken();
        Integer restaurantId = 1;
        String requestBody = "{\n" +
                "    \"Name\": \"Updated Restaurant\",\n" +
                "    \"Layout\": \"Updated Layout\",\n" +
                "    \"Address\": \"Updated Address\",\n" +
                "    \"PostCode\": \"Updated PostCode\",\n" +
                "    \"enable_question\": true,\n" +
                "    \"totalTables\": 1,\n" +
                "    \"EmailAddress\": \"Updated Email\",\n" +
                "    \"homeText\": \"Updated Home Text\",\n" +
                "    \"AdditionalInformation\": \"Updated Additional Information\",\n" +
                "    \"Webpage\": \"Updated Webpage\",\n" +
                "    \"PhoneNumber\": \"Updated Phone Number\",\n" +
                "    \"About\": \"Updated About\",\n" +
                "    \"is_eat_in\": \"0\",\n" +
                "    \"is_delivery\": \"0\",\n" +
                "    \"is_take_away\": \"0\",\n" +
                "    \"is_customer_order\": \"0\",\n" +
                "    \"Key_ID\": \"0\",\n" +
                "    \"review_type\": \"0\",\n" +
                "    \"google_map_iframe\": \"test\",\n" +
                "    \"Is_guest_user\": false,\n" +
                "    \"is_review_silder\": false\n" +
                "}";

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurentId", restaurantId)
                .queryParam("_method", "PATCH")
                .body(requestBody)
                .when()
                .patch(URL + "/api/restaurant/UpdateResturantDetails/{restaurentId}")
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
    public void testGetRestaurantByIdAPI() throws JsonProcessingException {
        String token = getToken();
        String restaurantId = "1";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/restaurant/{restaurantId}")
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
    public void testGetAllRestaurantsAPI() throws JsonProcessingException {
        String token = getToken();
        String searchKey = "restaurant name";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/restaurant")
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
    public void testGetRestaurantsAPI() throws JsonProcessingException {
        String token = getToken();
        int perPage = 10;
        String searchKey = "restaurant name";

        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("perPage", perPage)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/restaurants/{perPage}")
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
    public void testGetRestaurantTableByRestaurantIdAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/restaurant/Restuarant/tables/{restaurantId}")
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
