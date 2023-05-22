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


public class ForgotPasswordController {
    @Test
    public void testStoreForgetPassword() {
        String email = "rifat@gmail.com"; // Specify the email for forget password

        String requestBody = "{ \"email\": \"" + email + "\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + "/api/forgetpassword")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testChangePassword() throws JsonProcessingException {
        String token = getSuperadminToken(); // Replace with the actual token

        String requestBody = "{ \"password\": \"12345678\", \"cpassword\": \"12345678\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL + "/api/auth/changepassword")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testChangePasswordBySuperAdmin() throws JsonProcessingException {
        String token = getSuperadminToken(); // Replace with the actual token

        String requestBody = "{ \"password\": \"12345678\", \"user_id\": 1 }"; // Replace user_id with the actual user ID

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL + "/api/superadmin/auth/changepassword")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
    @Test
    public void testChangeEmailBySuperAdmin() throws JsonProcessingException {
        String token = getSuperadminToken(); // Replace with the actual token

        String requestBody = "{ \"email\": \"rifat@gmail.com\", \"user_id\": 1 }"; // Replace user_id with the actual user ID

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL + "/api/superadmin/auth/change-email")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.getBody().asString());
    }
}
