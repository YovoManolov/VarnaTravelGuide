public interface ShoppingPlaceServiceI {

    private final String shoppingPlacePref = "/shoppingPlaces"

    @GET(shoppingPlacePref + "/getAll")
    public Call<List<Place>> getAllShoppingPlaces();

    @GET(shoppingPlacePref + "/getOneById/{id}")
    public Call<Place> getHotelById(@Path("id") String shoppingPlaceId)
                throws RecordNotFoundException;

    @PutMapping(shoppingPlacePref + "/update/{id}")
    public Call<Place> updateHotel(Hotel newHotel, @Path("id") String shoppingPlaceId)
            throws RecordNotFoundException;

    @PostMapping(shoppingPlacePref + "/create")
    public Call<Place> createHotel(Hotel newShoppingPlace);

    @DeleteMapping(shoppingPlacePref + "deleteById/{id}")
    public Call<Object> deleteHotel(@Path("id") String shoppingPlaceId)
            throws RecordNotFoundException;

}