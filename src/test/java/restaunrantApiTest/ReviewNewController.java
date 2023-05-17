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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getToken;

public class ReviewNewController {
    @Test
    public void testGetReviewValuesAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;
        int rate = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("rate", rate)
                .when()
                .get(URL + "/api/review-new/getvalues/{restaurantId}/{rate}")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }
    @Test
    public void testGetAverageAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;
        String startDate = "2019-06-29";
        String endDate = "2026-06-29";

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("start", startDate)
                .pathParam("end", endDate)
                .when()
                .get(URL + "/api/review-new/getavg/review/{restaurantId}/{start}/{end}")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }

    @Test
    public void testFilterReviewAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;
        int rate = 1;
        String startDate = "2019-06-29";
        String endDate = "2026-06-29";

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("rate", rate)
                .pathParam("start", startDate)
                .pathParam("end", endDate)
                .when()
                .get(URL + "/api/review-new/getreview/{restaurantId}/{rate}/{start}/{end}")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }


    @Test
    public void testGetReviewByRestaurantIdAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/review-new/getreviewAll/{restaurantId}")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }

    @Test
    public void testGetCustomerReviewAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;
        String startDate = "2019-06-29";
        String endDate = "2026-06-29";

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .pathParam("start", startDate)
                .pathParam("end", endDate)
                .when()
                .get(URL + "/api/review-new/getcustomerreview/{restaurantId}/{start}/{end}")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }

    @Test
    public void testStoreReviewAPI() throws JsonProcessingException {
        String token = getToken();
        int restaurantId = 1;

        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("description", "test");
        requestBody.put("rate", "2.5");
        requestBody.put("comment", "not good");
        List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> value1 = new HashMap<>();
        value1.put("question_id", 1);
        value1.put("tag_id", 2);
        value1.put("star_id", 1);
        Map<String, Object> value2 = new HashMap<>();
        value2.put("question_id", 2);
        value2.put("tag_id", 1);
        value2.put("star_id", 4);
        values.add(value1);
        values.add(value2);
        requestBody.put("values", values);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("restaurantId", restaurantId)
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/review-new/{restaurantId}")
                .then()
                .statusCode(201)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }
    @Test
    public void testStoreReviewByGuestAPI() throws JsonProcessingException {
        int restaurantId = 1;

        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("guest_full_name", "Rifat");
        requestBody.put("guest_phone", "0177");
        requestBody.put("description", "test");
        requestBody.put("rate", "2.5");
        requestBody.put("comment", "not good");
        List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> value1 = new HashMap<>();
        value1.put("question_id", 1);
        value1.put("tag_id", 2);
        value1.put("star_id", 1);
        Map<String, Object> value2 = new HashMap<>();
        value2.put("question_id", 2);
        value2.put("tag_id", 1);
        value2.put("star_id", 4);
        values.add(value1);
        values.add(value2);
        requestBody.put("values", values);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("restaurantId", restaurantId)
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/review-new-guest/{restaurantId}")
                .then()
                .statusCode(201)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }

    @Test
    public void testStoreQuestionAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("question", "How was this?");
        requestBody.put("restaurant_id", 1);
        requestBody.put("is_active", true);
        requestBody.put("type", "star");

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/review-new/create/questions")
                .then()
                .statusCode(201)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }

    @Test
    public void testUpdateQuestionAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("question", "Was it good?");
        requestBody.put("type", "star");
        requestBody.put("is_active", true);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .put(URL + "/api/review-new/update/questions")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }


    @Test
    public void testUpdateQuestionActiveStateAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("is_active", true);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .put(URL + "/api/review-new/update/active_state/questions")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        // Additional assertions for the response data can be added here
    }





}
