

package hrmApiTest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hrmApiTest.InvoiceReminderController;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static hrmApiTest.Util.getBusinessOwnerToken;
import static hrmApiTest.Util.getSuperadminToken;
import static hrmApiTest.Util.getUserToken;
import static io.restassured.RestAssured.given;


public class CommonFlow {

    private UserManagementControllerMethods  userManagementControllerMethods;

    private BusinessManagementControllerMethods  businessManagementControllerMethods;



    private ObjectMapper objectMapper;

    // mvn -Dtest=hrmApiTest.flow.CommonFlow#testFlow test


    @BeforeClass
    public void setup() throws JsonProcessingException {

        userManagementControllerMethods = new UserManagementControllerMethods();
        businessManagementControllerMethods = new BusinessManagementControllerMethods();

        objectMapper = new ObjectMapper(); // Initialize the field, not a local variable

    }


    private static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



  @Test
    public void testFlow() throws JsonProcessingException {

        String superAdminToken = getUserToken("asjadtariq@gmail.com","12345678@We");

        String businessOwnerCredentials = this.businessManagementControllerMethods.createBusiness(superAdminToken,"123456@We");

        System.out.println(businessOwnerCredentials);
        
    //      String businessOwnerToken = getUserToken("abcd@gmail.com","123456@We");
    //      System.out.println("token retrieved------");
        
    //     try {
           
    //         for (int i = 0; i < 500; i++) {
    //             waitSeconds(1);
    //             // userManagementControllerMethods.createBritishCitizen(businessOwnerToken,i);
    //             userManagementControllerMethods.createILRCitizen(businessOwnerToken, i);
    //             System.out.println("user created...........");
    //         }


    //     } catch(Exception e)  {
    //         System.out.println(e.getMessage());
    //         Assert.fail("Test failed intentionally");
    //     }

    // }


    }

  
}
