package hrmApiTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Util {

    public static final String URL = "https://developement.hrm-backend.interactivehr.co.uk";

    public static String  getSuperadminToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"admin@gmail.com\", \"password\": \"12345678We\"}")
                .when()
                .post(URL + "/api/v2.0/login")
                .then()
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(response);

        String token = jsonNodeOfDailyView.get("token").asText();
        System.out.println("superadmin token : " + token);

        return token;

    }

    public static String  getBusinessOwnerToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"md.nazmul.islam@devnazmul.site\", \"password\": \"12345678#We\"}")
                .when()
                .post(URL + "/api/v2.0/login")
                .then()
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(response);

        String token = jsonNodeOfDailyView.get("data").get("token").asText();
        System.out.println("superadmin token : " + token);

        return token;

    }


    public static String  getOtherBusinessOwnerToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"rifatalashwad0.026133453414431074@gmail.com\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/v1.0/login")
                .then()
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(response);

        String token = jsonNodeOfDailyView.get("token").asText();
        System.out.println("business owner token : " + token);

        return token;
    }
    public static String  getPropertyDealerBusinessOwnerToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"rifatalashwad0.7262331127961494@gmail.com\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/v1.0/login")
                .then()
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(response);

        String token = jsonNodeOfDailyView.get("token").asText();
        System.out.println("business owner token : " + token);

        return token;
    }
    public static String  getBusinessOwnerToken(String email,String password) throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \""+ email +"\", \"password\": \""+password+"\"}")
                .when()
                .post(URL + "/api/v1.0/login")
                .then()
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(response);

        String token = jsonNodeOfDailyView.get("token").asText();
        System.out.println("business owner token : " + token);

        return token;
    }
}
