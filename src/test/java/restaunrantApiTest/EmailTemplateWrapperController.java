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
import org.json.simple.JSONObject;
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

public class EmailTemplateWrapperController {
    @Test
    public void testUpdateEmailTemplateWrapper() throws JsonProcessingException {
        String token = getSuperadminToken();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1); // Specify the ID of the email template wrapper you want to update
        requestBody.put("name", "email v1");
        requestBody.put("template", "html template goes here");
        requestBody.put("is_active", 1);

        String response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/email-template-wrappers")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testGetEmailTemplateWrappers() throws JsonProcessingException {
        String token = getSuperadminToken();
        int perPage = 6; // Specify the number of results per page

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search_key", "example"); // Add any additional query parameters if required

        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .queryParams(queryParams)
                .when()
                .get(URL + "/api/v1.0/email-template-wrappers/" + perPage)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());


    }
    @Test
    public void testGetEmailTemplateWrapperById() throws JsonProcessingException {
        String token = getSuperadminToken();
        int id = 6; // Specify the ID of the email template wrapper

        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/v1.0/email-template-wrappers/single/" + id)
                .then()
                .extract()
                .response();

        System.out.println(response.getBody().asString());


    }




}
