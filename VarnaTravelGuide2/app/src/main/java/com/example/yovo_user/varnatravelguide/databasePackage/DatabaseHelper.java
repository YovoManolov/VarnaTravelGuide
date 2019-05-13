package com.example.yovo_user.varnatravelguide.databasePackage;


import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.ImageDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.LandmarkDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategoryDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.RestaurantDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlacesDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHoursDaoImpl;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoDatabase;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

public class DatabaseHelper {

    private StitchAppClient stitchAppClient;
    public static RemoteMongoClient mongoClient;
    public static RemoteMongoDatabase varnaTravelGuideDB;

    private PlaceDaoImpl placeDaoImpl;
    private HotelDaoImpl hotelDaoImpl;
    private LandmarkDaoImpl landmarkDaoImpl;
    private RestaurantDaoImpl restaurantDaoImpl;
    private ImageDaoImpl imageDaoImpl;
    private WorkHoursDaoImpl workHoursDaoImpl;
    private PriceCategoryDaoImpl priceCategoryDaoImpl;
    private ShoppingPlacesDaoImpl shoppingPlacesDaoImpl;

    public DatabaseHelper() {

        stitchAppClient = Stitch.getDefaultAppClient();
        this.stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential());

        mongoClient = stitchAppClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
        varnaTravelGuideDB = mongoClient.getDatabase("varnaTravelGuideDB");

        placeDaoImpl = new PlaceDaoImpl();
        landmarkDaoImpl = new LandmarkDaoImpl();
        restaurantDaoImpl = new RestaurantDaoImpl();
        workHoursDaoImpl = new WorkHoursDaoImpl();
        priceCategoryDaoImpl = new PriceCategoryDaoImpl();
        shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl();
        hotelDaoImpl = new HotelDaoImpl();
    }

    public static RemoteMongoDatabase getVarnaTravelGuideDB() {
        return varnaTravelGuideDB;
    }

    public static void setVarnaTravelGuideDB(RemoteMongoDatabase varnaTravelGuideDB) {
        DatabaseHelper.varnaTravelGuideDB = varnaTravelGuideDB;
    }

    public static RemoteMongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(RemoteMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public HotelDaoImpl getHotelDaoImpl() {
        return hotelDaoImpl;
    }

    public PlaceDaoImpl getPlaceDaoImpl() {
        return placeDaoImpl;
    }

    public LandmarkDaoImpl getLandmarkDaoImpl() {
        return landmarkDaoImpl;
    }

    public ImageDaoImpl getImageDaoImpl() {
        return imageDaoImpl;
    }

    public RestaurantDaoImpl getRestaurantDaoImpl() {
        return restaurantDaoImpl;
    }

    public WorkHoursDaoImpl getWorkHoursDaoImpl() {
        return workHoursDaoImpl;
    }

    public PriceCategoryDaoImpl getPriceCategoryDaoImpl() {
        return priceCategoryDaoImpl;
    }

    public ShoppingPlacesDaoImpl getShoppingPlacesDaoImpl() {
        return shoppingPlacesDaoImpl;
    }
}