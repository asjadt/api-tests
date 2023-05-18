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
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getToken;

public class DashboardWidgetController {

    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);




    @Test
    public void testCreateDashboardWidget() throws JsonProcessingException {
        String token = getToken();
        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"name\": \"test\", \"description\": \"12345678\", \"user_type\": \"admin\"}")
                .when()
                .post(URL +"/api/superadmin/dashboard-widget/create")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



    }


    @Test
    public void testUpdateDashboardWidget() throws JsonProcessingException {
        String token = getToken();
        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"id\": \"1\", \"name\": \"test\", \"description\": \"12345678\", \"user_type\": \"admin\"}")
                .when()
                .put(URL  + "/api/superadmin/dashboard-widget/update")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();


    }

    @Test
    public void testGetDashboardWidget() throws JsonProcessingException {
        String token = getToken();
        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL +"/api/superadmin/dashboard-widget/get")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



    }

    @Test
    public void testGetDashboardWidgetById() throws JsonProcessingException {
        String token = getToken();
        String id = "1"; // The ID of the widget you want to retrieve
        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .when()
                .get(URL +"/api/superadmin/dashboard-widget/get/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();



    }

    @Test
    public void testDeleteWidgetByIdAPI() throws JsonProcessingException {
        String widgetId = "1"; // Replace with the actual widget ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Replace with the actual access token
                .pathParam("id", widgetId)
                .when()
                .delete(URL +"/api/superadmin/dashboard-widget/delete/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }
    @Test
    public void testDeleteUserDashboardByIdAPI() throws JsonProcessingException {
        String dashboardId = "1"; // Replace with the actual dashboard ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Replace with the actual access token
                .pathParam("id", dashboardId)
                .when()
                .delete(URL+ "/api/user-dashboard/delete/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }



}
