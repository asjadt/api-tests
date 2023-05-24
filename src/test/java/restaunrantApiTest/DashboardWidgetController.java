package restaunrantApiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import in.reqres.TestPostRequests;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static restaunrantApiTest.Util.URL;
import static restaunrantApiTest.Util.getSuperadminToken;

public class DashboardWidgetController {

    private static final Logger LOG = LogManager.getLogger (TestPostRequests.class);




    @Test
    public void testCreateDashboardWidget() throws JsonProcessingException {
        String token = getSuperadminToken();
        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"name\": \"test\", \"description\": \"12345678\", \"user_type\": \"admin\"}")
                .when()
                .post(URL +"/api/superadmin/dashboard-widget/create")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();
    }
    public String testCreateDashboardWidgetGetString(String superadmintoken) throws JsonProcessingException {

        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + superadmintoken)
                .body("{\"name\": \"test\", \"description\": \"12345678\", \"user_type\": \"admin\"}")
                .when()
                .post(URL +"/api/superadmin/dashboard-widget/create")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);
        return response;
    }


    @Test
    public void testUpdateDashboardWidget(String superadmintoken,Integer dashboardWidgetId) throws JsonProcessingException {

        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + superadmintoken)
                .body("{\"id\": \""+dashboardWidgetId+"\", \"name\": \"test\", \"description\": \"12345678\", \"user_type\": \"admin\"}")
                .when()
                .put(URL  + "/api/superadmin/dashboard-widget/update")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testGetDashboardWidget(String superadmintoken) throws JsonProcessingException {

        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + superadmintoken)
                .when()
                .get(URL +"/api/superadmin/dashboard-widget/get")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println(response);


    }

    @Test
    public void testGetDashboardWidgetById(String superadmintoken,Integer id) throws JsonProcessingException {


        String response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + superadmintoken)
                .pathParam("id", id)
                .when()
                .get(URL +"/api/superadmin/dashboard-widget/get/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .asString();

        System.out.println(response);

    }

    @Test
    public void testDeleteWidgetByIdAPI(String superadmintoken,Integer widgetId) throws JsonProcessingException {


      String response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + superadmintoken) // Replace with the actual access token
                .pathParam("id", widgetId)
                .when()
                .delete(URL +"/api/superadmin/dashboard-widget/delete/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
        System.out.println(response);
    }
    @Test
    public void testDeleteUserDashboardByIdAPI() throws JsonProcessingException {
        String dashboardId = "1"; // Replace with the actual dashboard ID

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken()) // Replace with the actual access token
                .pathParam("id", dashboardId)
                .when()
                .delete(URL+ "/api/user-dashboard/delete/{id}")
                .then()
                .statusCode(200)
                // Additional assertions for the response data can be added here
                .extract()
                .response()
                .asString();
    }



}
