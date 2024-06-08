package hrmApiTest.flow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static hrmApiTest.Util.*;
import static io.restassured.RestAssured.given;


public class BusinessManagementControllerMethods {

        private static final String[] FIRST_NAMES = {
                "John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Ava", "Robert", "Isabella",
                "Liam", "Mason", "Benjamin", "Elijah", "Logan", "Alexander", "Charlotte", "Amelia", "Mia", "Harper",
                "Ethan", "Daniel", "Matthew", "Joseph", "Sebastian", "Jack", "Henry", "Lucas", "David", "Carter",
                "Gabriel", "Samuel", "Wyatt", "Grayson", "Isaac", "Lincoln", "Anthony", "Jayden", "Johnathan", "Aiden",
                "Victoria", "Grace", "Chloe", "Camila", "Luna", "Layla", "Lily", "Hannah", "Aria", "Zoe",
                "Samantha", "Natalie", "Eleanor", "Hazel", "Aurora", "Savannah", "Brooklyn", "Bella", "Claire", "Skylar",
                "Paisley", "Everly", "Anna", "Caroline", "Nova", "Genesis", "Emilia", "Kennedy", "Sofia", "Aubrey",
                "Evelyn", "Autumn", "Mila", "Peyton", "Willow", "Aurora", "Naomi", "Ellie", "Lillian", "Addison",
                "Lucy", "Ariana", "Stella", "Maya", "Penelope", "Madeline", "Ivy", "Vivian", "Scarlett", "Emily",
                "Camila", "Violet", "Paisley", "Annabelle", "Madelyn", "Hailey", "Riley", "Alyssa", "Zara", "Isla",
                "Kinsley", "Kaylee", "Sydney", "Mackenzie", "Jasmine", "Katherine", "Eliza", "Juliana", "Piper", "Alexandra",
                "Brody", "Oliver", "Christian", "Nolan", "Parker", "Aaron", "Connor", "Josiah", "Isaiah", "Gavin",
                "Dominic", "Brayden", "Jaxon", "Adrian", "Leo", "Adam", "Ian", "Carson", "Nathan", "Austin",
                "Jordan", "Brandon", "Cole", "Hudson", "Easton", "Xavier", "Zachary", "Angel", "Aaron", "Eli",
                "Ezra", "Bentley", "Sawyer", "Jason", "Evan", "Adrian", "Miles", "Wesley", "Alex", "Bryson",
                "Ryker", "Asher", "Jeremiah", "Micah", "Caleb", "Max", "Silas", "Declan", "Jace", "River",
                "Ryder", "Griffin", "Diego", "Kai", "Jayce", "Rowan", "Archer", "Enzo", "Jasper", "Maverick",
                "Landon", "Gage", "Jude", "Chase", "Tucker", "Bryce", "Ronan", "Cody", "Gage", "Beau",
                "Blake", "Colton", "Cash", "Milo", "Spencer", "Preston", "Luka", "Tristan", "Jake", "Knox",
                "Matteo", "Kaleb", "Kane", "Myles", "Bradley", "Dean", "Anderson", "Reid", "Declan", "Killian"
            };
            
            private static final String[] LAST_NAMES = {
                "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
                "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
                "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
                "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
                "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts",
                "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker", "Cruz", "Edwards", "Collins", "Reyes",
                "Stewart", "Morris", "Morales", "Murphy", "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper",
                "Peterson", "Bailey", "Reed", "Kelly", "Howard", "Ramos", "Kim", "Cox", "Ward", "Richardson",
                "Watson", "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza", "Ruiz", "Hughes",
                "Price", "Alvarez", "Castillo", "Sanders", "Patel", "Myers", "Long", "Ross", "Foster", "Jimenez",
                "Powell", "Jenkins", "Perry", "Russell", "Sullivan", "Bell", "Coleman", "Butler", "Henderson", "Barnes",
                "Gonzales", "Fisher", "Vasquez", "Simmons", "Romero", "Jordan", "Patterson", "Alexander", "Hamilton", "Graham",
                "Reynolds", "Griffin", "Wallace", "Moreno", "West", "Cole", "Hayes", "Bryant", "Herrera", "Gibson",
                "Ellis", "Tran", "Medina", "Aguilar", "Stevens", "Murray", "Ford", "Castro", "Marshall", "Owens",
                "Harrison", "Fernandez", "McDonald", "Woods", "Washington", "Kennedy", "Wells", "Vargas", "Henry", "Chen",
                "Freeman", "Webb", "Tucker", "Guzman", "Burns", "Crawford", "Olson", "Simpson", "Porter", "Hunter",
                "Gordon", "Mendez", "Silva", "Shaw", "Snyder", "Mason", "Dixon", "Muñoz", "Hunt", "Hicks",
                "Holmes", "Palmer", "Wagner", "Black", "Robertson", "Boyd", "Rose", "Stone", "Salazar", "Fox"
            };
        
          
        
