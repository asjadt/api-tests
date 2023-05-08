package restaunrantApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.reqres.PostData;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DailyViewsController {

    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);
    private static final String URL = "https://mughalsignandprint.co.uk/restaurant";

    public class User {
        private int id;
        private String name;
        private String token;

        // getters and setters
    }
    public String  getToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"superman@g.c\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .extract()
                .response()
                .body()
                .asString();

        String token = JsonPath.from(response).getString("token");
        System.out.println("Token: " + token);

        return token;

    }
    @Test
    public void testStoreDailyViews() throws JsonProcessingException {
      String token =  this.getToken();
        given().contentType(ContentType.JSON)
               .header("Authorization", "Bearer " + token)
                .body("{\"view_date\": \"2023-05-08\", \"daily_views\": \"1\"}")
                .when()
                .post(URL + "/api/dailyviews/1")
                .then()
                .statusCode(200);
    }
    @Test
    public void testStoreDailyViewsUnauthorized() {
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"view_date\": \"2023-05-08\", \"daily_views\": \"1\"}")
                .when()
                .post(URL + "/api/dailyviews/1")
                .then()
                .statusCode(401);
    }
    @Test
    public void testCreateDailyViews() throws JsonProcessingException {
        String token =  this.getToken();
        String requestBody = "{\"view_date\": \"2023-05-08\", \"daily_views\": \"5\"}";
        given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/dailyviews/1")
                .then()
                .statusCode(200)
                .body("view_date", equalTo("2023-05-08"))
           ;
    }
}
