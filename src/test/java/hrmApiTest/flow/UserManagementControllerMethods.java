package hrmApiTest.flow;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static hrmApiTest.Util.URL;
import static hrmApiTest.Util.getRandomDate;
import static hrmApiTest.Util.getRandomNumber;
import static hrmApiTest.Util.getSuperadminToken;
import static io.restassured.RestAssured.given;
public class UserManagementControllerMethods {

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

 // Method to get a random ID from the list of IDs
 private static int getRandomId(List<Integer> ids) {
    int index = random.nextInt(ids.size());
    return ids.get(index);
}


    public String createBritishCitizen(String businessOwnerToken,Integer businessId,List workLocations,List designations,List employmentStatuses, List workShifts,List departments, Integer times) throws JsonProcessingException {
        Random random = new Random();

        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate email from name
        String email = generateEmail(firstName, lastName);
        
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();

        
        requestBody.put("NI_number", "" + (Math.random() * 99999));
        requestBody.put("first_Name", firstName);
        requestBody.put("last_Name", lastName);
        requestBody.put("email", email);
        requestBody.put("date_of_birth", "31-05-2010");
        requestBody.put("password", "12345678#We");
        requestBody.put("password_confirmation", "12345678#We");
        requestBody.put("phone", "01777777777");
        requestBody.put("address_line_1", "Dhaka, Bangladesh");
        requestBody.put("country", "Bangladesh");
        requestBody.put("city", "Dhaka");
        requestBody.put("lat", "23.804093");
        requestBody.put("long", "90.4152376");
        requestBody.put("role", ("business_employee#" + businessId));
        requestBody.put("gender", "male");
        requestBody.put("user_id", "FAT-0002");
        requestBody.put("postcode", "1212");
        requestBody.put("work_shift_id", getRandomId(workShifts));
        requestBody.put("is_in_employee", 1);
        requestBody.put("designation_id", getRandomId(designations));
        requestBody.put("employment_status_id", getRandomId(employmentStatuses));
        requestBody.put("salary_per_annum", "5000");
        requestBody.put("work_location_id", getRandomId(workLocations));
        requestBody.put("weekly_contractual_hours", "50");
        requestBody.put("minimum_working_days_per_week", "5");
        requestBody.put("overtime_rate", "50");
        requestBody.put("recruitment_processes", Arrays.asList(
            new HashMap<String, Object>() {{
                put("recruitment_process_id", getRandomNumber(1,5));
                put("description", "jobsss");
                put("attachments", new ArrayList<>());
            }},
            new HashMap<String, Object>() {{
                put("recruitment_process_id", getRandomNumber(6,11));
                put("description", "");
                put("attachments", new ArrayList<>());
            }}
            // Add more processes here...
        ));
        requestBody.put("joining_date", getRandomDate(2001, 2023));
        requestBody.put("address_line_2", "");
        requestBody.put("departments", Arrays.asList(getRandomId(departments)));
        requestBody.put("emergency_contact_details", Arrays.asList(
            new HashMap<String, Object>() {{
                put("relationship_of_above_to_you", "Father");
                put("first_Name", FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]);
                put("last_Name", LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
                put("address_line_1", "Dhaka, Bangladesh");
                put("city", "Dhaka");
                put("country", "Bangladesh");
                put("postcode", "1212");
                put("lat", "23.804093");
                put("long", "90.4152376");
                put("mobile_tel_number", "01777777777");
            }},
            new HashMap<>() // Add more emergency contacts here...
        ));
        requestBody.put("immigration_status", "british_citizen");
        requestBody.put("sponsorship_details", new HashMap<String, Object>() {{
            put("date_assigned", "");
            put("expiry_date", "");
            put("status", "");
            put("note", "");
            put("certificate_number", "");
            put("current_certificate_status", "");
            put("is_sponsorship_withdrawn", 0);
        }});
        requestBody.put("is_active_visa_details", 0);
        requestBody.put("is_active_right_to_works", 0);
        requestBody.put("visa_details", new HashMap<String, Object>() {{
            put("BRP_number", "");
            put("visa_issue_date", "");
            put("visa_expiry_date", "");
            put("place_of_issue", "");
            put("visa_docs", new ArrayList<>());
        }});
        requestBody.put("right_to_works", new HashMap<String, Object>() {{
            put("right_to_work_code", "");
            put("right_to_work_check_date", "");
            put("right_to_work_expiry_date", "");
            put("right_to_work_docs", new ArrayList<>());
        }});
        requestBody.put("passport_details", new HashMap<String, Object>() {{
            put("passport_number", "");
            put("passport_issue_date", "");
            put("passport_expiry_date", "");
            put("place_of_issue", "");
        }});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken) // Replace with your method to get the bearer token
                .body(requestBody)
                .when()
                .post(URL +"/api/v2.0/users") // Adjust the URL if needed
                .then()
                .extract()
                .response()
                .asString();

        
        
