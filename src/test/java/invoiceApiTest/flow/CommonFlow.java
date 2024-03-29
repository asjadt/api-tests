

package invoiceApiTest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import invoiceApiTest.InvoiceReminderController;
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

    private InvoiceControllerMethods invoiceControllerMethods;

    private RepairControllerMethods repairControllerMethods;

    private RepairCategoryControllerMethods repairCategoryControllerMethods;

    private InvoiceReminderControllerMethods invoiceReminderControllerMethods;

    private InvoicePaymentControllerMethods invoicePaymentControllerMethods;


    private ReceiptControllerMethods receiptControllerMethods;

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
        invoiceControllerMethods = new InvoiceControllerMethods();
        repairControllerMethods = new RepairControllerMethods();
        repairCategoryControllerMethods = new RepairCategoryControllerMethods();
        invoiceReminderControllerMethods = new InvoiceReminderControllerMethods();
        invoicePaymentControllerMethods = new InvoicePaymentControllerMethods();
        receiptControllerMethods = new ReceiptControllerMethods();
        objectMapper = new ObjectMapper(); // Initialize the field, not a local variable

    }

//    bill flow
//1 admin login
//2 business register
//3 bill create by admin
//4 repair category create by admin
//5 landlord create by business owner
//6 tenant create by business owner
//7 property create by business owner
//8 sale item create by business owner
//9 repair item create by business owner
//10 bill create, update, get, delete by business owner
//11 business delete by admin
//12 bill delete by admin
//13 repair category delete by admin
    @Test
    public void BillFlow() throws JsonProcessingException {
        String superAdminToken = getSuperadminToken();
        String businessOwnerEmail = ("rifatalashwad") + Math.random() +("@gmail.com");
        String businessOwnerPassword = "12345678";
         String business =  userManagementControllerMethods.testRegisterUserWithBusinessAPI(superAdminToken,businessOwnerEmail,businessOwnerPassword);
         JsonNode jsonNodeOfBusiness = objectMapper.readTree(business);
         Integer businessId  = jsonNodeOfBusiness.get("business").get("id").asInt();
        Integer userId  = jsonNodeOfBusiness.get("user").get("id").asInt();
         System.out.println("business id :" + businessId);
        System.out.println("user id :" + userId);

       String businessOwnerToken =  getBusinessOwnerToken(businessOwnerEmail,businessOwnerPassword);


        try {

            String billItem1 = billItemControllerMethods.testCreateBillItemAPI(superAdminToken);
            JsonNode jsonNodeOfBillItem1 = objectMapper.readTree(billItem1);
            Integer billItemId1 = jsonNodeOfBillItem1.get("id").asInt();

            String repairCategory = repairCategoryControllerMethods.testCreateRepairCategoryAPI(superAdminToken);
            JsonNode jsonNodeOfRepairCategory = objectMapper.readTree(repairCategory);
            Integer repairCategoryId = jsonNodeOfRepairCategory.get("id").asInt();


            String landlord =   landlordControllerMethods.testCreateLandlordAPI(businessOwnerToken);
            JsonNode jsonNodeOfLandlord = objectMapper.readTree(landlord);
            Integer landlordId = jsonNodeOfLandlord.get("id").asInt();

            String tenant1 =   tenantControllerMethods.testCreateTenantAPI(businessOwnerToken);
            JsonNode jsonNodeOfTenant1 = objectMapper.readTree(tenant1);
            Integer tenantId1 = jsonNodeOfTenant1.get("id").asInt();

            String property =   propertyControllerMethods.testCreatePropertyAPI(businessOwnerToken,landlordId,tenantId1);
            JsonNode jsonNodeOfProperty = objectMapper.readTree(property);
            Integer propertyId = jsonNodeOfProperty.get("id").asInt();


            String repairItem1 =   repairControllerMethods.testCreateRepairAPI(businessOwnerToken,repairCategoryId,propertyId);
            JsonNode jsonNodeOfRepairItem1 = objectMapper.readTree(repairItem1);
            Integer repairItemId1 = jsonNodeOfRepairItem1.get("id").asInt();


            String saleItem1 =   saleItemControllerMethods.testCreateSaleItemAPI(businessOwnerToken);
            JsonNode jsonNodeOfSaleItem1 = objectMapper.readTree(saleItem1);
            Integer saleItemId1 = jsonNodeOfSaleItem1.get("id").asInt();









         String bill =   billControllerMethods.testCreateBillAPI(businessOwnerToken,landlordId,propertyId,billItemId1,repairItemId1,saleItemId1);
            JsonNode jsonNodeOfBill = objectMapper.readTree(bill);
            Integer billId = jsonNodeOfBill.get("id").asInt();
            String billGeneratedId = jsonNodeOfBill.get("generated_id").asText();
            billControllerMethods.testUpdateBillAPI(businessOwnerToken,billId,landlordId,propertyId,billItemId1,repairItemId1,saleItemId1);



            billControllerMethods.testGetBillsAPI(businessOwnerToken);
            billControllerMethods.testGetAllBillsAPI(businessOwnerToken);

            billControllerMethods.testGetBillByIdAPI(businessOwnerToken,billGeneratedId);
            billControllerMethods.testDeleteBillByIdAPI(businessOwnerToken,billId);








            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);
            billItemControllerMethods.testDeleteBillItemByIdAPI(superAdminToken,billItemId1);
            repairCategoryControllerMethods.testDeleteRepairCategoryByIdAPI(superAdminToken,repairCategoryId);
        } catch(Exception e)  {
            System.out.println("wwwwwww");
            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);

            Assert.fail("Test failed intentionally");
            System.out.println("wwwwwww");
            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);
        }

    }
