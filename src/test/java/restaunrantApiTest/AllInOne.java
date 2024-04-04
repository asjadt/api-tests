package restaunrantApiTest;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;

import static restaunrantApiTest.Util.URL;

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
import static restaunrantApiTest.Util.getSuperadminToken;


public class AllInOne {


    private DailyViewsController dailyViewsController; // Injected controller instance
    private OwnerController ownerController;

    private RestaurantController restaurantController;
    private DashboardWidgetController dashboardWidgetController;
    private EmailTemplateController emailTemplateController;
    private MenuController menuController;
    private DishController dishController;
    private VariationController variationController;
    private OrderController orderController;

    private ReviewNewController reviewNewController;

    private ObjectMapper objectMapper;
    @BeforeClass
    public void setup() {
        dailyViewsController = new DailyViewsController();
        ownerController = new OwnerController();
        restaurantController = new RestaurantController();
        dashboardWidgetController = new DashboardWidgetController();
        emailTemplateController = new EmailTemplateController();
        menuController = new MenuController();
        dishController = new DishController();
        variationController = new VariationController();
        orderController = new OrderController();
        reviewNewController = new ReviewNewController();

        objectMapper = new ObjectMapper();
    }


    private RestaurantInfo getRestaurantInfo() throws JsonProcessingException {
        String restaurantEmail = "restemail@gmail.com";
        String restaurant = ownerController.testCreateUserWithRestaurantAPIGetString(restaurantEmail);
        JsonNode jsonNodeOfRestaurant = objectMapper.readTree(restaurant);
        Integer restaurantId = jsonNodeOfRestaurant.get("data").get("restaurant").get("id").asInt();
        String restaurantOwnerToken = jsonNodeOfRestaurant.get("token").textValue();
        Integer OwnerID = jsonNodeOfRestaurant.get("data").get("restaurant").get("OwnerID").asInt();
        System.out.println("restaurantId :" +restaurantId);
        System.out.println("restaurantOwnerToken :" +restaurantOwnerToken);
        return new RestaurantInfo(restaurantId, restaurantOwnerToken,OwnerID,restaurantEmail);
    }

    @Test
    public void DailyView() throws JsonProcessingException {
        String superadminToken = getSuperadminToken();
        RestaurantInfo restaurantInfo = getRestaurantInfo();
        waitSeconds(1);
        try {

            Integer restaurantId = restaurantInfo.getRestaurantId();
            waitSeconds(1);
            String restaurantOwnerToken = restaurantInfo.getRestaurantOwnerToken();

            waitSeconds(1);




            String dailyView =  dailyViewsController.testStoreDailyViewsGetString(restaurantId,restaurantOwnerToken);
            waitSeconds(1);
            JsonNode jsonNodeOfDailyView = objectMapper.readTree(dailyView);

            Integer dailyViewId = jsonNodeOfDailyView.get("id").asInt();
            System.out.println("daily view ID: " + dailyViewId);


            dailyViewsController.testUpdateDailyViews(restaurantId,restaurantOwnerToken);
            waitSeconds(1);
            restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantInfo.getRestaurantEmail());
            waitSeconds(1);

        } catch(Exception e) {
            restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantInfo.getRestaurantEmail());