        return response;
    }

    public String createILRCitizen(String businessOwnerToken,Integer businessId, List workLocations, List designations, List employmentStatuses,List workShifts, List departments, Integer times) throws JsonProcessingException {
        Random random = new Random();

        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate email from name
        String email = generateEmail(firstName, lastName);


    
        
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();

        
        
        requestBody.put("NI_number", "" + (Math.random() * 99999));
        requestBody.put("first_Name", firstName);
        requestBody.put("last_Name", lastName);
        requestBody.put("email", email);
        requestBody.put("date_of_birth", getRandomDate(1980, 2009));
        requestBody.put("password", "12345678#We");
        requestBody.put("password_confirmation", "12345678#We");
        requestBody.put("phone", "01777777777");
        requestBody.put("address_line_1", "Dhaka, Bangladesh");
        requestBody.put("country", "Bangladesh");
        requestBody.put("city", "Dhaka");
        requestBody.put("lat", "23.804093");
        requestBody.put("long", "90.4152376");
        requestBody.put("role", ("business_employee#" + businessId));
        requestBody.put("gender", "male");
        requestBody.put("user_id", "FAT-0002");
        requestBody.put("postcode", "1212");
        requestBody.put("work_shift_id", getRandomId(workShifts));
        requestBody.put("is_in_employee", 1);
        requestBody.put("designation_id", getRandomId(designations));
        requestBody.put("employment_status_id", getRandomId(employmentStatuses));
        requestBody.put("salary_per_annum", "5000");
        requestBody.put("work_location_id", getRandomId(workLocations));
        requestBody.put("weekly_contractual_hours", "50");
        requestBody.put("minimum_working_days_per_week", "5");
        requestBody.put("overtime_rate", "50");
        requestBody.put("recruitment_processes", Arrays.asList(
            new HashMap<String, Object>() {{
                put("recruitment_process_id", getRandomNumber(1,5));
                put("description", "jobsss");
                put("attachments", new ArrayList<>());
            }},
            new HashMap<String, Object>() {{
                put("recruitment_process_id", getRandomNumber(6,11));
                put("description", "");
                put("attachments", new ArrayList<>());
            }}
            // Add more processes here...
        ));
        requestBody.put("joining_date", getRandomDate(2001, 2023));
        requestBody.put("address_line_2", "");
        requestBody.put("departments", Arrays.asList(getRandomId(departments)));
        requestBody.put("emergency_contact_details", Arrays.asList(
            new HashMap<String, Object>() {{
                put("relationship_of_above_to_you", "Father");
                put("first_Name", FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]);
                put("last_Name", LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
                put("address_line_1", "Dhaka, Bangladesh");
                put("city", "Dhaka");
                put("country", "Bangladesh");
                put("postcode", "1212");
                put("lat", "23.804093");
                put("long", "90.4152376");
                put("mobile_tel_number", "01777777777");
            }},
            new HashMap<>() // Add more emergency contacts here...
        ));
        requestBody.put("immigration_status", "british_citizen");
        requestBody.put("sponsorship_details", new HashMap<String, Object>() {{
            put("date_assigned", "");
            put("expiry_date", "");
            put("status", "");
            put("note", "");
            put("certificate_number", "");
            put("current_certificate_status", "");
            put("is_sponsorship_withdrawn", 0);
        }});
        requestBody.put("is_active_visa_details", 0);
        requestBody.put("is_active_right_to_works", 1);
        requestBody.put("visa_details", new HashMap<String, Object>() {{
            put("BRP_number", "");
            put("visa_issue_date", "");
            put("visa_expiry_date", "");
            put("place_of_issue", "");
            put("visa_docs", new ArrayList<>());
        }});
        requestBody.put("right_to_works", new HashMap<String, Object>() {{
            put("right_to_work_code", "" + (Math.random() * 999999));
            put("right_to_work_check_date", getRandomDate(1991, 2010));
            put("right_to_work_expiry_date", getRandomDate(2011, 2030));
            put("right_to_work_docs", new ArrayList<HashMap<String, Object>>() {{
                add(new HashMap<String, Object>() {{
                    put("id", 0);
                    put("file_name", "/temporary_files/1717259656_Screenshot_from_2024-06-01_18-56-57.png");
                    put("description", "yjjty");
                }});
                add(new HashMap<String, Object>() {{
                    put("id", 1);
                    put("file_name", "/temporary_files/1717259666_Screenshot_from_2024-06-01_15-47-31.png");
                    put("description", "dfhfhhtfghfgh");
                }});
            }});
        }});
        




        requestBody.put("passport_details", new HashMap<String, Object>() {{
            put("passport_number", "");
            put("passport_issue_date", "");
            put("passport_expiry_date", "");
            put("place_of_issue", "");
        }});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken) // Replace with your method to get the bearer token
                .body(requestBody)
                .when()
                .post(URL +"/api/v2.0/users") // Adjust the URL if needed
                .then()
                .extract()
                .response()
                .asString();

        
        
        return response;
    }


    public String createImmigrant(String businessOwnerToken,Integer businessId,List workLocations,List designations,List employmentStatuses,List workShifts, List departments, Integer times) throws JsonProcessingException {
        Random random = new Random();

        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate email from name
        String email = generateEmail(firstName, lastName);


        String joiningData = getRandomDate(1991, 2023);
        
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();

        
        
        requestBody.put("NI_number", "" + (Math.random() * 99999));
        requestBody.put("first_Name", firstName);
        requestBody.put("last_Name", lastName);
        requestBody.put("email", email);
        requestBody.put("date_of_birth", getRandomDate(1980, 2009));
        requestBody.put("password", "12345678#We");
        requestBody.put("password_confirmation", "12345678#We");
        requestBody.put("phone", "01777777777");
        requestBody.put("address_line_1", "Dhaka, Bangladesh");
        requestBody.put("country", "Bangladesh");
        requestBody.put("city", "Dhaka");
        requestBody.put("lat", "23.804093");
        requestBody.put("long", "90.4152376");
        requestBody.put("role", ("business_employee#" + businessId));
        requestBody.put("gender", "male");
        requestBody.put("user_id", "FAT-0002");
        requestBody.put("postcode", "1212");
        requestBody.put("work_shift_id", getRandomId(workShifts));
        requestBody.put("is_in_employee", 1);
        requestBody.put("designation_id", getRandomId(designations));
        requestBody.put("employment_status_id", getRandomId(employmentStatuses));
        requestBody.put("salary_per_annum", "5000");
        requestBody.put("work_location_id", getRandomId(workLocations));
        requestBody.put("weekly_contractual_hours", "50");
        requestBody.put("minimum_working_days_per_week", "5");
        requestBody.put("overtime_rate", "50");
        requestBody.put("recruitment_processes", Arrays.asList(
            new HashMap<String, Object>() {{
                put("recruitment_process_id", getRandomNumber(1,5));
                put("description", "jobsss");
                put("attachments", new ArrayList<>());
            }},
            new HashMap<String, Object>() {{
                put("recruitment_process_id", getRandomNumber(6,11));
                put("description", "");
                put("attachments", new ArrayList<>());
            }}
            // Add more processes here...
        ));
        requestBody.put("joining_date", getRandomDate(2001, 2023));
        requestBody.put("address_line_2", "");
        requestBody.put("departments", Arrays.asList(getRandomId(departments)));
        requestBody.put("emergency_contact_details", Arrays.asList(
            new HashMap<String, Object>() {{
                put("relationship_of_above_to_you", "Father");
                put("first_Name", FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]);
                put("last_Name", LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
                put("address_line_1", "Dhaka, Bangladesh");
                put("city", "Dhaka");
                put("country", "Bangladesh");
                put("postcode", "1212");
                put("lat", "23.804093");
                put("long", "90.4152376");
                put("mobile_tel_number", "01777777777");
            }},
            new HashMap<>() // Add more emergency contacts here...
        ));
        requestBody.put("immigration_status", "immigrant");
        requestBody.put("sponsorship_details", new HashMap<String, Object>() {{
            put("date_assigned", "");
            put("expiry_date", "");
            put("status", "");
            put("note", "");
            put("certificate_number", "");
            put("current_certificate_status", "");
            put("is_sponsorship_withdrawn", 0);
        }});
        requestBody.put("is_active_visa_details", 1);
        requestBody.put("is_active_right_to_works", 1);
        requestBody.put("visa_details", new HashMap<String, Object>() {{
            put("BRP_number",  "" + (Math.random() * 999999));
            put("visa_issue_date",  getRandomDate(1991, 2010));
            put("visa_expiry_date", getRandomDate(2011, 2030));
            put("place_of_issue", "Algeria");
         
            put("visa_docs", new ArrayList<HashMap<String, Object>>() {{
                add(new HashMap<String, Object>() {{
                    put("id", 0);
                    put("file_name", "/temporary_files/1717259656_Screenshot_from_2024-06-01_18-56-57.png");
                    put("description", "\n" + //
                                                "Where does it come from?\n" + //
                                                "\n" + //
                                                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" + //
                                                "\n" + //
                                                "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" + //
                                                "Where can I get some?\n" + //
                                                "\n" + //
                                                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" + //
                                                "\t\n" + //
                                                "\tparagraphs\n" + //
                                                "\twords\n" + //
                                                "\tbytes\n" + //
                                                "\tlists\n" + //
                                                "\t\tStart with 'Lorem\n" + //
                                                "ipsum dolor sit amet...'\n" + //
                                                "\t\n" + //
                                                "\n" + //
                                                "Translations: Can you help translate this site into a foreign language ? Please email us with details if you can help.\n" + //
                                                "There is a set of mock banners available here in three colours and in a range of standard banner sizes:\n" + //
                                                "BannersBannersBanners\n" + //
                                                "Donate: If you use this site regularly and would like to help keep the site on the Internet, please consider donating a small sum to help pay for the hosting and bandwidth bill. There is no minimum donation, any sum is appreciated - click here to donate using PayPal. Thank you for your support.\n" + //
                                                "Donate Bitcoin: 16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyF\n" + //
                                                "NodeJS Python Interface GTK Lipsum Rails .NET Groovy\n" + //
                                                "The standard Lorem Ipsum passage, used since the 1500s\n" + //
                                                "\n" + //
                                                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" + //
                                                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" + //
                                                "Section 1.10.33 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"\n" + //
                                                "");
                }});

                add(new HashMap<String, Object>() {{
                    put("id", 1);
                    put("file_name", "/temporary_files/1717259666_Screenshot_from_2024-06-01_15-47-31.png");
                    put("description", "\n" + //
                                                "Where does it come from?\n" + //
                                                "\n" + //
                                                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" + //
                                                "\n" + //
                                                "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" + //
                                                "Where can I get some?\n" + //
                                                "\n" + //
                                                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" + //
                                                "\t\n" + //
                                                "\tparagraphs\n" + //
                                                "\twords\n" + //
                                                "\tbytes\n" + //
                                                "\tlists\n" + //
                                                "\t\tStart with 'Lorem\n" + //
                                                "ipsum dolor sit amet...'\n" + //
                                                "\t\n" + //
                                                "\n" + //
                                                "Translations: Can you help translate this site into a foreign language ? Please email us with details if you can help.\n" + //
                                                "There is a set of mock banners available here in three colours and in a range of standard banner sizes:\n" + //
                                                "BannersBannersBanners\n" + //
                                                "Donate: If you use this site regularly and would like to help keep the site on the Internet, please consider donating a small sum to help pay for the hosting and bandwidth bill. There is no minimum donation, any sum is appreciated - click here to donate using PayPal. Thank you for your support.\n" + //
                                                "Donate Bitcoin: 16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyF\n" + //
                                                "NodeJS Python Interface GTK Lipsum Rails .NET Groovy\n" + //
                                                "The standard Lorem Ipsum passage, used since the 1500s\n" + //
                                                "\n" + //
                                                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" + //
                                                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" + //
                                                "Section 1.10.33 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"\n" + //
                                                "");
                }});
            }});
        }});


        requestBody.put("right_to_works", new HashMap<String, Object>() {{
            put("right_to_work_code", "" + (Math.random() * 999999));
            put("right_to_work_check_date", getRandomDate(1991, 2010));
            put("right_to_work_expiry_date", getRandomDate(2011, 2030));
            put("right_to_work_docs", new ArrayList<HashMap<String, Object>>() {{
                add(new HashMap<String, Object>() {{
                    put("id", 0);
                    put("file_name", "/temporary_files/1717259656_Screenshot_from_2024-06-01_18-56-57.png");
                    put("description", "\n" + //
                                                "Where does it come from?\n" + //
                                                "\n" + //
                                                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" + //
                                                "\n" + //
                                                "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" + //
                                                "Where can I get some?\n" + //
                                                "\n" + //
                                                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" + //
                                                "\t\n" + //
                                                "\tparagraphs\n" + //
                                                "\twords\n" + //
                                                "\tbytes\n" + //
                                                "\tlists\n" + //
                                                "\t\tStart with 'Lorem\n" + //
                                                "ipsum dolor sit amet...'\n" + //
                                                "\t\n" + //
                                                "\n" + //
                                                "Translations: Can you help translate this site into a foreign language ? Please email us with details if you can help.\n" + //
                                                "There is a set of mock banners available here in three colours and in a range of standard banner sizes:\n" + //
                                                "BannersBannersBanners\n" + //
                                                "Donate: If you use this site regularly and would like to help keep the site on the Internet, please consider donating a small sum to help pay for the hosting and bandwidth bill. There is no minimum donation, any sum is appreciated - click here to donate using PayPal. Thank you for your support.\n" + //
                                                "Donate Bitcoin: 16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyF\n" + //
                                                "NodeJS Python Interface GTK Lipsum Rails .NET Groovy\n" + //
                                                "The standard Lorem Ipsum passage, used since the 1500s\n" + //
                                                "\n" + //
                                                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" + //
                                                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" + //
                                                "Section 1.10.33 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"\n" + //
                                                "");
                }});
                add(new HashMap<String, Object>() {{
                    put("id", 1);
                    put("file_name", "/temporary_files/1717259666_Screenshot_from_2024-06-01_15-47-31.png");
                    put("description", "\n" + //
                                                "Where does it come from?\n" + //
                                                "\n" + //
                                                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" + //
                                                "\n" + //
                                                "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" + //
                                                "Where can I get some?\n" + //
                                                "\n" + //
                                                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" + //
                                                "\t\n" + //
                                                "\tparagraphs\n" + //
                                                "\twords\n" + //
                                                "\tbytes\n" + //
                                                "\tlists\n" + //
                                                "\t\tStart with 'Lorem\n" + //
                                                "ipsum dolor sit amet...'\n" + //
                                                "\t\n" + //
                                                "\n" + //
                                                "Translations: Can you help translate this site into a foreign language ? Please email us with details if you can help.\n" + //
                                                "There is a set of mock banners available here in three colours and in a range of standard banner sizes:\n" + //
                                                "BannersBannersBanners\n" + //
                                                "Donate: If you use this site regularly and would like to help keep the site on the Internet, please consider donating a small sum to help pay for the hosting and bandwidth bill. There is no minimum donation, any sum is appreciated - click here to donate using PayPal. Thank you for your support.\n" + //
                                                "Donate Bitcoin: 16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyF\n" + //
                                                "NodeJS Python Interface GTK Lipsum Rails .NET Groovy\n" + //
                                                "The standard Lorem Ipsum passage, used since the 1500s\n" + //
                                                "\n" + //
                                                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" + //
                                                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" + //
                                                "Section 1.10.33 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" + //
                                                "\n" + //
                                                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" + //
                                                "1914 translation by H. Rackham\n" + //
                                                "\n" + //
                                                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"\n" + //
                                                "");
                }});
            }});
        }});
        




        requestBody.put("passport_details", new HashMap<String, Object>() {{
            put("passport_number", "" + (Math.random() * 999999));
            put("passport_issue_date", getRandomDate(1991, 2010));
            put("passport_expiry_date", getRandomDate(2011, 2030));
            put("place_of_issue", "Algeria");
        }});

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken) // Replace with your method to get the bearer token
                .body(requestBody)
                .when()
                .post(URL +"/api/v2.0/users") // Adjust the URL if needed
                .then()
                .extract()
                .response()
                .asString();

        
        
        return response;
    }















    public String getEmployeeFormData(String businessOwnerToken) throws JsonProcessingException {
        
        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + businessOwnerToken) // Replace with your method to get the bearer token
          
                .when()
                .get(URL +"/api/v1.0/dropdown-options/employee-form") // Adjust the URL if needed
                .then()
                .extract()
                .response()
                .asString();

        System.out.println(response);
        System.out.println("response............................................................");
        return response;
    }












    @Test
    public String testRegisterUserWithBusinessAPI(String superAdminToken,String businessOwnerEmail,String businessOwnerPassword)  {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user", new HashMap<>() {{
            put("first_Name", "Rifat");
            put("last_Name", "Al-Ashwad");
            put("email", businessOwnerEmail);
            put("password", businessOwnerPassword);
            put("password_confirmation", businessOwnerPassword);
            put("phone", "01771034383");
            put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        }});

        requestBody.put("business", new HashMap<>() {{
            put("name", "ABCD business");
            put("about", "Best business in Dhaka");
            put("web_page", "https://www.facebook.com/");
            put("phone", "01771034383");
            put("email", businessOwnerEmail);
            put("phone", "01771034383");
            put("additional_information", "No Additional Information");
            put("address_line_1", "Dhaka");
            put("address_line_2", "Dinajpur");
            put("lat", "23.704263332849386");
            put("long", "90.44707059805279");
            put("country", "Bangladesh");
            put("city", "Dhaka");
            put("currency", "BDT");
            put("postcode", "Dinajpur");
            put("invoice_title", "invoice_title");
            put("footer_text", "footer_text");
            put("is_reference_manual", "1");
            put("receipt_footer", "t srt stgh st h");
            put("account_name", "thdht rth s");
            put("account_number", "fdghdgh");
            put("sort_code", "sort_coderthdrfth");
            put("pin", "1234");
//            put("type", "other");
            put("type", "property_dealer");
            put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        }});


        List<Map<String, Object>> billItems = new ArrayList<>();
        Map<String, Object> billItem1 = new HashMap<>();
        billItem1.put("bill_item_id", 12);
        billItems.add(billItem1);
        requestBody.put("bill_items", billItems);





        List<Map<String, Object>> saleItems = new ArrayList<>();
        Map<String, Object> saleItem1 = new HashMap<>();
        saleItem1.put("sale_id", "");
        saleItem1.put("item", "item");
        saleItem1.put("description", "description");
        saleItem1.put("amount", 10.1);
        Map<String, Object> saleItem2 = new HashMap<>();
        saleItem2.put("sale_id", "");
        saleItem2.put("item", "item");
        saleItem2.put("description", "description");
        saleItem2.put("amount", 10.1);
        saleItems.add(saleItem1);
        saleItems.add(saleItem2);
        requestBody.put("sale_items", saleItems);




        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + superAdminToken)
                .body(requestBody)
                .when()
                .post(URL + "/api/v1.0/auth/register-with-business")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asString();

        System.out.println(response);
        return response;

    }


    @Test
    public void testUpdateUserWithBusinessAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> userRequestBody = new HashMap<>();
        userRequestBody.put("id", 2);
        userRequestBody.put("first_Name", "Rifat");
        userRequestBody.put("last_Name", "Al-Ashwad");
        userRequestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        userRequestBody.put("password", "12345678");
        userRequestBody.put("password_confirmation", "12345678");
        userRequestBody.put("phone", "01771034383");
        userRequestBody.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");

        Map<String, Object> businessRequestBody = new HashMap<>();
        businessRequestBody.put("id", 1);
        businessRequestBody.put("name", "ABCD business");
        businessRequestBody.put("about", "Best business in Dhaka");
        businessRequestBody.put("web_page", "https://www.facebook.com/");
        businessRequestBody.put("phone", "01771034383");
        businessRequestBody.put("email", ("rifatalashwad") + Math.random() +("@gmail.com"));
        businessRequestBody.put("additional_information", "No Additional Information");
        businessRequestBody.put("address_line_1", "Dhaka");
        businessRequestBody.put("address_line_2", "Dinajpur");
        businessRequestBody.put("lat", "23.704263332849386");
        businessRequestBody.put("long", "90.44707059805279");
        businessRequestBody.put("country", "Bangladesh");
        businessRequestBody.put("city", "Dhaka");
        businessRequestBody.put("postcode", "Dinajpur");
        businessRequestBody.put("invoice_title", "invoice_title");
        businessRequestBody.put("footer_text", "footer_text");
        businessRequestBody.put("is_reference_manual", "1");
        businessRequestBody.put("receipt_footer", "receipt_footer");
        businessRequestBody.put("account_name", "thdht rth s");
        businessRequestBody.put("account_number", "fdghdgh");
        businessRequestBody.put("sort_code", "sort_coderthdrfth");
        businessRequestBody.put("pin", "1234");
        businessRequestBody.put("type", "other");
        businessRequestBody.put("logo", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        businessRequestBody.put("image", "https://images.unsplash.com/photo-1671410714831-969877d103b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user", userRequestBody);
        requestBody.put("business", businessRequestBody);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/auth/update-user-with-business") // Assuming you have the correct URL
                .then()
                .statusCode(201) // Assuming that a successful response should return a status code of 200
                .extract()
                .response()
                .asString();

        System.out.println(response);
    }
    @Test
    public void testUserToggleActiveAPI() throws JsonProcessingException {
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 2);

        // Perform the API request
        String response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getSuperadminToken())
                .body(requestBody)
                .when()
                .put(URL + "/api/v1.0/users/toggle-active")
                .then()
                .statusCode(200) // Ensure a successful response code
                .extract()
                .response().asString();



        System.out.println(response);
    }

    @Test
    public void testDeleteUserByIdAPI(String superAdminToken,Integer id) throws JsonProcessingException {
        // Prepare the request


        // Make the request
        String  response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + superAdminToken)
                .header("password", "12345678We")
                .when()
                .delete(URL + "/api/v1.0/users/" + id)
                .then()
                .statusCode(200) // Ensure a successful response code
                .extract()
                .response()
                .asString();
        System.out.println(response);

    }

}
