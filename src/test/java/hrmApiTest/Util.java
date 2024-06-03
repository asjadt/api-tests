package hrmApiTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

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

                .body("{\"email\": \"abcd@gmail.com\", \"password\": \"123456@We\"}")
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


    
    public static String  getUserToken(String email,String password) throws JsonProcessingException {

        String response =   given().contentType(ContentType.JSON)

                .body("{\"email\": \""+ email +"\", \"password\": \""+password+"\"}")
                .when()
                .post(URL + "/api/v2.0/login")
                .then()
                .extract()
                .response()
                .body()
                .asString();

         
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNodeOfDailyView = objectMapper.readTree(response);
        
                String token = jsonNodeOfDailyView.get("data").get("token").asText();
                
                System.out.println("token retrieved");

                System.out.println(token);
                return token;
    }




   

    public static String getRandomDate(int startYear, int endYear) {
        // Define the start and end dates
        LocalDate startDate = LocalDate.of(startYear, 1, 1);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);

        // Calculate the number of days between the start and end dates
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // Generate a random number of days to add to the start date
        long randomDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);

        // Generate the random date
        LocalDate randomDate = startDate.plusDays(randomDays);

        // Format the random date to d-M-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        return randomDate.format(formatter);
    }


    public static  Integer getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


}
