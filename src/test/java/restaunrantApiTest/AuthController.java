package restaunrantApiTest;

import data.reqres.PostData;
import in.reqres.TestPostRequests;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@Epic("Rest Assured POC - Example Tests")
@Feature("Performing different API Tests using Rest-Assured")
public class AuthController {


    private static final String URL = "https://mughalsignandprint.co.uk/restaurant";




    @Test
    @Description("Test all cases of the Laravel API for storing a forget password token")
    @Story("Execute Post requests using Rest Assured")
    public void resendEmailVerifyToken() {
        final String validEmailResponse = given().contentType(ContentType.JSON)
                .body("{\"email\": \"rifat@gmail.com\"}")
                .when()
                .post(URL + "/api/resend-email-verify-mail")
                .then()
                .statusCode(200)
                .body("message", equalTo("please check email"))
                .extract()
                .response()
                .body()
                .asString();
        System.out.println("Valid email response: " + validEmailResponse);
        final String userNotFoundResponse = given().contentType(ContentType.JSON)
                .body("{\"email\": \"nonexistinguser@g.c\"}")
                .when()
                .post(URL + "/resend-email-verify-mail")
                .then()
                .statusCode(404)
                .extract()
                .response()
                .body()
                .asString();
        System.out.println("User not found response: " + userNotFoundResponse);
    }
    @Test
    @Description("Test all cases of the Laravel API for storing a forget password token")
    @Story("Execute Post requests using Rest Assured")
    public void register() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        final String validRegistrationResponse = given().contentType(ContentType.JSON)
                .body("{\"email\": \"rifat" +randomNumber + "@gmail.com\", \"password\": \"yourPassword\"}")
                .log().headers() // log only the headers
                .log().body() // log only the request body
                .when()
                .post(URL + "/api/auth/register")
                .then()
                .statusCode(201)
                .body("message", equalTo("You have successfully registered"))

                .extract()
                .response()
                .body()
                .asString();
        System.out.println("Valid registration response: " + validRegistrationResponse);

        final String missingEmailResponse = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"password\": \"yourPassword\"}")
                .when()
                .post(URL + "/api/auth/register")
                .then()
                .statusCode(422)

                .extract()
                .response()
                .body()
                .asString();
        System.out.println("Missing email response: " + missingEmailResponse);

    }
    @Test
    public void loginWithValidCredentialsShouldReturn200() {
        final String success =    given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"email\": \"superman@g.c\", \"password\": \"12345678\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .extract()
                .response()

                .asString();
        System.out.println(" response: " + success);
    }

    @Test
    public void loginWithInvalidCredentialsShouldReturn401() {
        given().contentType(ContentType.JSON)
                .body("{\"email\": \"test@gmail.com\", \"password\": \"invalidPassword\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .statusCode(401)
                .body("message", equalTo("Invalid Credentials"));
    }

    @Test
    public void loginWithLockedUserShouldReturn403() {
        // First, we need to lock the user by making three failed login attempts
        given().contentType(ContentType.JSON)
                .body("{\"email\": \"test@gmail.com\", \"password\": \"invalidPassword\"}")
                .when()
                .post(URL + "/api/auth");

        given().contentType(ContentType.JSON)
                .body("{\"email\": \"test@gmail.com\", \"password\": \"invalidPassword\"}")
                .when()
                .post(URL + "/api/auth");

        given().contentType(ContentType.JSON)
                .body("{\"email\": \"test@gmail.com\", \"password\": \"invalidPassword\"}")
                .when()
                .post(URL + "/api/auth");

        // After three failed attempts, the user should be locked
        given().contentType(ContentType.JSON)
                .body("{\"email\": \"test@gmail.com\", \"password\": \"yourPassword\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .statusCode(403)
                .body("message", equalTo("You have 3 failed attempts. Reset your password or wait for 15 minutes to access your account."));
    }

    @Test
    public void loginWithUnverifiedEmailShouldReturn409() {
        // Create a new user with unverified email


        // Try to login with unverified email after 24 hours
        given().contentType(ContentType.JSON)
                .body("{\"email\": \"" + "rifat@gmail.com" + "\", \"password\": \"" + "12345678" + "\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .statusCode(409)
                .body("message", equalTo("please activate your email first"));
    }

    @Test
    public void loginWithMissingEmailShouldReturn422() {
        given().contentType(ContentType.JSON)
                .body("{\"password\": \"yourPassword\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .statusCode(302)
                .body("message", equalTo("The given data was invalid."))
                .body("errors.email[0]", equalTo("The email field is required."));
    }

    @Test
    public void loginWithMissingPasswordShouldReturn422() {
        given().contentType(ContentType.JSON)
                .body("{\"email\": \"test@gmail.com\"}")
                .when()
                .post(URL + "/api/auth")
                .then()
                .statusCode(302);

    }






}
