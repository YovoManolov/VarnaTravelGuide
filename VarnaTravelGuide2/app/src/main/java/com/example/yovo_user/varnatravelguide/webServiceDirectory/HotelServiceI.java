public interface HotelServiceI {

    private final String hotelPref="/hotels"

    @GET(hotelPref+"/getAll")
    public  Call<List<Hotel>> getAllHotels();

    @GET(hotelPref+"/getOneById/{id}")
    public  Call<Hotel> getHotelById(@Path("id") String hotelId) throws RecordNotFoundException;

    @PutMapping(hotelPref+"/update/{id}")
    public Call<Hotel> updateHotel(Hotel newHotel, @Path("id") String hotelId) throws RecordNotFoundException;

    @PostMapping(hotelPref+"/create")
    public Call<Hotel> createHotel(Hotel newHotel);

    @DeleteMapping(hotelPref+"deleteById/{id}")
    public Call<Object> deleteHotel(@Path("id")String hotelId) throws RecordNotFoundException;

}