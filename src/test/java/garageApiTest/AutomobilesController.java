package garageApiTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.*;

import static garageApiTest.Util.URL;
import static garageApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;
public class AutomobilesController {

    @Test
    public void testCreateAutomobileCategoryAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "car");

        // Perform the API request
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/automobile-categories")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);

    }
    @Test
    public void testUpdateAutomobileCategoryAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 2);
        requestBody.put("name", "car");

        // Perform the API request
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/automobile-categories")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAutomobileCategoriesAPI() throws JsonProcessingException {
        String perPage = "6";
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        // Perform the API request
        String response =      given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-categories/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAllAutomobileCategoriesAPI() throws JsonProcessingException {
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        // Perform the API request
        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-categories/get/all")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


    @Test
    public void testGetAutomobileCategoryByIdAPI() throws JsonProcessingException {
        String id = "1";

        // Perform the API request
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .pathParam("id", id)
                .when()
                .get(URL + "/api/v1.0/automobile-categories/single/get/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testDeleteAutomobileCategoryByIdAPI() throws JsonProcessingException {
        String id = "5";

        // Perform the API request
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace {your_token} with the actual token

                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/automobile-categories/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testCreateAutomobileMakeAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "car");
        requestBody.put("description", "car");
        requestBody.put("automobile_category_id", "1");

        String response =     given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/automobile-makes")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


    @Test
    public void testUpdateAutomobileMakeAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 68);
        requestBody.put("name", "car");
        requestBody.put("description", "description");
        requestBody.put("automobile_category_id", "1");
        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/automobile-makes")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAutomobileMakesAPI() throws JsonProcessingException {
        int categoryId = 1;
        int perPage = 6;
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .pathParam("categoryId", categoryId)
                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-makes/{categoryId}/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAllAutomobileMakesAPI() throws JsonProcessingException {
        int categoryId = 1;
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .pathParam("categoryId", categoryId)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-makes-all/{categoryId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAllAutomobileMakesV2API() throws JsonProcessingException {
        int categoryId = 1;
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .pathParam("categoryId", categoryId)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v2.0/automobile-makes-all/{categoryId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


    @Test
    public void testGetAutomobileMakeByIdAPI() throws JsonProcessingException {
        int id = 68;

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .pathParam("id", id)
                .when()
                .get(URL + "/api/v1.0/automobile-makes/single/get/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeleteAutomobileMakeByIdAPI() throws JsonProcessingException {
        int id = 69;

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken())) // Replace with actual access token

                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/automobile-makes/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testCreateAutomobileModelAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "car");
        requestBody.put("description", "car");
        requestBody.put("automobile_make_id", "1");

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))  // Replace {token} with the actual token

                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/automobile-models")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUpdateAutomobileModelAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 586);
        requestBody.put("name", "car");
        requestBody.put("description", "description");
        requestBody.put("automobile_make_id", "1");

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))  // Replace {token} with the actual token
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/automobile-models")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAutomobileModelAPI() throws JsonProcessingException {
        int makeId = 1;
        int perPage = 6;
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))  // Replace {token} with the actual token

                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-models/{makeId}/{perPage}", makeId, perPage)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetAutomobileModelByIdAPI() throws JsonProcessingException {
        int id = 586;

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))  // Replace {token} with the actual token

                .when()
                .get(URL + "/api/v1.0/automobile-models/single/get/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetAllAutomobileModelsAPI() throws JsonProcessingException {
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";
        List<Integer> automobileMakeIds = Arrays.asList(1, 2);

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))  // Replace {token} with the actual token

                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .queryParam("automobile_make_ids[]", automobileMakeIds)
                .when()
                .get(URL + "/api/v1.0/automobile-models-all")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeleteAutomobileModelByIdAPI() throws JsonProcessingException {
        int id = 587;

     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))  // Replace {token} with the actual token

                .when()
                .delete(URL + "/api/v1.0/automobile-models/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testCreateAutomobileModelVariantAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "car");
        requestBody.put("description", "car");
        requestBody.put("automobile_model_id", "1");

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/automobile-model-variants")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testUpdateAutomobileModelVariantAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1624);
        requestBody.put("name", "car");
        requestBody.put("description", "description");
        requestBody.put("automobile_model_id", "1");
        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/automobile-model-variants")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetAutomobileModelVariantsAPI() throws JsonProcessingException {
        int modelId = 1;
        int perPage = 6;
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .pathParam("modelId", modelId)
                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-model-variants/{modelId}/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetAutomobileModelVariantByIdAPI() throws JsonProcessingException {
        int id = 1624;

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .pathParam("id", id)
                .when()
                .get(URL + "/api/v1.0/automobile-model-variants/single/get/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteAutomobileModelVariantByIdAPI() throws JsonProcessingException {
        int id = 1625;

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/automobile-model-variants/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }



    @Test
    public void testCreateAutomobileFuelTypeAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "car");
        requestBody.put("description", "car");
        requestBody.put("automobile_model_variant_id", "1");

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/automobile-fuel-types")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testUpdateAutomobileFuelTypeAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "car");
        requestBody.put("description", "description");
        requestBody.put("automobile_model_variant_id", "1");

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/automobile-fuel-types")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetAutomobileFuelTypeAPI() throws JsonProcessingException {
        String modelVariantId = "1";
        String perPage = "6";
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .pathParam("modelVariantId", modelVariantId)
                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/automobile-fuel-types/{modelVariantId}/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetAutomobileFuelTypeByIdAPI() throws JsonProcessingException {
        String id = "1";

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .pathParam("id", id)
                .when()
                .get(URL + "/api/v1.0/automobile-fuel-types/single/get/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteAutomobileFuelTypeByIdAPI() throws JsonProcessingException {
        String id = "1";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", ("Bearer " + getSuperadminToken()))
                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/automobile-fuel-types/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }



}