            private static final String[] DOMAINS = {
                "example.com", "mail.com", "inbox.com", "webmail.com", "email.com",
                "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com",
                "aol.com", "zoho.com", "yandex.com", "protonmail.com", "gmx.com",
                "fastmail.com", "hushmail.com", "rocketmail.com", "live.com", "msn.com",
                "rediffmail.com", "lycos.com", "mail.ru", "att.net", "verizon.net",
                "comcast.net", "sbcglobal.net", "bellsouth.net", "charter.net", "shaw.ca",
                "rogers.com", "sympatico.ca", "btinternet.com", "virginmedia.com",
                "sky.com", "talktalk.net", "tiscali.co.uk", "ntlworld.com", "btopenworld.com",
                "blueyonder.co.uk", "orange.fr", "wanadoo.fr", "free.fr", "laposte.net",
                "libero.it", "virgilio.it", "alice.it", "tin.it", "t-online.de", "web.de",
                "gmx.de", "arcor.de", "seznam.cz", "centrum.cz", "volny.cz", "tiscali.cz",
                "post.cz", "netvigator.com", "sina.com", "qq.com", "naver.com", "daum.net",
                "hanmail.net", "nate.com", "yahoo.co.jp", "yahoo.co.in", "yahoo.co.uk",
                "yahoo.com.br", "yahoo.com.au", "yahoo.ca", "yahoo.com.sg", "yahoo.co.id",
                "yahoo.com.mx", "yahoo.com.hk", "yahoo.fr", "yahoo.de", "yahoo.it",
                "yahoo.es", "yahoo.co.kr", "yahoo.se", "yahoo.com.ar", "yahoo.com.my",
                "yahoo.com.ph", "yahoo.co.th", "yahoo.co.za"
            };
            private static final Random random = new Random();
        
            // Method to generate email from first name and last name
            private static String generateEmail(String firstName, String lastName) {
                // Remove any spaces and convert to lowercase
                String cleanedFirstName = firstName.toLowerCase().trim().replaceAll("\\s", "");
                String cleanedLastName = lastName.toLowerCase().trim().replaceAll("\\s", "");
                
                // Concatenate first name, last name, and randomly selected domain
                String domain = DOMAINS[random.nextInt(DOMAINS.length)];
                return cleanedFirstName + "." + cleanedLastName + "@" + domain;
            }

            private static Map<String, Object> createTimeMap(int day, String startAt, String endAt, boolean isWeekend) {
                Map<String, Object> timeMap = new HashMap<>();
                timeMap.put("day", day);
                timeMap.put("start_at", startAt);
                timeMap.put("end_at", endAt);
                timeMap.put("is_weekend", isWeekend);
                return timeMap;
            }
     public String createBusiness(String superAdminToken,String password) throws JsonProcessingException {
  
        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate email from name
        String email = generateEmail(firstName, lastName);



         // Prepare the request body
         Map<String, Object> requestBody = new HashMap<>();
          // User information
          Map<String, Object> user = new HashMap<>();
          user.put("image", "");
          user.put("first_Name", firstName);
          user.put("last_Name", lastName);
          user.put("email", email);
          user.put("phone", "01777777777");
          user.put("password", password);
          user.put("password_confirmation", "");
          user.put("send_password", 0);
          
          requestBody.put("user", user);
          
          // Business information
          Map<String, Object> business = new HashMap<>();
          business.put("name", firstName + " " + lastName + "'s shop" );
          business.put("email", email);
          business.put("phone", "01777777777");
          business.put("web_page", "");
          business.put("address_line_1", "fgjhfgn");
          business.put("address_line_2", "");
          business.put("lat", "");
          business.put("long", "");
          business.put("country", "xfgnxfgnfxgn");
          business.put("city", "fgjnxfgnxfgn");
          business.put("currency", "£");
          business.put("postcode", "xfgnxfgn");
          business.put("start_date", getRandomDate(1991, 2000));
          business.put("type", "regular");
          business.put("logo", "");
          business.put("image", "");
          business.put("images", new ArrayList<>());
          business.put("flexible_rota_enabled", 0);
          business.put("pension_scheme_registered", 0);
          business.put("pension_scheme_name", "");
          
          List<Map<String, Object>> pensionSchemeLetters = new ArrayList<>();
          Map<String, Object> letter = new HashMap<>();
          letter.put("id", 1);
          letter.put("file", "");
          letter.put("description", "");
          pensionSchemeLetters.add(letter);
          
          business.put("pension_scheme_letters", pensionSchemeLetters);
          
          requestBody.put("business", business);
          
          // Times information
          List<Map<String, Object>> times = new ArrayList<>();
          times.add(createTimeMap(0, "09:00:00", "18:00:00", true));
          times.add(createTimeMap(1, "09:00:00", "18:00:00", false));
          times.add(createTimeMap(2, "09:00:00", "18:00:00", false));
          times.add(createTimeMap(3, "09:00:00", "18:00:00", false));
          times.add(createTimeMap(4, "09:00:00", "18:00:00", false));
          times.add(createTimeMap(5, "09:00:00", "18:00:00", false));
          times.add(createTimeMap(6, "09:00:00", "18:00:00", false));
          
          requestBody.put("times", times);


    
          
        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + superAdminToken) // Replace with your method to get the bearer token
                .body(requestBody)
                .when()
                .post(URL +"/api/v1.0/auth/register-with-business") // Adjust the URL if needed
                .then()
                .extract()
                .response()
                .asString();


        
        return response;
    }



}