            Assert.fail("Test failed intentionally");
        }



    }

    @Test
    public void DashboardWidget() throws JsonProcessingException {

        try {
            String superadminToken = getSuperadminToken();

            waitSeconds(1);


            String dashboardWidget  = dashboardWidgetController.testCreateDashboardWidgetGetString(superadminToken);
            waitSeconds(1);
            JsonNode jsonNodeOfDailyView = objectMapper.readTree(dashboardWidget);
            Integer dashboardWidgetId = jsonNodeOfDailyView.get("id").asInt();
            System.out.println("dashboard widget id: " + dashboardWidgetId);

            dashboardWidgetController.testUpdateDashboardWidget(superadminToken,dashboardWidgetId);
            waitSeconds(1);
            dashboardWidgetController.testGetDashboardWidget(superadminToken);
            waitSeconds(1);
            dashboardWidgetController.testGetDashboardWidgetById(superadminToken,dashboardWidgetId);
            waitSeconds(1);
            dashboardWidgetController.testDeleteWidgetByIdAPI(superadminToken,dashboardWidgetId);
            waitSeconds(1);
        } catch(Exception e)  {

            Assert.fail("Test failed intentionally");
            // Code to run even after test fails
            // Place your additional code here
        }


    }


    
    @Test
    public void MenuToDishToVatiationsToOrder() throws JsonProcessingException {

        String superadminToken = getSuperadminToken();
        waitSeconds(1);
        RestaurantInfo restaurantInfo = getRestaurantInfo();
        waitSeconds(1);
        try {

            Integer restaurantId = restaurantInfo.getRestaurantId();
            waitSeconds(1);
            String restaurantOwnerToken = restaurantInfo.getRestaurantOwnerToken();
            waitSeconds(1);
            Integer restaurantOwnerID = restaurantInfo.getRestaurantOwnerID();
            waitSeconds(1);





            //   String menu =  menuController.testStoreMenuGetString(restaurantOwnerToken,restaurantId);
            String menu = menuController.testStoreMultipleMenu(restaurantId);
            waitSeconds(1);
            JsonNode jsonNodeOfMenu = objectMapper.readTree(menu);
            Integer menuId = jsonNodeOfMenu.get(0).get("id").asInt();
            String menuName = jsonNodeOfMenu.get(0).get("name").asText();
            System.out.println("Menu ID: " + menuId);

            menuController.testCheckMenu(restaurantOwnerToken,restaurantId,menuName);
            waitSeconds(1);
            menuController.testUpdateMenu(restaurantOwnerToken,menuId);
            waitSeconds(1);
            menuController.testGetMenuById(menuId);
            waitSeconds(1);
            menuController.testGetMenuById2(restaurantId,menuId);
            waitSeconds(1);
            menuController.testGetMenuByRestaurantId(restaurantId);
            waitSeconds(1);
            menuController.testGetMenuByRestaurantIdWithPagination(restaurantId);
            waitSeconds(1);
            menuController.testStoreMultipleMenu(restaurantId);
            waitSeconds(1);
            menuController.testUpdateMultipleMenu(restaurantId,menuId);
            waitSeconds(1);
            menuController.testUpdateMenu2(menuId);
            waitSeconds(1);

//    String dish =   dishController.testStoreDish(restaurantOwnerToken,menuId);
            String dish =   dishController.testStoreMultipleDishGetString(restaurantId,menuId);
            waitSeconds(1);
            JsonNode jsonNodeOfDish = objectMapper.readTree(dish);
            Integer dishId = jsonNodeOfDish.get(0).get("id").asInt();
            String dishName = jsonNodeOfDish.get(0).get("name").asText();
            System.out.println("Dish ID: " + dishId);


            String deal =    dishController.testStoreMultipleDealDish(restaurantId,menuId,dishId);
            waitSeconds(1);
            JsonNode jsonNodeOfDeal = objectMapper.readTree(deal);
            Integer dealId = jsonNodeOfDeal.get("dishes").get(0).get("id").asInt();
            String dealName = jsonNodeOfDeal.get("dishes").get(0).get("name").asText();
            System.out.println("deal ID: " + dealId);


            dishController.testGetAllDishes(restaurantId);
            waitSeconds(1);
            dishController.testGetAllDishesWithPagination(restaurantId);
            waitSeconds(1);
            dishController.testGetDishByMenuId(menuId);
            waitSeconds(1);
            dishController.testGetDishByMenuIdAndRestaurantId(restaurantId,menuId);
            waitSeconds(1);
            dishController.testGetDealDishByMenuIdAndRestaurantId(restaurantId,menuId);
            waitSeconds(1);
            dishController.testGetDishByDealId(dishId);
            waitSeconds(1);
            dishController.testGetDishByDealId2(restaurantId,dealId);
            waitSeconds(1);
            dishController.testGetDishById(dishId);
            waitSeconds(1);
            dishController.testGetDishById2(restaurantId,dishId);
            waitSeconds(1);
            dishController.testGetAllDishesWithDeals();
            waitSeconds(1);
            dishController.testUpdateMultipleDealDish(dealId,dishId);
            waitSeconds(1);
            dishController.testUpdateMultipleDish(dishId);
            waitSeconds(1);
            dishController.testUpdateDish2(dishId);
            waitSeconds(1);
            // dailyViewsController.testUpdateDailyViews(restaurantId,restaurantOwnerToken);


//        String variationType =    variationController.testStoreVariationTypeAPI(restaurantOwnerToken,restaurantId);
            String variationType =  variationController.testStoreMultipleVariationTypeAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            System.out.println("variationType:" + variationType);
            JsonNode jsonNodeOfVariationType = objectMapper.readTree(variationType);
            Integer variationTypeId = jsonNodeOfVariationType.get(0).get("id").asInt();
            String variationTypeName = jsonNodeOfVariationType.get(0).get("name").asText();
            System.out.println("variation Type ID: " + variationTypeId);

            variationController.testUpdateMultipleVariationTypeAPI(restaurantOwnerToken,variationTypeId);
            waitSeconds(1);
            variationController.testUpdateVariationTypeAPI(restaurantOwnerToken,variationTypeId);
            waitSeconds(1);
            variationController.testGetSingleVariationTypeAPI(restaurantOwnerToken,variationTypeId);
            waitSeconds(1);
            variationController.testGetAllVariationTypeWithVariationAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            variationController.testGetAllVariationByTypeIdAPI(restaurantOwnerToken,variationTypeId);
            waitSeconds(1);




//        String variation =  variationController.testStoreVariationAPI(restaurantOwnerToken,variationTypeId);
            String variation =  variationController.testStoreMultipleVariationAPI(restaurantOwnerToken,restaurantId,variationTypeId);
            waitSeconds(1);
            System.out.println("variation:" + variation);
            JsonNode jsonNodeOfVariation = objectMapper.readTree(variation);
            Integer variationId = jsonNodeOfVariation.get(0).get("id").asInt();
            String variationName = jsonNodeOfVariation.get(0).get("name").asText();
            System.out.println("variation  ID: " + variationId);

            variationController.testUpdateMultipleVariationAPI(restaurantOwnerToken,variationId);
            waitSeconds(1);
            variationController.testUpdateVariationAPI(restaurantOwnerToken,variationId);
            waitSeconds(1);
            variationController.testGetAllVariationWithDishAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            variationController.testGetAllVariationByRestaurantIdAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);




//        String dishVariation =  variationController.testStoreDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
            String dishVariation =  variationController.testStoreMultipleDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
            waitSeconds(1);
            System.out.println("dish variation:" + dishVariation);
            JsonNode jsonNodeOfDishVariation = objectMapper.readTree(dishVariation);
            Integer dishVariationId = jsonNodeOfDishVariation.get(0).get("id").asInt();

            System.out.println("dish variation  ID: " + dishVariationId);

            variationController.testGetAllDishVariationAPI(restaurantOwnerToken,dishId);
            waitSeconds(1);
            variationController.testGetAllDishVariationAPI(restaurantOwnerToken,dishId);
            waitSeconds(1);
            variationController.testUpdateDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
            waitSeconds(1);
            variationController.testUpdateMultipleDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
            waitSeconds(1);



            String order =  orderController.testStoreOrderAPI( restaurantOwnerToken,  restaurantId,  restaurantOwnerID,
                    dishId, variationId, dealId);
            waitSeconds(1);
            System.out.println("order:" + order);
            JsonNode jsonNodeOfOrder = objectMapper.readTree(order);
            Integer orderId = jsonNodeOfOrder.get("data").get("id").asInt();

            orderController.testStoreByUserAPI(restaurantOwnerToken, restaurantId, restaurantOwnerID, variationId, dealId, dishId);
            waitSeconds(1);
            orderController.testOrderCompleteAPI(restaurantOwnerToken,orderId);
            waitSeconds(1);
            orderController.testUpdateStatusAPI(restaurantOwnerToken,orderId);
            waitSeconds(1);
            orderController.testEditOrderAPI(restaurantOwnerToken,orderId, variationId, dishId, dealId);
            waitSeconds(1);
            orderController.testGetOrderByIdAPI(restaurantOwnerToken,orderId);
            waitSeconds(1);
            orderController.testGetOrderById2API(restaurantOwnerToken,orderId,restaurantId);
            waitSeconds(1);
            orderController.testGetOrderByCustomerIdAPI(restaurantOwnerToken,restaurantOwnerID);
            waitSeconds(1);
            orderController.testGetTodaysOrderByStatusAPI(restaurantOwnerToken);
            waitSeconds(1);
            orderController.testGetAllOrderAPI(restaurantOwnerToken);
            waitSeconds(1);
            orderController.testGetAllOrderTodayAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetAllOrderEveryDayAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetOrderByTypeAPI(restaurantOwnerToken);
            waitSeconds(1);
            orderController.testGetOrderByType2API(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetAllPendingOrderAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetAllPendingOrderWithPaginationAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetAllAutoPrintOrderAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetDailyOrderReportAPI(restaurantOwnerToken);
            waitSeconds(1);
            orderController.testGetOrderReportAPI(restaurantOwnerToken);
            waitSeconds(1);
            orderController.testGetOrderReportByRestaurantIdAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetOrderReportByRestaurantId2API(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            orderController.testGetOrderByUserAPI(restaurantOwnerToken);
            waitSeconds(1);




            orderController.testDeleteOrderAPI(restaurantOwnerToken,orderId);
            waitSeconds(1);
            variationController.testDeleteDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
            waitSeconds(1);
            variationController.testDeleteVariationTypeAPI(restaurantOwnerToken,variationTypeId);
            waitSeconds(1);
            dishController.testDeleteDishAPI(restaurantOwnerToken,dealId);
            waitSeconds(1);
            dishController.testDeleteDishAPI(restaurantOwnerToken,dishId);
            waitSeconds(1);
            menuController.testDeleteMenuAPI(restaurantOwnerToken,menuId);
            waitSeconds(1);
            restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantInfo.getRestaurantEmail());
            waitSeconds(1);

        } catch(Exception e)  {
            System.out.println(e);
            restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantInfo.getRestaurantEmail());

            Assert.fail("Test failed intentionally");
        }


       }

    @Test
    public void ReviewCombined() throws JsonProcessingException {
        String superadminToken = getSuperadminToken();
        waitSeconds(1);
        RestaurantInfo restaurantInfo = getRestaurantInfo();
        waitSeconds(1);
        try {

            Integer restaurantId = restaurantInfo.getRestaurantId();
            waitSeconds(1);
            String restaurantOwnerToken = restaurantInfo.getRestaurantOwnerToken();
            waitSeconds(1);


            Integer   starId = 1;





            String question =   reviewNewController.testStoreQuestionAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            JsonNode jsonNodeOfQuestion = objectMapper.readTree(question);
            Integer questionId = jsonNodeOfQuestion.get("id").asInt();
            System.out.println("question ID: " + questionId);


            reviewNewController.testUpdateQuestionAPI(restaurantOwnerToken,questionId);
            waitSeconds(1);
            reviewNewController.testUpdateQuestionActiveStateAPI(restaurantOwnerToken,questionId);
            waitSeconds(1);
            reviewNewController.testGetQuestionAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.getQuestionAllUnauthorized(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetAllQuestionsAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetAllQuestionsReportAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetQuestionByIdAPI(restaurantOwnerToken,questionId);
            waitSeconds(1);
            reviewNewController.testGetQuestionById2API(restaurantOwnerToken,questionId);
            waitSeconds(1);


            String tag =   reviewNewController.testStoreMultipleTagsAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            JsonNode jsonNodeOfTag = objectMapper.readTree(tag);
            Integer tagId = jsonNodeOfTag.get("data").get(0).get("id").asInt();
            System.out.println("question ID: " + tagId);

//        reviewNewController.testStoreTagAPI(restaurantOwnerToken,restaurantId);
            reviewNewController.testUpdateTagAPI(restaurantOwnerToken,tagId);
            waitSeconds(1);
            reviewNewController.testGetTagsAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetTagByIdAPI(restaurantOwnerToken,tagId);
            waitSeconds(1);
            reviewNewController.testGetTagById2API(restaurantOwnerToken,restaurantId,tagId);
            waitSeconds(1);




            reviewNewController.testStoreOwnerQuestionAPI(restaurantOwnerToken,questionId,tagId,starId);
            waitSeconds(1);
            reviewNewController.testUpdateOwnerQuestionAPI(restaurantOwnerToken,questionId,tagId,starId);
            waitSeconds(1);



            reviewNewController.testStoreReviewAPI( restaurantOwnerToken, restaurantId, questionId, tagId, starId);
            waitSeconds(1);
            reviewNewController.testStoreReviewByGuestAPI( restaurantOwnerToken, restaurantId, questionId, tagId, starId);
            waitSeconds(1);
            reviewNewController.testGetReviewValuesAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);

            reviewNewController.testFilterReviewAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetReviewByRestaurantIdAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetCustomerReviewAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetQuestionAllReportGuestAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
            reviewNewController.testGetQuestionAllReportUnauthorizedAPI(restaurantOwnerToken,restaurantId);
            waitSeconds(1);
//        reviewNewController.testGetQuestionAllReportGuestUnauthorizedAPI(restaurantOwnerToken,restaurantId);
//       reviewNewController.testGetQuestionAllReportGuestQuantumAPI(restaurantOwnerToken,restaurantId);
//        reviewNewController.testGetQuestionAllReportQuantumAPI(restaurantOwnerToken,restaurantId);








            reviewNewController.testDeleteTagByIdAPI(restaurantOwnerToken,tagId);
            waitSeconds(1);
            reviewNewController.testDeleteQuestionByIdAPI(restaurantOwnerToken,questionId);
            waitSeconds(1);

        } catch(Exception e)  {
            restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantInfo.getRestaurantEmail());

            Assert.fail("Test failed intentionally");
        }


       }





    private static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // @Test
    // public void EmailTemplate() throws JsonProcessingException {
    //     String superadminToken = getSuperadminToken();




    //     String emailTemplate  = emailTemplateController.testCreateEmailTemplateGetString(superadminToken);
    //     JsonNode jsonNodeOfDailyView = objectMapper.readTree(emailTemplate);
    //     Integer emailTemplateId = jsonNodeOfDailyView.get("id").asInt();
    //     System.out.println("dashboard widget id: " + emailTemplateId);

    //     // dashboardWidgetController.testUpdateDashboardWidget(superadminToken,dashboardWidgetId);

    //     // dashboardWidgetController.testGetDashboardWidget(superadminToken);

    //     // dashboardWidgetController.testGetDashboardWidgetById(superadminToken,dashboardWidgetId);

    //     // dashboardWidgetController.testDeleteWidgetByIdAPI(superadminToken,dashboardWidgetId);

    // }
    



//    @Test
//    public void loginWithValidCredentialsShouldReturn200() {
//         String success =    given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body("{\"email\": \"superman@g.c\", \"password\": \"12345678\"}")
//                .when()
//                .post(URL + "/api/auth")
//                .then()
//                .statusCode(200)
//                .extract()
//                .response()
//                .asString();
//        System.out.println(" response: " + success);
//    }
}




// remainingsssssssssssssssss
// Deal is under Dish enitity
// Dish is under Menu Entity
// DishVariation is under Dish Entity 
