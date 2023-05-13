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

public class Util {
    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);
    public static final String URL = "https://quickreview.app/restaurant";

    public static String  getToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"superman@g.c\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .extract()
                .response()
                .body()
                .asString();

        String token = JsonPath.from(response).getString("token");
        System.out.println("Token: " + token);

        return token;

    }
}
