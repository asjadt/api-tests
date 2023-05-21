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
import static org.testng.AssertJUnit.assertNotNull;
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getToken;


public class AllInOne {
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
