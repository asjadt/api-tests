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

        objectMapper = new ObjectMapper();
    }


    private RestaurantInfo getRestaurantInfo() throws JsonProcessingException {
        String restaurant = ownerController.testCreateUserWithRestaurantAPIGetString();
        JsonNode jsonNodeOfRestaurant = objectMapper.readTree(restaurant);
        Integer restaurantId = jsonNodeOfRestaurant.get("data").get("restaurant").get("id").asInt();
        String restaurantOwnerToken = jsonNodeOfRestaurant.get("token").textValue();
        Integer OwnerID = jsonNodeOfRestaurant.get("data").get("restaurant").get("OwnerID").asInt();
        System.out.println("restaurantId :" +restaurantId);
        System.out.println("restaurantOwnerToken :" +restaurantOwnerToken);
        return new RestaurantInfo(restaurantId, restaurantOwnerToken,OwnerID);
    }

    @Test
    public void DailyView() throws JsonProcessingException {
        String superadminToken = getSuperadminToken();
        RestaurantInfo restaurantInfo = getRestaurantInfo();
        Integer restaurantId = restaurantInfo.getRestaurantId();
        String restaurantOwnerToken = restaurantInfo.getRestaurantOwnerToken();






      String dailyView =  dailyViewsController.testStoreDailyViewsGetString(restaurantId,restaurantOwnerToken);
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(dailyView);
        Integer dailyViewId = jsonNodeOfDailyView.get("id").asInt();
        System.out.println("daily view ID: " + dailyViewId);


        dailyViewsController.testUpdateDailyViews(restaurantId,restaurantOwnerToken);







        restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantId);
    }

    @Test
    public void DashboardWidget() throws JsonProcessingException {
        String superadminToken = getSuperadminToken();




        String dashboardWidget  = dashboardWidgetController.testCreateDashboardWidgetGetString(superadminToken);
        JsonNode jsonNodeOfDailyView = objectMapper.readTree(dashboardWidget);
        Integer dashboardWidgetId = jsonNodeOfDailyView.get("id").asInt();
        System.out.println("dashboard widget id: " + dashboardWidgetId);

        dashboardWidgetController.testUpdateDashboardWidget(superadminToken,dashboardWidgetId);

        dashboardWidgetController.testGetDashboardWidget(superadminToken);

        dashboardWidgetController.testGetDashboardWidgetById(superadminToken,dashboardWidgetId);

        dashboardWidgetController.testDeleteWidgetByIdAPI(superadminToken,dashboardWidgetId);

    }


    
    @Test
    public void MenuToDishToVatiationsToOrder() throws JsonProcessingException {
        String superadminToken = getSuperadminToken();
        RestaurantInfo restaurantInfo = getRestaurantInfo();
        Integer restaurantId = restaurantInfo.getRestaurantId();
        String restaurantOwnerToken = restaurantInfo.getRestaurantOwnerToken();
        Integer restaurantOwnerID = restaurantInfo.getRestaurantOwnerID();





    //   String menu =  menuController.testStoreMenuGetString(restaurantOwnerToken,restaurantId);
       String menu = menuController.testStoreMultipleMenu(restaurantId);
        JsonNode jsonNodeOfMenu = objectMapper.readTree(menu);
        Integer menuId = jsonNodeOfMenu.get(0).get("id").asInt();
        String menuName = jsonNodeOfMenu.get(0).get("name").asText();
        System.out.println("Menu ID: " + menuId);

        menuController.testCheckMenu(restaurantOwnerToken,restaurantId,menuName);
        menuController.testUpdateMenu(restaurantOwnerToken,menuId);
        menuController.testGetMenuById(menuId);
        menuController.testGetMenuById2(restaurantId,menuId);
        menuController.testGetMenuByRestaurantId(restaurantId);
        menuController.testGetMenuByRestaurantIdWithPagination(restaurantId);
        menuController.testStoreMultipleMenu(restaurantId);
        menuController.testUpdateMultipleMenu(restaurantId,menuId);
        menuController.testUpdateMenu2(menuId);
        
//    String dish =   dishController.testStoreDish(restaurantOwnerToken,menuId);
        String dish =   dishController.testStoreMultipleDishGetString(restaurantId,menuId);
        JsonNode jsonNodeOfDish = objectMapper.readTree(dish);
        Integer dishId = jsonNodeOfDish.get(0).get("id").asInt();
        String dishName = jsonNodeOfDish.get(0).get("name").asText();
        System.out.println("Dish ID: " + dishId);


      String deal =    dishController.testStoreMultipleDealDish(restaurantId,menuId,dishId);
        JsonNode jsonNodeOfDeal = objectMapper.readTree(deal);
        Integer dealId = jsonNodeOfDeal.get("dishes").get(0).get("id").asInt();
        String dealName = jsonNodeOfDeal.get("dishes").get(0).get("name").asText();
        System.out.println("deal ID: " + dealId);


        dishController.testGetAllDishes(restaurantId);
        dishController.testGetAllDishesWithPagination(restaurantId);
        dishController.testGetDishByMenuId(menuId);
        dishController.testGetDishByMenuIdAndRestaurantId(restaurantId,menuId);
        dishController.testGetDealDishByMenuIdAndRestaurantId(restaurantId,menuId);
        dishController.testGetDishByDealId(dishId);
        dishController.testGetDishByDealId2(restaurantId,dealId);
        dishController.testGetDishById(dishId);
        dishController.testGetDishById2(restaurantId,dishId);
        dishController.testGetAllDishesWithDeals();
        dishController.testUpdateMultipleDealDish(dealId,dishId);
        dishController.testUpdateMultipleDish(dishId);
        dishController.testUpdateDish2(dishId);
        // dailyViewsController.testUpdateDailyViews(restaurantId,restaurantOwnerToken);


//        String variationType =    variationController.testStoreVariationTypeAPI(restaurantOwnerToken,restaurantId);
        String variationType =  variationController.testStoreMultipleVariationTypeAPI(restaurantOwnerToken,restaurantId);
        System.out.println("variationType:" + variationType);
        JsonNode jsonNodeOfVariationType = objectMapper.readTree(variationType);
        Integer variationTypeId = jsonNodeOfVariationType.get(0).get("id").asInt();
        String variationTypeName = jsonNodeOfVariationType.get(0).get("name").asText();
        System.out.println("variation Type ID: " + variationTypeId);

        variationController.testUpdateMultipleVariationTypeAPI(restaurantOwnerToken,variationTypeId);
        variationController.testUpdateVariationTypeAPI(restaurantOwnerToken,variationTypeId);
        variationController.testGetSingleVariationTypeAPI(restaurantOwnerToken,variationTypeId);
        variationController.testGetAllVariationTypeWithVariationAPI(restaurantOwnerToken,restaurantId);
        variationController.testGetAllVariationByTypeIdAPI(restaurantOwnerToken,variationTypeId);




//        String variation =  variationController.testStoreVariationAPI(restaurantOwnerToken,variationTypeId);
        String variation =  variationController.testStoreMultipleVariationAPI(restaurantOwnerToken,restaurantId,variationTypeId);
        System.out.println("variation:" + variation);
        JsonNode jsonNodeOfVariation = objectMapper.readTree(variation);
        Integer variationId = jsonNodeOfVariation.get(0).get("id").asInt();
        String variationName = jsonNodeOfVariation.get(0).get("name").asText();
        System.out.println("variation  ID: " + variationId);

        variationController.testUpdateMultipleVariationAPI(restaurantOwnerToken,variationId);
        variationController.testUpdateVariationAPI(restaurantOwnerToken,variationId);
        variationController.testGetAllVariationWithDishAPI(restaurantOwnerToken,restaurantId);
        variationController.testGetAllVariationByRestaurantIdAPI(restaurantOwnerToken,restaurantId);




//        String dishVariation =  variationController.testStoreDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
        String dishVariation =  variationController.testStoreMultipleDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
        System.out.println("dish variation:" + dishVariation);
        JsonNode jsonNodeOfDishVariation = objectMapper.readTree(dishVariation);
        Integer dishVariationId = jsonNodeOfDishVariation.get(0).get("id").asInt();
       
        System.out.println("dish variation  ID: " + dishVariationId);

        variationController.testGetAllDishVariationAPI(restaurantOwnerToken,dishId);
        variationController.testGetAllDishVariationAPI(restaurantOwnerToken,dishId);
        variationController.testUpdateDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
        variationController.testUpdateMultipleDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);



        String order =  orderController.testStoreOrderAPI( restaurantOwnerToken,  restaurantId,  restaurantOwnerID,
                 dishId, variationId, dealId);
        System.out.println("order:" + order);
        JsonNode jsonNodeOfOrder = objectMapper.readTree(order);
        Integer orderId = jsonNodeOfOrder.get("data").get("id").asInt();

        orderController.testStoreByUserAPI(restaurantOwnerToken, restaurantId, restaurantOwnerID, variationId, dealId, dishId);
        orderController.testOrderCompleteAPI(restaurantOwnerToken,orderId);
        orderController.testUpdateStatusAPI(restaurantOwnerToken,orderId);
        orderController.testEditOrderAPI(restaurantOwnerToken,orderId, variationId, dishId, dealId);
        orderController.testGetOrderByIdAPI(restaurantOwnerToken,orderId);
        orderController.testGetOrderById2API(restaurantOwnerToken,orderId,restaurantId);
        orderController.testGetOrderByCustomerIdAPI(restaurantOwnerToken,restaurantOwnerID);
        orderController.testGetTodaysOrderByStatusAPI(restaurantOwnerToken);
        orderController.testGetAllOrderAPI(restaurantOwnerToken);
        orderController.testGetAllOrderTodayAPI(restaurantOwnerToken,restaurantId);
        orderController.testGetAllOrderEveryDayAPI(restaurantOwnerToken,restaurantId);
        orderController.testGetOrderByTypeAPI(restaurantOwnerToken);
        orderController.testGetOrderByType2API(restaurantOwnerToken,restaurantId);
        orderController.testGetAllPendingOrderAPI(restaurantOwnerToken,restaurantId);
        orderController.testGetAllPendingOrderWithPaginationAPI(restaurantOwnerToken,restaurantId);
        orderController.testGetAllAutoPrintOrderAPI(restaurantOwnerToken,restaurantId);
        orderController.testGetDailyOrderReportAPI(restaurantOwnerToken);
        orderController.testGetOrderReportAPI(restaurantOwnerToken);
        orderController.testGetOrderReportByRestaurantIdAPI(restaurantOwnerToken,restaurantId);
        orderController.testGetOrderReportByRestaurantId2API(restaurantOwnerToken,restaurantId);
        orderController.testGetOrderByUserAPI(restaurantOwnerToken);




        orderController.testDeleteOrderAPI(restaurantOwnerToken,orderId);
        variationController.testDeleteDishVariationAPI(restaurantOwnerToken,variationTypeId,dishId);
        variationController.testDeleteVariationTypeAPI(restaurantOwnerToken,variationTypeId);
        dishController.testDeleteDishAPI(restaurantOwnerToken,dealId);
        dishController.testDeleteDishAPI(restaurantOwnerToken,dishId);
        menuController.testDeleteMenuAPI(restaurantOwnerToken,menuId);
        restaurantController.testDeleteRestaurantForceDeleteAPI(superadminToken,restaurantId);
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
