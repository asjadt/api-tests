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
import static restaunrantApiTest.Util.getToken;


public class VariationController {
    @Test
    public void testStoreVariationTypeAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "test");
        requestBody.put("description", "test");
        requestBody.put("restaurant_id", "1");

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/variation/variation_type")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);

    }

    @Test
    public void testDeleteVariationTypeAPI() throws JsonProcessingException {
        // Set the ID of the variation type to delete
        int variationTypeId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .delete(URL + "/api/variation/variation_type/" + variationTypeId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testStoreMultipleVariationTypeAPI() throws JsonProcessingException {
        // Set the restaurant ID
        int restaurantId = 1;

        // Prepare the request body
        List<Map<String, String>> variationTypes = new ArrayList<>();
        Map<String, String> variationType1 = new HashMap<>();
        variationType1.put("name", "hggggg");
        variationType1.put("description", "fffffffffff");
        variationTypes.add(variationType1);

        Map<String, String> variationType2 = new HashMap<>();
        variationType2.put("name", "hggggg");
        variationType2.put("description", "fffffffffff");
        variationTypes.add(variationType2);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("VarationType", variationTypes);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/variation/variation_type/multiple/" + restaurantId)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);


    }

    @Test
    public void testUpdateMultipleVariationTypeAPI() throws JsonProcessingException {
        // Prepare the request body
        List<Map<String, String>> variationTypes = new ArrayList<>();

        Map<String, String> variationType1 = new HashMap<>();
        variationType1.put("varation_type_id", "3");
        variationType1.put("name", "Multiple");
        variationType1.put("description", "Multiple");
        variationTypes.add(variationType1);

        Map<String, String> variationType2 = new HashMap<>();
        variationType2.put("varation_type_id", "2");
        variationType2.put("name", "Multiple");
        variationType2.put("description", "Multiple");
        variationTypes.add(variationType2);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("VarationType", variationTypes);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .patch(URL + "/api/variation/variation_type/multiple")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);


    }

    @Test
    public void testUpdateMultipleVariationAPI() throws JsonProcessingException {
        // Prepare the request body
        List<Map<String, String>> variations = new ArrayList<>();

        Map<String, String> variation1 = new HashMap<>();
        variation1.put("id", "3");
        variation1.put("name", "Multiple");
        variation1.put("description", "Multiple");
        variation1.put("price", "10");
        variations.add(variation1);

        Map<String, String> variation2 = new HashMap<>();
        variation2.put("id", "2");
        variation2.put("name", "Multiple");
        variation2.put("description", "Multiple");
        variation2.put("price", "10");
        variations.add(variation2);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("variations", variations);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .patch(URL + "/api/variation/variation/multiple")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testUpdateVariationTypeAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "test");
        requestBody.put("description", "test");
        requestBody.put("VTypeId", "1");

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .patch(URL + "/api/variation/variationtype")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testStoreVariationAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "test");
        requestBody.put("description", "test");
        requestBody.put("type_id", 1);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/variation")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testStoreMultipleVariationAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("restaurant_id", "1");

        List<Map<String, Object>> variations = new ArrayList<>();

        Map<String, Object> variation1 = new HashMap<>();
        variation1.put("name", "ssssss");
        variation1.put("description", "Multiple");
        variation1.put("type_id", 1);
        variation1.put("price", 90);
        variations.add(variation1);

        Map<String, Object> variation2 = new HashMap<>();
        variation2.put("name", "ssssss");
        variation2.put("description", "Multiple");
        variation2.put("type_id", 1);
        variation2.put("price", 90);
        variations.add(variation2);

        requestBody.put("varation", variations);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/variation/multiple/varations")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testUpdateVariationAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "test");
        requestBody.put("description", "test");
        requestBody.put("Vid", 1);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .patch(URL + "/api/variation")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testStoreDishVariationAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("minimum_variation_required", 1);
        requestBody.put("no_of_varation_allowed", 1);
        requestBody.put("type_id", "1");
        requestBody.put("dish_id", 1);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/variation/dish_variation")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }
    private static final int DISH_ID = 1;
    @Test
    public void testStoreMultipleDishVariationAPI() throws JsonProcessingException {

        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String, Object>> variations = new ArrayList<>();

        Map<String, Object> variation1 = new HashMap<>();
        variation1.put("minimum_variation_required", 5);
        variation1.put("no_of_varation_allowed", 5);
        variation1.put("type_id", 1);

        Map<String, Object> variation2 = new HashMap<>();
        variation2.put("minimum_variation_required", 5);
        variation2.put("no_of_varation_allowed", 5);
        variation2.put("type_id", 2);

        variations.add(variation1);
        variations.add(variation2);

        requestBody.put("varation", variations);

        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBodyJson)
                .when()
                .post(URL + "/api/variation/multiple/dish_variation/" + DISH_ID)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }



    @Test
    public void testGetAllDishVariationAPI() throws JsonProcessingException {
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .get(URL + "/api/variation/dish_variation/" + DISH_ID)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }
    @Test
    public void testUpdateDishVariationAPI() throws JsonProcessingException {
        int dishId = 1;
        int typeId = 2;
        int minimumVariationRequired = 5;
        int noOfVariationAllowed = 10;

        // Create the request body JSON
        String requestBody = String.format("{ \"dish_id\": %d, \"type_id\": %d, \"minimum_variation_required\": %d, \"no_of_varation_allowed\": %d }",
                dishId, typeId, minimumVariationRequired, noOfVariationAllowed);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBody)
                .when()
                .patch(URL + "/api/variation/dish_variation")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testUpdateMultipleDishVariationAPI() throws JsonProcessingException {
        int dishId = 1;

        // Create the request body JSON
        String requestBody = "{ \"DishVariations\": [ { \"minimum_variation_required\": 10, \"no_of_varation_allowed\": 10, \"type_id\": 10, \"dish_id\": 1 } ] }";

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .body(requestBody)
                .when()
                .patch(URL + "/api/variation/dish_variation/multiple/" + dishId)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testGetAllVariationWithDishAPI() throws JsonProcessingException {
        int restaurantId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .get(URL + "/api/variation/" + restaurantId)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetSingleVariationTypeAPI() throws JsonProcessingException {
        int id = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .get(URL + "/api/variation-type/" + id)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testGetAllVariationWithDishNewAPI() throws JsonProcessingException {
        int restaurantId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .get(URL + "/api/variation2/" + restaurantId)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }
    @Test
    public void testGetAllVariationByTypeIdAPI() throws JsonProcessingException {
        int typeId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .get(URL + "/api/variation/type/count/" + typeId)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetAllVariationByRestaurantIdAPI() throws JsonProcessingException {
        int restaurantId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .get(URL + "/api/variation/by-restaurant-id/" + restaurantId)
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteDishVariationAPI() throws JsonProcessingException {
        int typeId = 1;
        int dishId = 1;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken()) // Set the authorization header
                .when()
                .delete(URL + "/api/variation/unlink/" + typeId + "/" + dishId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }


}