//        invoice flow
//1 admin login
//2 business register
//3 repair category create by admin
//4 landlord create by business owner
//5 tenant create by business owner
//6 property create by business owner
//7 sale item create by business owner
//8 repair item create by business owner
//9 invoice create, update, get,get by id,reference, delete by business owner
//10 invoice reminder create, update, get,get by id, delete by business owner
//11 invoice payment create, update, get,get by id, delete by business owner
//12 business delete by admin
//13 repair category delete by admin
    @Test
    public void InvoiceFlow() throws JsonProcessingException {
        String superAdminToken = getSuperadminToken();
        String businessOwnerEmail = ("rifatalashwad") + Math.random() +("@gmail.com");
        String businessOwnerPassword = "12345678";
        String business =  userManagementControllerMethods.testRegisterUserWithBusinessAPI(superAdminToken,businessOwnerEmail,businessOwnerPassword);
        JsonNode jsonNodeOfBusiness = objectMapper.readTree(business);
        Integer businessId  = jsonNodeOfBusiness.get("business").get("id").asInt();
        Integer userId  = jsonNodeOfBusiness.get("user").get("id").asInt();
        System.out.println("business id :" + businessId);

        String businessOwnerToken =  getBusinessOwnerToken(businessOwnerEmail,businessOwnerPassword);


        try {
//            String billItem1 = billItemControllerMethods.testCreateBillItemAPI(superAdminToken);
//            JsonNode jsonNodeOfBillItem1 = objectMapper.readTree(billItem1);
//            Integer billItemId1 = jsonNodeOfBillItem1.get("id").asInt();

            String repairCategory = repairCategoryControllerMethods.testCreateRepairCategoryAPI(superAdminToken);
            JsonNode jsonNodeOfRepairCategory = objectMapper.readTree(repairCategory);
            Integer repairCategoryId = jsonNodeOfRepairCategory.get("id").asInt();

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



            String repairItem1 =   repairControllerMethods.testCreateRepairAPI(businessOwnerToken,repairCategoryId,propertyId);
            JsonNode jsonNodeOfRepairItem1 = objectMapper.readTree(repairItem1);
            Integer repairItemId1 = jsonNodeOfRepairItem1.get("id").asInt();



            String invoice =   invoiceControllerMethods.testCreateInvoiceAPI(businessOwnerToken,landlordId,propertyId,repairItemId1,saleItemId1);
            JsonNode jsonNodeOfInvoice = objectMapper.readTree(invoice);
            Integer invoiceId = jsonNodeOfInvoice.get("id").asInt();
            String invoiceGeneratedId = jsonNodeOfInvoice.get("generated_id").asText();
            String invoiceReference = jsonNodeOfInvoice.get("invoice_reference").asText();
            invoiceControllerMethods.testUpdateInvoiceAPI(businessOwnerToken,invoiceId,invoiceReference,landlordId,propertyId,repairItemId1,saleItemId1);
            invoiceControllerMethods.testUpdateInvoiceStatusAPI(businessOwnerToken,invoiceId);
            invoiceControllerMethods.testMarkInvoiceSendAPI(businessOwnerToken,invoiceId);
            invoiceControllerMethods.testSendInvoiceAPI(businessOwnerToken,invoiceId);
            invoiceControllerMethods.testGetInvoicesAPI(businessOwnerToken);
            invoiceControllerMethods.testGetInvoicesAPI(businessOwnerToken);
            invoiceControllerMethods.testGetInvoiceById(businessOwnerToken,invoiceGeneratedId);
            invoiceControllerMethods.testGetInvoiceByReferenceAPI(businessOwnerToken,invoiceReference);


            String invoiceReminder =   invoiceReminderControllerMethods.testCreateInvoiceReminderNumberDateConvertAPI(businessOwnerToken,invoiceId);
            JsonNode jsonNodeOfInvoiceReminder = objectMapper.readTree(invoiceReminder);
            Integer invoiceReminderId = jsonNodeOfInvoiceReminder.get("id").asInt();
            invoiceReminderControllerMethods.testUpdateInvoiceReminderAPI(businessOwnerToken,invoiceReminderId);
            invoiceReminderControllerMethods.testGetInvoiceRemindersAPI(businessOwnerToken);
            invoiceReminderControllerMethods.testGetInvoiceReminderByIdAPI(businessOwnerToken,invoiceReminderId);
            invoiceReminderControllerMethods.testDeleteInvoiceReminderByIdAPI(businessOwnerToken,invoiceReminderId);


            String invoicePayment =   invoicePaymentControllerMethods.testCreateInvoicePaymentAPI(businessOwnerToken,invoiceId);
            JsonNode jsonNodeOfInvoicePayment = objectMapper.readTree(invoicePayment);
            Integer invoicePaymentId = jsonNodeOfInvoicePayment.get("id").asInt();
            String invoicePaymentGeneratedId = jsonNodeOfInvoicePayment.get("generated_id").asText();
            invoicePaymentControllerMethods.testUpdateInvoicePaymentAPI(businessOwnerToken,invoiceId,invoicePaymentId);
            invoicePaymentControllerMethods.testGetInvoicePaymentsAPI(businessOwnerToken);
            invoicePaymentControllerMethods.testGetInvoicePaymentByIdV2API(businessOwnerToken,invoicePaymentGeneratedId);
            invoicePaymentControllerMethods.testDeleteInvoicePaymentByIdV2API(businessOwnerToken,invoicePaymentId);




            invoiceControllerMethods.testDeleteInvoiceById(businessOwnerToken,invoiceId);





//            String bill =   billControllerMethods.testCreateBillAPI(businessOwnerToken,landlordId,propertyId,billItemId1,repairItemId1,saleItemId1);
//            JsonNode jsonNodeOfBill = objectMapper.readTree(bill);
//            Integer billId = jsonNodeOfBill.get("id").asInt();
//            String billGeneratedId = jsonNodeOfBill.get("generated_id").asText();
//            billControllerMethods.testUpdateBillAPI(businessOwnerToken,billId,landlordId,propertyId,billItemId1,repairItemId1,saleItemId1);
//
//
//
//            billControllerMethods.testGetBillsAPI(businessOwnerToken);
//            billControllerMethods.testGetAllBillsAPI(businessOwnerToken);
//
//            billControllerMethods.testGetBillByIdAPI(businessOwnerToken,billGeneratedId);
//            billControllerMethods.testDeleteBillByIdAPI(businessOwnerToken,billId);








            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);
//            billItemControllerMethods.testDeleteBillItemByIdAPI(superAdminToken,billItemId1);
            repairCategoryControllerMethods.testDeleteRepairCategoryByIdAPI(superAdminToken,repairCategoryId);
        } catch(Exception e)  {
           userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);

            Assert.fail("Test failed intentionally");
        }

    }
