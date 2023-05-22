package restaunrantApiTest;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

import static restaunrantApiTest.Util.URL;

import data.reqres.PostData;
import in.reqres.TestPostRequests;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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


public class AllInOne {


    private DailyViewsController dailyViewsController; // Injected controller instance
    private OwnerController ownerController;

    private RestaurantController restaurantController;


    private ObjectMapper objectMapper;
    @BeforeClass
    public void setup() {
        dailyViewsController = new DailyViewsController();
        ownerController = new OwnerController();
        restaurantController = new RestaurantController();
        objectMapper = new ObjectMapper();
    }


    private RestaurantInfo getRestaurantInfo() throws JsonProcessingException {
        String restaurant = ownerController.testCreateUserWithRestaurantAPIGetString();
        JsonNode jsonNodeOfRestaurant = objectMapper.readTree(restaurant);
        Integer restaurantId = jsonNodeOfRestaurant.get("data").get("restaurant").get("id").asInt();
        String restaurantOwnerToken = jsonNodeOfRestaurant.get("token").textValue();
        return new RestaurantInfo(restaurantId, restaurantOwnerToken);
    }

    @Test
    public void DailyView() throws JsonProcessingException {
        RestaurantInfo restaurantInfo = getRestaurantInfo();
        Integer restaurantId = restaurantInfo.getRestaurantId();
        String restaurantOwnerToken = restaurantInfo.getRestaurantOwnerToken();






      String dailyView =  dailyViewsController.testStoreDailyViewsGetString(restaurantId,restaurantOwnerToken);
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(dailyView);
        Integer dailyViewId = jsonNodeOfDailyView.get("id").asInt();
        System.out.println("daily view ID: " + dailyViewId);


        dailyViewsController.testUpdateDailyViews(restaurantId,restaurantOwnerToken);








        restaurantController.testDeleteRestaurantAPI(restaurantId,restaurantOwnerToken);
    }





    @Test
    public void loginWithValidCredentialsShouldReturn200() {
         String success =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\": \"superman@g.c\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(" response: " + success);
    }
}
