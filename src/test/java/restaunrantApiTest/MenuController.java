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
import static restaunrantApiTest.Util.getToken;

public class MenuController {
    @Test
    public void testStoreMenu() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String path = "/api/menu/" + restaurantId;
        String requestBody = "{\n" +
                "  \"name\": \"test@g.c " + Math.random() + "\",\n" +
                "  \"description\": \"12345678\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    @Test
    public void testCheckMenu() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String path = "/api/menu/check/" + restaurantId;
        String requestBody = "{\n" +
                "  \"name\": \"test@g.c\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }


    @Test
    public void testUpdateMenu() {
        String menuId = "1"; // Replace with the actual menu ID

        String path = "/api/menu/update/" + menuId;
        String requestBody = "{\n" +
                "  \"name\": \"test@g.c\",\n" +
                "  \"description\": \"12345678\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    @Test
    public void testGetMenuById() {
        String menuId = "1"; // Replace with the actual menu ID

        String path = "/api/menu/" + menuId;

        Response response = given()
                .when()
                .get(URL + path)
                .then()

                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testGetMenuById2() {
        String menuId = "1"; // Replace with the actual menu ID
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String path = "/api/menu/by-restaurant/" + menuId + "/" + restaurantId;

        Response response = given()
                .when()
                .get(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    @Test
    public void testGetMenuByRestaurantId() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String path = "/api/menu/AllbuId/" + restaurantId;

        Response response = given()
                .when()
                .get(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testGetMenuByRestaurantIdWithPagination() {
        String restaurantId = "1"; // Replace with the actual restaurant ID
        String perPage = "6"; // Replace with the desired number of items per page

        String path = "/api/menu/AllbuId/" + restaurantId + "/" + perPage;

        Response response = given()
                .when()
                .get(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }

    @Test
    public void testStoreMultipleMenu() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String path = "/api/menu/multiple/" + restaurantId;

        String requestBody = "{\n" +
                "  \"menu\": [\n" +
                "    {\n" +
                "      \"name\": \"hggggg\",\n" +
                "      \"description\": \"555\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"hggggg\",\n" +
                "      \"description\": \"555\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + path)
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testUpdateMultipleMenu() {
        String restaurantId = "1"; // Replace with the actual restaurant ID

        String path = "/api/menu/Edit/multiple";
        String requestBody = "{\n" +
                "  \"restaurant_id\": \"" + restaurantId + "\",\n" +
                "  \"menu\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"name\": \"hggggg\",\n" +
                "      \"description\": 555\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"name\": \"hggggg\",\n" +
                "      \"description\": 555\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testUpdateMenu2() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"test@g.c\",\n" +
                "  \"description\": \"12345678\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(URL + "/api/menu/Updatemenu")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testDeleteMenuAPI() throws JsonProcessingException {
        String menuId = "1"; // Replace with the actual menu ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("menuId", menuId)
                .when()
                .delete(URL +"/api/menu/{menuId}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }
}