//            receipt flow
//1 admin login
//2 business register
//3 repair category create by admin
//4 landlord create by business owner
//5 tenant create by business owner
//6 property create by business owner
//7 sale item create by business owner
//8 repair item create by business owner
//9 receipt create by business owner
//10 invoice create, update, get,get by id,reference, delete by business owner
//11 invoice reminder create, update, get,get by id, delete by business owner
//12 invoice payment create, update, get,get by id, delete by business owner
//13 business delete by admin
//14 repair category delete by admin
    @Test
    public void ReceiptFlow() throws JsonProcessingException {
        String superAdminToken = getSuperadminToken();
        String businessOwnerEmail = ("rifatalashwad") + Math.random() +("@gmail.com");
        String businessOwnerPassword = "12345678";
        String business =  userManagementControllerMethods.testRegisterUserWithBusinessAPI(superAdminToken,businessOwnerEmail,businessOwnerPassword);
        JsonNode jsonNodeOfBusiness = objectMapper.readTree(business);
        Integer businessId  = jsonNodeOfBusiness.get("business").get("id").asInt();
        Integer userId  = jsonNodeOfBusiness.get("user").get("id").asInt();
        System.out.println("business id :" + businessId);

        String businessOwnerToken =  getBusinessOwnerToken(businessOwnerEmail,businessOwnerPassword);


        try {
//            String billItem1 = billItemControllerMethods.testCreateBillItemAPI(superAdminToken);
//            JsonNode jsonNodeOfBillItem1 = objectMapper.readTree(billItem1);
//            Integer billItemId1 = jsonNodeOfBillItem1.get("id").asInt();

            String repairCategory = repairCategoryControllerMethods.testCreateRepairCategoryAPI(superAdminToken);
            JsonNode jsonNodeOfRepairCategory = objectMapper.readTree(repairCategory);
            Integer repairCategoryId = jsonNodeOfRepairCategory.get("id").asInt();

            String landlord =   landlordControllerMethods.testCreateLandlordAPI(businessOwnerToken);
            JsonNode jsonNodeOfLandlord = objectMapper.readTree(landlord);
            Integer landlordId = jsonNodeOfLandlord.get("id").asInt();

            String tenant1 =   tenantControllerMethods.testCreateTenantAPI(businessOwnerToken);
            JsonNode jsonNodeOfTenant1 = objectMapper.readTree(tenant1);
            Integer tenantId1 = jsonNodeOfTenant1.get("id").asInt();

            String property =   propertyControllerMethods.testCreatePropertyAPI(businessOwnerToken,landlordId,tenantId1);
            JsonNode jsonNodeOfProperty = objectMapper.readTree(property);
            Integer propertyId = jsonNodeOfProperty.get("id").asInt();
            String propertyAddress = jsonNodeOfProperty.get("address").asText();

            String saleItem1 =   saleItemControllerMethods.testCreateSaleItemAPI(businessOwnerToken);
            JsonNode jsonNodeOfSaleItem1 = objectMapper.readTree(saleItem1);
            Integer saleItemId1 = jsonNodeOfSaleItem1.get("id").asInt();



            String repairItem1 =   repairControllerMethods.testCreateRepairAPI(businessOwnerToken,repairCategoryId,propertyId);
            JsonNode jsonNodeOfRepairItem1 = objectMapper.readTree(repairItem1);
            Integer repairItemId1 = jsonNodeOfRepairItem1.get("id").asInt();



            String receipt =   receiptControllerMethods.testCreateReceiptAPI(businessOwnerToken,tenantId1,propertyAddress,saleItemId1);
            JsonNode jsonNodeOfReceipt = objectMapper.readTree(receipt);
            Integer receiptId = jsonNodeOfReceipt.get("id").asInt();
            Integer invoiceId = jsonNodeOfReceipt.get("invoice_id").asInt();

//            String invoice =  invoiceControllerMethods.testUpdateInvoiceStatusAPI(businessOwnerToken,invoiceId);
            String invoice =  invoiceControllerMethods.testMarkInvoiceSendAPI(businessOwnerToken,invoiceId);
            JsonNode jsonNodeOfInvoice = objectMapper.readTree(invoice);
            String invoiceGeneratedId = jsonNodeOfInvoice.get("generated_id").asText();
            String invoiceReference = jsonNodeOfInvoice.get("invoice_reference").asText();

            invoiceControllerMethods.testUpdateInvoiceAPI(businessOwnerToken,invoiceId,invoiceReference,landlordId,propertyId,repairItemId1,saleItemId1);


            invoiceControllerMethods.testSendInvoiceAPI(businessOwnerToken,invoiceId);
            invoiceControllerMethods.testGetInvoicesAPI(businessOwnerToken);
            invoiceControllerMethods.testGetInvoicesAPI(businessOwnerToken);
            invoiceControllerMethods.testGetInvoiceById(businessOwnerToken,invoiceGeneratedId);
            invoiceControllerMethods.testGetInvoiceByReferenceAPI(businessOwnerToken,invoiceReference);


            String invoiceReminder =   invoiceReminderControllerMethods.testCreateInvoiceReminderNumberDateConvertAPI(businessOwnerToken,invoiceId);
            JsonNode jsonNodeOfInvoiceReminder = objectMapper.readTree(invoiceReminder);
            Integer invoiceReminderId = jsonNodeOfInvoiceReminder.get("id").asInt();
            invoiceReminderControllerMethods.testUpdateInvoiceReminderAPI(businessOwnerToken,invoiceReminderId);
            invoiceReminderControllerMethods.testGetInvoiceRemindersAPI(businessOwnerToken);
            invoiceReminderControllerMethods.testGetInvoiceReminderByIdAPI(businessOwnerToken,invoiceReminderId);
            invoiceReminderControllerMethods.testDeleteInvoiceReminderByIdAPI(businessOwnerToken,invoiceReminderId);


            String invoicePayment =   invoicePaymentControllerMethods.testCreateInvoicePaymentAPI(businessOwnerToken,invoiceId);
            JsonNode jsonNodeOfInvoicePayment = objectMapper.readTree(invoicePayment);
            Integer invoicePaymentId = jsonNodeOfInvoicePayment.get("id").asInt();
            String invoicePaymentGeneratedId = jsonNodeOfInvoicePayment.get("generated_id").asText();
            invoicePaymentControllerMethods.testUpdateInvoicePaymentAPI(businessOwnerToken,invoiceId,invoicePaymentId);
            invoicePaymentControllerMethods.testGetInvoicePaymentsAPI(businessOwnerToken);
            invoicePaymentControllerMethods.testGetInvoicePaymentByIdV2API(businessOwnerToken,invoicePaymentGeneratedId);
            invoicePaymentControllerMethods.testDeleteInvoicePaymentByIdV2API(businessOwnerToken,invoicePaymentId);




            invoiceControllerMethods.testDeleteInvoiceById(businessOwnerToken,invoiceId);





//            String bill =   billControllerMethods.testCreateBillAPI(businessOwnerToken,landlordId,propertyId,billItemId1,repairItemId1,saleItemId1);
//            JsonNode jsonNodeOfBill = objectMapper.readTree(bill);
//            Integer billId = jsonNodeOfBill.get("id").asInt();
//            String billGeneratedId = jsonNodeOfBill.get("generated_id").asText();
//            billControllerMethods.testUpdateBillAPI(businessOwnerToken,billId,landlordId,propertyId,billItemId1,repairItemId1,saleItemId1);
//
//
//
//            billControllerMethods.testGetBillsAPI(businessOwnerToken);
//            billControllerMethods.testGetAllBillsAPI(businessOwnerToken);
//
//            billControllerMethods.testGetBillByIdAPI(businessOwnerToken,billGeneratedId);
//            billControllerMethods.testDeleteBillByIdAPI(businessOwnerToken,billId);








            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);
//            billItemControllerMethods.testDeleteBillItemByIdAPI(superAdminToken,billItemId1);
            repairCategoryControllerMethods.testDeleteRepairCategoryByIdAPI(superAdminToken,repairCategoryId);
        } catch(Exception e)  {
            userManagementControllerMethods.testDeleteUserByIdAPI(superAdminToken,userId);

            Assert.fail("Test failed intentionally");
        }

    }
}
