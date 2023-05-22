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
import static restaunrantApiTest.Util.getSuperadminToken;

public class SuperAdminReportController {
    @Test
    public void testGetTotalRestaurantReportAPI() throws JsonProcessingException {
      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/total-restaurant")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetTotalEnabledRestaurantReportAPI() throws JsonProcessingException {
        String response =      given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/total-restaurant-enabled")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetTotalDisabledRestaurantReportAPI() throws JsonProcessingException {
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/total-restaurant-disabled")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetTotalCustomersReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/total-customers")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetTotalOrdersReportAPI() throws JsonProcessingException {
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/total-orders")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetMonthlyOrdersReportAPI() throws JsonProcessingException {
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/monthly-orders")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetMonthlyRevenueReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/monthly-revenue")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetMonthlyCustomerReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/monthly-customer")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetMonthlyMenuReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/monthly-menu")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetWeeklyOrdersReportAPI() throws JsonProcessingException {
        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/weekly-orders")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetWeeklyRevenueReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/weekly-revenue")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetWeeklyCustomerReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/weekly-customer")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetWeeklyMenuReportAPI() throws JsonProcessingException {
        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/weekly-menu")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetTodayOrdersReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/today-orders")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }


    @Test
    public void testGetTotalReviewsReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/total-reviews")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetTodayReviewsReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/today-reviews")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetReviewReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/review-report")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetOrderReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/order-report")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetCustomerReportAPI() throws JsonProcessingException {
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .when()
                .get(URL +"/api/superadmin/dashboard-report/customer-report")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
}
