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

import java.util.*;

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
    @Test
    public void testGetQuestionAPI() throws JsonProcessingException {
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .queryParam("restaurant_id", "123") // Set the restaurant ID as a query parameter (optional)
                .when()
                .get(URL + "/api/review-new/get/questions")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }


    @Test
    public void getQuestionAllUnauthorized() throws JsonProcessingException {
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .queryParam("restaurant_id", "1") // Set the restaurant ID as a query parameter (optional)
                .when()
                .get(URL + "/api/review-new/get/questions-all/customer")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetAllQuestionsAPI() throws JsonProcessingException {
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .queryParam("restaurant_id", "1") // Set the restaurant ID as a query parameter (optional)
                .when()
                .get(URL + "/api/review-new/get/questions-all")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }
    @Test
    public void testGetAllQuestionsReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .queryParam("restaurant_id", "1") // Set the restaurant ID as a query parameter (optional)
                .queryParam("start_date", "2023-06-29") // Set the start date as a query parameter (optional)
                .queryParam("end_date", "2023-06-29") // Set the end date as a query parameter (optional)
                .when()
                .get(URL + "/api/review-new/get/questions-all-report")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
        System.out.println(response);
    }
    @Test
    public void testGetQuestionByIdAPI() throws JsonProcessingException {
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", 1) // Set the question ID as a path parameter
                .when()
                .get(URL + "/api/review-new/get/questions/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetQuestionById2API() throws JsonProcessingException {
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", 1) // Set the question ID as a path parameter
                .when()
                .get(URL + "/api/review-new/get/questions/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteQuestionByIdAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", 1) // Set the question ID as a path parameter
                .when()
                .delete(URL + "/api/review-new/delete/questions/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testStoreTagAPI() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("tag", "How was this?");
        requestBody.put("restaurant_id", 1);

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/review-new/create/tags")
                .then()
                .statusCode(201)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testStoreMultipleTagsAPI() throws JsonProcessingException {
        List<String> tags = Arrays.asList(("tag1" + Math.random()), ("tag2" + Math.random()));
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("tags", tags);

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/review-new/create/tags/multiple/1")
                .then()
                .statusCode(201)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testUpdateTagAPI() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("tag", "How was this?");
        requestBody.put("id", 1);

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/review-new/update/tags")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetTagsAPI() throws JsonProcessingException {
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(URL + "/api/review-new/get/tags")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetTagByIdAPI() throws JsonProcessingException {
        int tagId = 1; // Replace with the actual tag ID

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", tagId)
                .when()
                .get(URL + "/api/review-new/get/tags/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetTagById2API() throws JsonProcessingException {
        int tagId = 1; // Replace with the actual tag ID
        int restaurantId = 1; // Replace with the actual restaurant ID

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", tagId)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL + "/api/review-new/get/tags/{id}/{restaurantId}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteTagByIdAPI() throws JsonProcessingException {
        int tagId = 1; // Replace with the actual tag ID

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", tagId)
                .when()
                .delete(URL + "/api/review-new/delete/tags/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testStoreOwnerQuestionAPI() throws JsonProcessingException {
        int questionId = 1; // Replace with the actual question ID
        String starId = "2"; // Replace with the actual star ID

        Map<String, Object> tag1 = new HashMap<>();
        tag1.put("tag_id", "2");

        Map<String, Object> tag2 = new HashMap<>();
        tag2.put("tag_id", "2");

        Map<String, Object> star = new HashMap<>();
        star.put("star_id", starId);
        star.put("tags", Arrays.asList(tag1, tag2));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("question_id", questionId);
        requestBody.put("stars", Collections.singletonList(star));

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/review-new/owner/create/questions")
                .then()
                .statusCode(201)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testUpdateOwnerQuestionAPI() throws JsonProcessingException {
        int questionId = 1; // Replace with the actual question ID
        String starId = "2"; // Replace with the actual star ID

        Map<String, Object> tag1 = new HashMap<>();
        tag1.put("tag_id", "2");

        Map<String, Object> tag2 = new HashMap<>();
        tag2.put("tag_id", "2");

        Map<String, Object> star = new HashMap<>();
        star.put("star_id", starId);
        star.put("tags", Arrays.asList(tag1, tag2));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("question_id", questionId);
        requestBody.put("stars", Collections.singletonList(star));

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .queryParam("_method", "PATCH")
                .body(requestBody)
                .when()
                .post(URL + "/api/review-new/owner/update/questions")
                .then()
                .statusCode(201)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetQuestionAllReportGuestAPI() throws JsonProcessingException {
        String restaurantId = "1"; // Replace with the actual restaurant ID
        String startDate = "2023-06-29"; // Replace with the actual start date
        String endDate = "2023-06-29"; // Replace with the actual end date

    String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .queryParam("restaurant_id", restaurantId)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .when()
                .get(URL + "/api/review-new/get/questions-all-report/guest")
                .then()
            .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }
    @Test
    public void testGetQuestionAllReportUnauthorizedAPI() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("restaurant_id", restaurantId)
                .when()
                .get(URL + "/api/review-new/get/questions-all-report/unauthorized")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetQuestionAllReportGuestUnauthorizedAPI() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("restaurant_id", restaurantId)
                .when()
                .get(URL + "/api/review-new/get/questions-all-report/guest/unauthorized")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetQuestionAllReportGuestQuantumAPI() throws JsonProcessingException {
        String restaurantId = "1"; // Replace with the actual restaurant ID
        String quantum = "daily"; // Replace with the desired quantum value

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
             .header("Authorization", "Bearer " + getToken()) // Replace with the actual access token
                .queryParam("restaurant_id", restaurantId)
                .queryParam("quantum", quantum)
                .when()
                .get(URL + "/api/review-new/get/questions-all-report/guest/quantum")
                .then()

                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
        System.out.println(response);
    }
    @Test
    public void testGetQuestionAllReportQuantumAPI() throws JsonProcessingException {
        String restaurantId = "1"; // Replace with the actual restaurant ID
        String quantum = "daily"; // Replace with the desired quantum value
        String period = "30"; // Replace with the desired period value

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Replace with the actual access token
                .queryParam("restaurant_id", restaurantId)
                .queryParam("quantum", quantum)
                .queryParam("period", period)
                .when()
                .get("/api/review-new/get/questions-all-report/quantum")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
}
