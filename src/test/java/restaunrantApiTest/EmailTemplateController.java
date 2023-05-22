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

public class EmailTemplateController {

    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);

    @Test
    public void testCreateEmailTemplate() throws JsonProcessingException {
        String token = getSuperadminToken();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type", "email_verification_mail");
        requestBody.put("template", "html template goes here");
        requestBody.put("is_active", true);

        String response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/email-templates")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);

    }
    @Test
    public void testUpdateEmailTemplate() throws JsonProcessingException {
        String token = getSuperadminToken();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("template", "updated html template goes here");
        requestBody.put("is_active", true);

        String response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(new ObjectMapper().writeValueAsString(requestBody))
                .when()
                .put(URL + "/api/v1.0/email-templates")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testGetEmailTemplates() throws JsonProcessingException {
        String token = getSuperadminToken();
        int perPage = 6;

        String response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .pathParam("perPage", perPage)
                .when()
                .get(URL + "/api/v1.0/email-templates/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testGetEmailTemplateTypes() throws JsonProcessingException {
        String token = getSuperadminToken();

        String response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/v1.0/email-template-types")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);


    }
    @Test
    public void testGetEmailTemplateById() throws JsonProcessingException {
        String token = getSuperadminToken();
        int id = 1; // Specify the ID of the email template you want to retrieve

        String response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/v1.0/email-templates/single/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);


    }

    @Test
    public void testDeleteEmailTemplateByIdAPI() throws JsonProcessingException {
        String templateId = "1"; // Replace with the actual template ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken()) // Replace with the actual access token
                .pathParam("id", templateId)
                .when()
                .delete(URL +"/api/v1.0/email-templates/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }
}
