

package invoiceApiTest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static invoiceApiTest.Util.getBusinessOwnerToken;
import static invoiceApiTest.Util.getSuperadminToken;

import static io.restassured.RestAssured.given;


public class CommonFlow {

    private UserManagementControllerMethods  userManagementControllerMethods;
    private BillControllerMethods  billControllerMethods;

    private BillItemControllerMethods billItemControllerMethods;

    private LandlordControllerMethods landlordControllerMethods;

    private TenantControllerMethods tenantControllerMethods;

    private PropertyControllerMethods propertyControllerMethods;

    private SaleItemControllerMethods saleItemControllerMethods;
    private ObjectMapper objectMapper;



    @BeforeClass
    public void setup() throws JsonProcessingException {
        userManagementControllerMethods = new UserManagementControllerMethods();
        billControllerMethods = new BillControllerMethods();
        billItemControllerMethods = new BillItemControllerMethods();
        landlordControllerMethods = new LandlordControllerMethods();
        tenantControllerMethods = new TenantControllerMethods();
        propertyControllerMethods = new PropertyControllerMethods();
        saleItemControllerMethods = new SaleItemControllerMethods();
        objectMapper = new ObjectMapper(); // Initialize the field, not a local variable

    }
    @Test
    public void BillFlow() throws JsonProcessingException {
        String superAdminToken = getSuperadminToken();
        String businessOwnerEmail = ("rifatalashwad") + Math.random() +("@gmail.com");
        String businessOwnerPassword = "12345678";
         String business =  userManagementControllerMethods.testRegisterUserWithBusinessAPI(superAdminToken,businessOwnerEmail,businessOwnerPassword);
         JsonNode jsonNodeOfBusiness = objectMapper.readTree(business);
         Integer businessId  = jsonNodeOfBusiness.get("business").get("id").asInt();
         System.out.println("business id :" + businessId);

       String businessOwnerToken =  getBusinessOwnerToken(businessOwnerEmail,businessOwnerPassword);


        try {
            String billItem1 = billItemControllerMethods.testCreateBillItemAPI(superAdminToken);
            JsonNode jsonNodeOfBillItem1 = objectMapper.readTree(billItem1);
            Integer billItemId1 = jsonNodeOfBillItem1.get("id").asInt();

            String landlord =   landlordControllerMethods.testCreateLandlordAPI(businessOwnerToken);
            JsonNode jsonNodeOfLandlord = objectMapper.readTree(landlord);
            Integer landlordId = jsonNodeOfLandlord.get("id").asInt();

            String tenant1 =   tenantControllerMethods.testCreateTenantAPI(businessOwnerToken);
            JsonNode jsonNodeOfTenant1 = objectMapper.readTree(tenant1);
            Integer tenantId1 = jsonNodeOfTenant1.get("id").asInt();

            String property =   propertyControllerMethods.testCreatePropertyAPI(businessOwnerToken,landlordId,tenantId1);
            JsonNode jsonNodeOfProperty = objectMapper.readTree(property);
            Integer propertyId = jsonNodeOfProperty.get("id").asInt();

            String saleItem1 =   saleItemControllerMethods.testCreateSaleItemAPI(businessOwnerToken);
            JsonNode jsonNodeOfSaleItem1 = objectMapper.readTree(saleItem1);
            Integer saleItemId1 = jsonNodeOfSaleItem1.get("id").asInt();







         String bill =   billControllerMethods.testCreateBillAPI(businessOwnerToken,landlordId,propertyId,billItemId1,saleItemId1);
            JsonNode jsonNodeOfBill = objectMapper.readTree(bill);
            Integer billId = jsonNodeOfBill.get("id").asInt();
            String billGeneratedId = jsonNodeOfBill.get("generated_id").asText();
            billControllerMethods.testUpdateBillAPI(businessOwnerToken,billId,landlordId,propertyId,billItemId1,saleItemId1);



            billControllerMethods.testGetBillsAPI(businessOwnerToken);
            billControllerMethods.testGetAllBillsAPI(businessOwnerToken);

            billControllerMethods.testGetBillByIdAPI(businessOwnerToken,billGeneratedId);
            billControllerMethods.testDeleteBillByIdAPI(businessOwnerToken,billId);








            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,businessId);
            billItemControllerMethods.testDeleteBillItemByIdAPI(superAdminToken,billItemId1);
        } catch(Exception e)  {
            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,businessId);

            Assert.fail("Test failed intentionally");
        }

    }
}
