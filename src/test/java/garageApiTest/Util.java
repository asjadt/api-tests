package garageApiTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class Util {

    public static final String URL = "https://britanniagreen.co.uk/garage";

    public static String  getSuperadminToken() throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \"admin@gmail.com\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .extract()
                .response()
                .body()
                .asString();

        String token = JsonPath.from(response).getString("token");
        System.out.println("superadmin token : " + token);

        return token;

    }
}
