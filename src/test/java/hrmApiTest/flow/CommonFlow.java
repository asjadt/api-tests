

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

import java.util.ArrayList;
import java.util.List;


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

    private  void extractIdsFromJson(String jsonData, List<Integer> idList, String nodeName) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            JsonNode node = jsonNode.get(nodeName);
            for (JsonNode item : node) {
                int id = item.get("id").asInt();
                idList.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//  mvn -Dtest=hrmApiTest.flow.CommonFlow#testFlow test
  @Test
    public void testFlow() throws JsonProcessingException {

        String superAdminToken = getUserToken("asjadtariq@gmail.com","12345678@We");

        String business = this.businessManagementControllerMethods.createBusiness(superAdminToken,"123456@We");

        JsonNode jsonNodeOfBusiness = objectMapper.readTree(business);

        String businessOwnerEmail = jsonNodeOfBusiness.get("user").get("email").asText();
        Integer businessId = jsonNodeOfBusiness.get("business").get("id").asInt();

        System.out.println(businessOwnerEmail);

        String businessOwnerToken = getUserToken(businessOwnerEmail,"123456@We");

        

      
        List<Integer> workLocations = new ArrayList<>();
        List<Integer> designations = new ArrayList<>();
        List<Integer> employmentStatuses = new ArrayList<>();
        List<Integer> workShifts = new ArrayList<>();
        List<Integer> roles = new ArrayList<>();
        List<Integer> departments = new ArrayList<>();

        String employeeFormData = userManagementControllerMethods.getEmployeeFormData(businessOwnerToken);
        extractIdsFromJson(employeeFormData, workLocations, "work_locations");
        extractIdsFromJson(employeeFormData, designations, "designations");
        extractIdsFromJson(employeeFormData, employmentStatuses, "employment_statuses");
        extractIdsFromJson(employeeFormData, workShifts, "work_shifts");
        extractIdsFromJson(employeeFormData, roles, "roles");
        extractIdsFromJson(employeeFormData, departments, "departments");

        
        try {
           
            for (int i = 0; i < 500; i++) {

                // waitSeconds(1);

                String brotish = userManagementControllerMethods.createBritishCitizen(businessOwnerToken,businessId,workLocations,designations,employmentStatuses,workShifts,departments,i);
                String lri =     userManagementControllerMethods.createILRCitizen(businessOwnerToken,businessId,workLocations,designations,employmentStatuses,workShifts,departments, i);
               String immgrant = userManagementControllerMethods.createImmigrant(businessOwnerToken,businessId, workLocations,designations,employmentStatuses,workShifts,departments,i);

               String sponsoredEmployee = userManagementControllerMethods.createSponsoredEmployee(businessOwnerToken,businessId, workLocations,designations,employmentStatuses,workShifts,departments,i);


                System.out.println("user created...........");
                // System.out.println(brotish);
                // System.out.println(lri);
                // System.out.println(immgrant);
            }


        } catch(Exception e)  {
            System.out.println(e.getMessage());
            Assert.fail("Test failed intentionally");
        }

    }


    

  
}
