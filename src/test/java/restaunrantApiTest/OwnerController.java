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

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getSuperadminToken;


public class OwnerController {

    @Test
    public void testGetRoleAPI() throws JsonProcessingException {
        String token = getSuperadminToken();

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/owner/role/getrole")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testCreateUser2API() throws JsonProcessingException {
        String token = getSuperadminToken();
        String requestBody = "{\"email\": \"test" + Math.random() + "@gmail.com\", \"password\": \"12345678\", \"first_Name\": \"Rifat\", \"phone\": \"Rifat\", \"type\": \"customer\"}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/owner/user/registration")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testCreateUserWithRestaurantAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        String requestBody = "{\"email\": \"rifat" + Math.random()  +"@gmail.com\", \"password\": \"12345678\", \"first_Name\": \"Rifat\", \"last_Name\": \"Rifat\", \"phone\": \"Rifat\", \"restaurant_name\": \"restaurant_name\", \"restaurant_address\": \"restaurant_address\", \"restaurant_postcode\": \"restaurant_postcode\", \"restaurant_enable_question\": \"0\", \"restaurant_EmailAddress\": \"0\", \"restaurant_GoogleMapApi\": \"0\", \"restaurant_totalTables\": \"0\", \"restaurant_homeText\": \"0\", \"restaurant_AdditionalInformation\": \"0\", \"restaurant_Webpage\": \"0\", \"restaurant_PhoneNumber\": \"0\", \"restaurant_About\": \"0\", \"restaurant_Layout\": \"0\", \"is_eat_in\": \"0\", \"is_delivery\": \"0\", \"is_take_away\": \"0\", \"is_customer_order\": \"0\", \"review_type\": \"emoji\", \"Is_guest_user\": false, \"is_review_silder\": false, \"review_only\": true}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)

                .body(requestBody)
                .when()
                .post(URL + "/api/owner/user/with/restaurant")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
    }

    public    String testCreateUserWithRestaurantAPIGetString() throws JsonProcessingException {

        String requestBody = "{\"email\": \"rifat" + Math.random() + Math.random() + Math.random()  +"@gmail.com\", \"password\": \"12345678\", \"first_Name\": \"Rifat\", \"last_Name\": \"Rifat\", \"phone\": \"Rifat\", \"restaurant_name\": \"restaurant_name\", \"restaurant_address\": \"restaurant_address\", \"restaurant_postcode\": \"restaurant_postcode\", \"restaurant_enable_question\": \"0\", \"restaurant_EmailAddress\": \"0\", \"restaurant_GoogleMapApi\": \"0\", \"restaurant_totalTables\": \"0\", \"restaurant_homeText\": \"0\", \"restaurant_AdditionalInformation\": \"0\", \"restaurant_Webpage\": \"0\", \"restaurant_PhoneNumber\": \"0\", \"restaurant_About\": \"0\", \"restaurant_Layout\": \"0\", \"is_eat_in\": \"0\", \"is_delivery\": \"0\", \"is_take_away\": \"0\", \"is_customer_order\": \"0\", \"review_type\": \"emoji\", \"Is_guest_user\": false, \"is_review_silder\": false, \"review_only\": true}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)

                .body(requestBody)
                .when()
                .post(URL + "/api/owner/user/with/restaurant")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);

        return response;

    }
    @Test
    public void testCheckEmailAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        String requestBody = "{\"email\": \"test@g.c\"}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/owner/user/check/email")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testCreateGuestUserAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        String requestBody = "{\"email\": \"test" + Math.random() + "@g.c\", \"first_Name\": \"Rifat\", \"phone\": \"12345678\"}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/owner/guestuserregister")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testCreateStaffUserAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        String restaurantId = "1";
        String requestBody = "{\"email\": \"test" + Math.random() +  "@g.c\", \"first_Name\": \"Rifat\", \"phone\": \"12345678\", \"password\": \"Rifat\"}";

        String response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(URL + "/api/owner/staffregister/" + restaurantId)
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUpdatePinAPI() {
        String ownerId = "1";
        String requestBody = "{\"pin\": \"1234\"}";

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(URL + "/api/owner/pin/" + ownerId)
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testGetOwnerByIdAPI() {
        String ownerId = "1";

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(URL + "/api/owner/" + ownerId)
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetOwnerNotHaveRestaurentAPI() {
      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(URL + "/api/owner/getAllowner/withourrestaurant")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);

    }
    @Test
    public void testGetOwnerByPhoneNumberAPI() {
        String phoneNumber = "1234567890";

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(URL + "/api/owner/loaduser/bynumber/" + phoneNumber)
                .then()
                .statusCode(422)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testUpdateUserAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        String requestBody = "{\"id\": \"1\", \"first_Name\": \"John\", \"last_Name\": \"Doe\", \"phone\": \"1234567890\", \"Address\": \"123 Main St\", \"post_code\": \"12345\", \"password\": \"newPassword\", \"old_password\": \"oldPassword\"}";

    String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL + "/api/owner/updateuser")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testUpdateUserByUserAPI() throws JsonProcessingException {
        String token = getSuperadminToken();
        String requestBody = "{\"first_Name\": \"John\", \"last_Name\": \"Doe\", \"phone\": \"1234567890\", \"Address\": \"123 Main St\", \"post_code\": \"12345\", \"password\": \"newPassword\", \"old_password\": \"oldPassword\"}";

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .patch(URL + "/api/owner/updateuser/by-user")
                .then()
                .statusCode(200)
                // Assert that the response code is 200 (OK)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
    }
}
