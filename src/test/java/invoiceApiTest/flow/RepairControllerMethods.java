package invoiceApiTest.flow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;

public class RepairControllerMethods {
    @Test
    public String testCreateRepairAPI(String businessOwnerToken, Integer repairCategoryId,Integer propertyId) throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("property_id", propertyId); // Replace with the desired property ID
        requestBody.put("repair_category_id", repairCategoryId); // Replace with the desired repair category ID
        requestBody.put("item_description", "item_description");
        requestBody.put("price", "10");
        requestBody.put("create_date", "2019-06-29");
        requestBody.put("images", new String[]{"a.jpg", "b.jpg", "c.jpg"});
        requestBody.put("receipt", new String[]{"a.jpg", "b.jpg", "c.jpg"});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/repairs")
                .then()
                .statusCode(201) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;
    }

    @Test
    public void testUpdateRepairAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 61); // Replace with the desired repair ID
        requestBody.put("property_id", 1033); // Replace with the desired property ID
        requestBody.put("repair_category_id", "1"); // Replace with the desired repair category ID
        requestBody.put("item_description", "item_description");
        requestBody.put("price", "10");
        requestBody.put("create_date", "2019-06-29");
        requestBody.put("images", new String[]{"a.jpg", "b.jpg", "c.jpg"});
        requestBody.put("receipt", new String[]{"a.jpg", "b.jpg", "c.jpg"});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/repairs")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetRepairsAPI() throws JsonProcessingException {
        // Define the parameters
        int perPage = 6; // Replace with the desired perPage value
        int repairId = 1; // Replace with the desired repair_id value
        int propertyId = 1033; // Replace with the desired property_id value
        String startDate = "2019-06-29"; // Replace with the desired start_date
        String endDate = "2030-06-29"; // Replace with the desired end_date
        String orderBy = "ASC"; // Replace with the desired order_by value
        String searchKey = ""; // Replace with the desired search_key
        String repairCategory = ""; // Replace with the desired repair_category

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParam("perPage", perPage)
                .queryParam("repair_id", repairId)
                .queryParam("property_id", propertyId)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("order_by", orderBy)
                .queryParam("search_key", searchKey)
                .queryParam("repair_category", repairCategory)
                .when()
                .get(URL + "/api/v1.0/repairs/{perPage}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testGetRepairByIdAPI() throws JsonProcessingException {
        // Define the parameter
        String id = "hGyE60eyxo"; // Replace with the desired ID value

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .pathParam("id", id)
                .when()
                .get(URL + "/api/v1.0/repairs/get/single/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testDeleteRepairByIdAPI() throws JsonProcessingException {
        // Define the parameter
        Integer id = 60; // Replace with the desired ID value

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getPropertyDealerBusinessOwnerToken())
                .header("pin", "1234")
                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/repairs/{id}")
                .then()
                .statusCode(200) // Assuming a successful response should return status code 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }


}
