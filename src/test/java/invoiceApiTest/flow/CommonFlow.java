package invoiceApiTest.flow;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static invoiceApiTest.Util.*;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.notNullValue;

public class CommonFlow {

    private UserManagementControllerMethods  userManagementControllerMethods;
    private ObjectMapper objectMapper;
    @BeforeClass
    public void setup() throws JsonProcessingException {
        userManagementControllerMethods = new UserManagementControllerMethods();

        objectMapper = new ObjectMapper(); // Initialize the field, not a local variable

    }
    @Test
    public void BillFlow() throws JsonProcessingException {
         String superAdminToken = getSuperadminToken();
         String business =  userManagementControllerMethods.testRegisterUserWithBusinessAPI(superAdminToken);
         JsonNode jsonNodeOfBusiness = objectMapper.readTree(business);
         Integer businessId  = jsonNodeOfBusiness.get("business").get("id").asInt();
          System.out.println("business id :" + businessId);
        try {

            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,businessId);
        } catch(Exception e)  {
            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,businessId);

            Assert.fail("Test failed intentionally");
        }


    }
}
