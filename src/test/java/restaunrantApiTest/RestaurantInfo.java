package restaunrantApiTest;

public class RestaurantInfo {
    private Integer restaurantId;
    private String restaurantOwnerToken;
    private Integer OwnerID;
    private String restaurantEmail;

    public RestaurantInfo(Integer restaurantId, String restaurantOwnerToken,Integer OwnerID,String restaurantEmail) {
        this.restaurantId = restaurantId;
        this.restaurantOwnerToken = restaurantOwnerToken;
        this.OwnerID = OwnerID;
        this.restaurantEmail = restaurantEmail;

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
    public String getRestaurantEmail() {
        return restaurantEmail;
    }

}
