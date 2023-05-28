package restaunrantApiTest;

public class RestaurantInfo {
    private Integer restaurantId;
    private String restaurantOwnerToken;
    private Integer OwnerID;

    public RestaurantInfo(Integer restaurantId, String restaurantOwnerToken,Integer OwnerID) {
        this.restaurantId = restaurantId;
        this.restaurantOwnerToken = restaurantOwnerToken;
        this.OwnerID = OwnerID;

    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantOwnerToken() {
        return restaurantOwnerToken;
    }
    public Integer getRestaurantOwnerID() {
        return OwnerID;
    }
}
