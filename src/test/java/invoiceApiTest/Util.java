package invoiceApiTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Util {

    public static final String URL = "https://quickreview.app/invoice-management-system";

    public static String  getSuperadminToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"admin@gmail.com\", \"password\": \"12345678We\"}")
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
        System.out.println("superadmin token : " + token);

        return token;

    }
}
