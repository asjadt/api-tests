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

public class DishController {


    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);



    @Test
    public void testStoreDish() throws JsonProcessingException {
        String token = getToken();
        String menuId = "1"; // The ID of the menu where you want to store the dish
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("menuId", menuId)
                .body("{\"name\": \"test\", \"price\": \"12345678\", \"restaurant_id\": \"1\", \"take_away\": \"1\", \"delivery\": \"0\", \"description\": \"description\", \"ingredients\": \"ingredients\", \"calories\": \"calories\"}")
                .when()
                .post(URL +"/api/dishes/{menuId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
               ;

        System.out.println(response.body()
                .asString());
    }
    @Test
    public void testGetAllDishes() {
        String restaurantId = "1"; // The ID of the restaurant for which you want to get all dishes

        Response response = given()
                .contentType("application/json")
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/dishes/All/dishes/{restaurantId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
              ;

        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testGetAllDishesWithPagination() {
        String restaurantId = "1"; // The ID of the restaurant for which you want to get all dishes
        String perPage = "10"; // The number of items per page

        Response response = given()
                .contentType("application/json")
                .pathParam("restaurantId", restaurantId)
                .pathParam("perPage", perPage)
                .when()
                .get(URL +"/api/dishes/All/dishes/{restaurantId}/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
               ;

        System.out.println(response.body()
                .asString());

    }

    @Test
    public void testGetDishByMenuId() {
        String menuId = "1"; // The ID of the menu to retrieve dishes for

        Response response = given()
                .contentType("application/json")
                .pathParam("menuId", menuId)
                .when()
                .get(URL + "/api/dishes/{menuId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
              ;

        System.out.println(response.body()
                .asString());

    }

    @Test
    public void testGetDishByMenuIdAndRestaurantId() {
        String menuId = "1"; // The ID of the menu to retrieve dishes for
        String restaurantId = "1"; // The ID of the restaurant to filter dishes by

        Response response = given()
                .contentType("application/json")
                .pathParam("menuId", menuId)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/dishes/by-restaurant/{menuId}/{restaurantId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                ;

        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testGetDealDishByMenuIdAndRestaurantId() {
        String menuId = "1"; // The ID of the menu to retrieve deal dishes for
        String restaurantId = "1"; // The ID of the restaurant to filter deal dishes by

        Response response = given()
                .contentType("application/json")
                .pathParam("menuId", menuId)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/deal-dishes/by-restaurant/{menuId}/{restaurantId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
            ;

        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testGetDishByDealId() {
        String dealId = "1"; // The ID of the deal to retrieve dishes for

        Response response = given()
                .contentType("application/json")
                .pathParam("dealId", dealId)
                .when()
                .get(URL + "/api/dishes/getdealsdishes/{dealId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
              ;

        System.out.println(response.body()
                .asString());

    }

    @Test
    public void testGetDishByDealId2() {
        String dealId = "1"; // The ID of the deal to retrieve dishes for
        String restaurantId = "1"; // The ID of the restaurant

        Response response = given()
                .contentType("application/json")
                .pathParam("dealId", dealId)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/dishes/getdealsdishes/{dealId}/{restaurantId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
               ;

        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testGetDishById() {
        String dishId = "1"; // The ID of the dish to retrieve

        Response response = given()
                .contentType("application/json")
                .pathParam("dishId", dishId)
                .when()
                .get(URL + "/api/dishes/by-dishid/{dishId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
             ;

        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testGetDishById2() {
        String dishId = "1"; // The ID of the dish to retrieve
        String restaurantId = "1"; // The ID of the restaurant

        Response response = given()
                .contentType("application/json")
                .pathParam("dishId", dishId)
                .pathParam("restaurantId", restaurantId)
                .when()
                .get(URL +"/api/dishes/by-dishid/{dishId}/{restaurantId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
               ;

        System.out.println(response.body()
                .asString());

    }

    @Test
    public void testGetAllDishesWithDeals() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get(URL + "/api/dishes/getusermenu/dealsdishes")
                .then()
                .statusCode(200)
                .extract()
                .response()
                ;

        System.out.println(response.body()
                .asString());
    }

    @Test
    public void testStoreMultipleDish() {
        // Create an example request body
        List<Map<String, Object>> dishes = new ArrayList<>();

        Map<String, Object> dish1 = new HashMap<>();
        dish1.put("name", "hggggg");
        dish1.put("price", 555);
        dish1.put("take_away", 1);
        dish1.put("delivery", 0);
        dish1.put("restaurant_id", 56);
        dish1.put("description", "fffffffffff");
        dish1.put("ingredients", "hgggxrth srthdhh thgg");
        dish1.put("calories", "cfgt trfgh s rth");

        Map<String, Object> dish2 = new HashMap<>();
        dish2.put("name", "hggggg");
        dish2.put("price", 555);
        dish2.put("take_away", 1);
        dish2.put("delivery", 0);
        dish2.put("restaurant_id", 56);
        dish2.put("description", "fffffffffff");
        dish2.put("ingredients", "hgggxrth srthdhh thgg");
        dish2.put("calories", "cfgt trfgh s rth");

        dishes.add(dish1);
        dishes.add(dish2);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("dishes", dishes);

        // Send the POST request
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(URL+"/api/dishes/multiple/1")
                .then()
                .statusCode(201)
                .extract()
                .response();
        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testStoreMultipleDealDish() {
        // Create an example request body
        Map<String, Object> requestBody = new HashMap<>();

        List<Map<String, Object>> dishes = new ArrayList<>();

        Map<String, Object> dish1 = new HashMap<>();
        dish1.put("name", "hggggg");
        dish1.put("price", 555);
        dish1.put("take_away", 1);
        dish1.put("delivery", 0);
        dish1.put("restaurant_id", 56);
        dish1.put("description", "fffffffffff");
        dish1.put("ingredients", "hgggxrth srthdhh thgg");
        dish1.put("calories", "cfgt trfgh s rth");

        List<Map<String, Object>> selected1 = new ArrayList<>();
        Map<String, Object> selectedDish1 = new HashMap<>();
        selectedDish1.put("dish_id", "1");
        selected1.add(selectedDish1);
        dish1.put("selected", selected1);

        Map<String, Object> dish2 = new HashMap<>();
        dish2.put("name", "hggggg");
        dish2.put("price", 555);
        dish2.put("take_away", 1);
        dish2.put("delivery", 0);
        dish2.put("restaurant_id", 56);
        dish2.put("description", "fffffffffff");
        dish2.put("ingredients", "hgggxrth srthdhh thgg");
        dish2.put("calories", "cfgt trfgh s rth");

        List<Map<String, Object>> selected2 = new ArrayList<>();
        Map<String, Object> selectedDish2 = new HashMap<>();
        selectedDish2.put("dish_id", "1");
        selected2.add(selectedDish2);
        dish2.put("selected", selected2);

        dishes.add(dish1);
        dishes.add(dish2);

        requestBody.put("dishes", dishes);

        // Send the POST request
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(URL +"/api/dishes/multiple/deal/1")
                .then()
                .statusCode(201)
                .extract()
                .response();
        System.out.println(response.body()
                .asString());
    }

    @Test
    public void testUpdateMultipleDealDish() {
        // Create an example request body
        Map<String, Object> requestBody = new HashMap<>();

        List<Map<String, Object>> dishes = new ArrayList<>();

        Map<String, Object> dish1 = new HashMap<>();
        dish1.put("id", 1);
        dish1.put("name", "hggggg");
        dish1.put("price", 555);
        dish1.put("take_away", 1);
        dish1.put("delivery", 0);
        dish1.put("restaurant_id", 56);
        dish1.put("description", "fffffffffff");
        dish1.put("ingredients", "hgggxrth srthdhh thgg");
        dish1.put("calories", "cfgt trfgh s rth");

        List<Map<String, Object>> selected1 = new ArrayList<>();
        Map<String, Object> selectedDish1 = new HashMap<>();
        selectedDish1.put("dish_id", "1");
        selected1.add(selectedDish1);
        dish1.put("selected", selected1);

        Map<String, Object> dish2 = new HashMap<>();
        dish2.put("id", 2);
        dish2.put("name", "hggggg");
        dish2.put("price", 555);
        dish2.put("take_away", 1);
        dish2.put("delivery", 0);
        dish2.put("restaurant_id", 56);
        dish2.put("description", "fffffffffff");
        dish2.put("ingredients", "hgggxrth srthdhh thgg");
        dish2.put("calories", "cfgt trfgh s rth");

        List<Map<String, Object>> selected2 = new ArrayList<>();
        Map<String, Object> selectedDish2 = new HashMap<>();
        selectedDish2.put("dish_id", "1");
        selected2.add(selectedDish2);
        dish2.put("selected", selected2);

        dishes.add(dish1);
        dishes.add(dish2);

        requestBody.put("dishes", dishes);

        // Send the PATCH request
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch(URL + "/api/dishes/multiple/deal/1")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println(response.body()
                .asString());
    }
    @Test
    public void testUpdateMultipleDish() {
        // Create an example request body
        Map<String, Object> requestBody = new HashMap<>();

        List<Map<String, Object>> dishes = new ArrayList<>();

        Map<String, Object> dish1 = new HashMap<>();
        dish1.put("id", 1);
        dish1.put("name", "aaaaa");
        dish1.put("price", 555);
        dish1.put("take_away", 1);
        dish1.put("delivery", 0);
        dish1.put("description", "fffffffffff");
        dish1.put("ingredients", "hgggxrth srthdhh thgg");
        dish1.put("calories", "cfgt trfgh s rth");

        dishes.add(dish1);

        Map<String, Object> dish2 = new HashMap<>();
        dish2.put("id", 2);
        dish2.put("name", "aaaaa");
        dish2.put("price", 555);
        dish2.put("take_away", 1);
        dish2.put("delivery", 0);
        dish2.put("description", "fffffffffff");
        dish2.put("ingredients", "hgggxrth srthdhh thgg");
        dish2.put("calories", "cfgt trfgh s rth");

        dishes.add(dish2);

        requestBody.put("dishes", dishes);

        // Send the PATCH request
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch(URL+"/api/dishes/Edit/multiple")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.body()
                .asString());
    }
    @Test
    public void testUpdateDish2() {
        // Create an example request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "aaaa");
        requestBody.put("price", "10");
        requestBody.put("description", "Rifat");
        requestBody.put("id", 1);

        // Send the PATCH request
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch(URL +"/api/dishes/Updatedish")
                .then()
                .statusCode(200)
                .extract()
                .response()
                ;

        System.out.println(response.body()
                .asString());

    }
    @Test
    public void testDeleteDishAPI() throws JsonProcessingException {
        String dishId = "1"; // Replace with the actual dish ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Replace with the actual access token
                .pathParam("dishId", dishId)
                .when()
                .delete(URL +"/api/dishes/{dishId}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }

}
