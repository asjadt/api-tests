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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getSuperadminToken;

public class NotificationController {
    @Test
    public void testStoreNotification() throws JsonProcessingException {
        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "hello");
        requestBody.put("message", "hello");
        requestBody.put("reciever_id", "1");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/notification")
                .then()
                .statusCode(200)

                .extract()
                .response();
    }

    @Test
    public void testUpdateNotification() throws JsonProcessingException {
        // Prepare request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", "test@g.c");
        requestBody.put("status", "test");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .patch(URL + "/api/notification/{notificationId}", 1) // Replace 1 with the actual notification ID
                .then()
                .statusCode(200)

                .extract()
                .response();
    }
    @Test
    public void testGetNotification() throws JsonProcessingException {
        given()
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL + "/api/notification")
                .then()
                .statusCode(200)

                .extract()
                .response();
    }
    @Test
    public void testDeleteNotificationAPI() throws JsonProcessingException {
        String notificationId = "1"; // Replace with the actual notification ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .pathParam("notificationId", notificationId)
                .when()
                .delete(URL +"/api/notification/{notificationId}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }


}
