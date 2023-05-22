package restaunrantApiTest;

public class RestaurantInfo {
    private Integer restaurantId;
    private String restaurantOwnerToken;

    public RestaurantInfo(Integer restaurantId, String restaurantOwnerToken) {
        this.restaurantId = restaurantId;
        this.restaurantOwnerToken = restaurantOwnerToken;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantOwnerToken() {
        return restaurantOwnerToken;
    }
}
