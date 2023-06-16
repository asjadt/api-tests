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
import static garageApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

public class AffiliationController {
    @Test
    public void testCreateAffiliationAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "car");
        requestBody.put("description", "description");
        requestBody.put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");



     String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/affiliations")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testUpdateAffiliationAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "car");
        requestBody.put("description", "description");
        requestBody.put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");



        String response =       given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/affiliations")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }

    @Test
    public void testGetAffiliationsAPI() throws JsonProcessingException {

        int perPage = 6;
        String startDate = "2019-06-29";
        String endDate = "2030-06-29";
        String searchKey = "";

        String response =   given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .pathParam("perPage", perPage)
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .queryParam("search_key", searchKey)
                .when()
                .get(URL + "/api/v1.0/affiliations/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteAffiliationByIdAPI() throws JsonProcessingException {

        int id = 1;

        String response =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .pathParam("id", id)
                .when()
                .delete(URL + "/api/v1.0/affiliations/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }


}
