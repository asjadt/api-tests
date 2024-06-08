

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
import static hrmApiTest.Util.getUser;
import static hrmApiTest.Util.getUserToken;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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
    private  void extractIdsFromJsonV2(String jsonData, List<Integer> idList) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
         
            for (JsonNode item : jsonNode) {
                int id = item.get("id").asInt();
                idList.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  mvn -Dtest=hrmApiTest.flow.CommonFlow#testFlowExistingUser test
  @Test
  public void testFlowExistingUser() throws JsonProcessingException {

   
      try {
        String businessOwnerEmail = "tristan.owens@example.com";
        String businessOwner = getUser(businessOwnerEmail,"123456@We");
  
            JsonNode jsonNodeOfBusinessowner = objectMapper.readTree(businessOwner);
          
        String businessOwnerToken = jsonNodeOfBusinessowner.get("data").get("token").asText();
     
  
  
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




        List<Integer> employeeIds = new ArrayList<>();
  
        String employees = userManagementControllerMethods.getEmployees(businessOwnerToken); 

    


        extractIdsFromJsonV2(employees, employeeIds);
 

     
        
 
      
       // Start from January 2001
       LocalDate startDate = LocalDate.of(2008, 1, 1);
       LocalDate endDate = LocalDate.of(2023, 12, 31);

       LocalDate currentDate = startDate;
       while (!currentDate.isAfter(endDate)) {
        waitSeconds(1);
           int year = currentDate.getYear();
           int month = currentDate.getMonthValue();
           YearMonth yearMonth = YearMonth.of(year, month);

           LocalDate firstDateOfMonth = yearMonth.atDay(1);
           LocalDate lastDateOfMonth = yearMonth.atEndOfMonth();

           // Process first and last date of the month
           System.out.println("Month: " + yearMonth.getMonth() + " " + year);


            // Format the random date to dd-MM-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String firstDateOfMonthStr = firstDateOfMonth.format(formatter);
        String lastDateOfMonthStr = lastDateOfMonth.format(formatter);
    
       String result = userManagementControllerMethods.createAttendance(businessOwnerToken,employeeIds,workLocations,firstDateOfMonthStr,lastDateOfMonthStr);

       System.out.println(result);
           // Move to the next month
           currentDate = currentDate.plusMonths(1);
       }













    
   
  

      

      } catch(Exception e)  {
          System.out.println(e.getMessage());
          Assert.fail("Test failed intentionally");
      }

  }



  //  mvn -Dtest=hrmApiTest.flow.CommonFlow#testCreateEmployeeFlow test
  @Test
    public void testCreateEmployeeFlow() throws JsonProcessingException {

    
        try {

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
           
            for (int i = 0; i < 500; i++) {

                 waitSeconds(1);

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



//  mvn -Dtest=hrmApiTest.flow.CommonFlow#testAllFlow test
  @Test
    public void testAllFlow() throws JsonProcessingException {

      
        try {
           
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

            for (int i = 0; i < 500; i++) {

                 waitSeconds(1);

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
