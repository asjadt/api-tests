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

public class UserController {
    @Test
    public void testGetCustomerReportSuperadminAPI() throws JsonProcessingException {
        int perPage = 1;
        String searchTerm = "1";

      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("perPage", perPage)
                .queryParam("search_term", searchTerm)
                .when()
                .get(URL +"/api/superadmin/customer-list/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testGetOwnerReportAPI() throws JsonProcessingException {
        int perPage = 1;
        String searchTerm = "1";

        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("perPage", perPage)
                .queryParam("search_term", searchTerm)
                .when()
                .get(URL +"/api/superadmin/owner-list/{perPage}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }

    @Test
    public void testDeleteCustomerByIdAPI() throws JsonProcessingException {
        int id = 1;

        String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .pathParam("id", id)
                .when()
                .delete(URL +"/api/superadmin/user-delete/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
}
