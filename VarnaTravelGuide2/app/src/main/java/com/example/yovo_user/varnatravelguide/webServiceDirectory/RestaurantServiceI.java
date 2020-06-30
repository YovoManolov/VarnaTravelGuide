public interface RestaurantServiceI {

    private final String restaurantsPref = "/restaurants"

    @GET(restaurantsPref + "/getAll")
    public Call<List<Restaurant>> getAllRestaurants();

    @GET(restaurantsPref + "/getOneById/{id}")
    public Call<Restaurant> getRestaurantById(@Path("id") String restaurantId)
                            throws RecordNotFoundException;

    @PutMapping(restaurantsPref + "/update/{id}")
    public Call<Restaurant> updateHotel(Restaurant newRestaurant,
                                        @Path("id") String restaurantId)
                            throws RecordNotFoundException;

    @PostMapping(restaurantsPref + "/create")
    public Call<Restaurant> createHotel(Restaurant restaurant);

    @DeleteMapping(restaurantsPref + "deleteById/{id}")
    public Call<Object> deleteHotel(@Path("id") String restaurantId)
                            throws RecordNotFoundException;

}