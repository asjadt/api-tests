package garageApiTest;



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

import static garageApiTest.Util.URL;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;


public class AuthController {
    @Test
    public void testLoginAPI() {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", "admin@gmail.com");
        requestBody.put("password", "12345678");

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .body(requestBody)
                .when()
                .post(URL +"/api/v1.0/login")
                .then()
                .statusCode(200)
             .extract()
             .response()
             .asString();

        System.out.println(response);
    }

}
