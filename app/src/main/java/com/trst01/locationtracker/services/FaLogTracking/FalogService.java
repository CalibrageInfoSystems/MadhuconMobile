package com.trst01.locationtracker.services.FaLogTracking;

import static com.trst01.locationtracker.BuildConfig.DEBUG;
import static com.trst01.locationtracker.constant.AppConstant.TestLoc;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
//import android.location.LocationManager;
//import android.location.LocationProvider;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonConstants;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.database.dao.AppDAO;
import com.trst01.locationtracker.helper.ApplicationThread;
import com.trst01.locationtracker.models.Doc;
import com.trst01.locationtracker.models.LocationDTO;
import com.trst01.locationtracker.repositories.AppRepository;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;
import com.trst01.locationtracker.view_models.AppViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

//import com.matrixoilpalm.mainapp.areacalculator.LocationProvider;
//import com.matrixoilpalm.mainapp.cloudhelper.ApplicationThread;
//import com.matrixoilpalm.mainapp.common.CommonConstants;
//import com.matrixoilpalm.mainapp.common.CommonUtils;
//import com.matrixoilpalm.mainapp.database.DataAccessHandler;
//import com.matrixoilpalm.mainapp.database.Palm3FoilDatabase;
//import com.matrixoilpalm.mainapp.database.Queries;
//import com.matrixoilpalm.mainapp.datasync.helpers.DataSyncHelper;
//import com.matrixoilpalm.mainapp.dbmodels.UserDetails;

/**
 * Created by BaliReddy on 10/12/2017.
 */

public class FalogService extends Service implements LocationListener {
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//
//    }

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    private static final String LOG_TAG = "MyService";

    private static LocationProvider mLocationProvider;
    private static String latLong = "";
//    private final int INTERVAL = 5000;
//    private Timer timer = new Timer();

    PowerManager.WakeLock wakeLock;
    public Context context;
    double latitude, longitude;
    //    private Palm3FoilDatabase palm3FoilDatabase;
    private static final int MIN_UPDATE_TIME = 0;
    private static final int MIN_UPDATE_DISTANCE = 250;
    private Location location;

    public LocationManager locationManager;
    public String CreatedDate, UpdatedDate, ServerUpdatedStatus, CreatedByUserId, UpdatedByUserId, IsActive, IMEINumber;
    public String USER_ID_TRACKING;
    public static final String ACTION_LOCATION_UPDATED_TRACKING = "com.matrixoilpalm.mainapp.falogService.location.updated";
    public static final String ACTION_START = "com.matrixoilpalm.mainapp.falogService.start";
    public static final String TRACKING_LONGITUDE = "geo_longitude";
    public static final String TRACKING_LATITUDE = "geo_latitude";
//    private DataAccessHandler dataAccessHandler = null;
//    public AppViewModel viewModel;
//    public AppRepository appRepository;
//    private final AppDAO appDAO;
//    private final Executor executor;
//    private final AppHelper appHelper;

    int delay = 10000;
    Handler handler = new Handler();
    Runnable runnable;
    //    public AppHelper appHelper;
    public AppHelper appHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        appHelper = new AppHelper(getApplicationContext());
//        String TestLo = appHelper.getSharedPrefObj().getString(TestLoc,"");
//        Log.e("token",appHelper.getSharedPrefObj().getString(TestLoc,""));
//        LocationDTO locationDTO = new LocationDTO();
//        Gson gson = new Gson();
//        gson.fromJson(TestLo, LocationDTO.class);
//        locationDTO = gson.fromJson(TestLo, LocationDTO.class);
//        Log.e("token",locationDTO.getDoc().get(0).getLat()+"-"+locationDTO.getDoc().get(0).getLon());
//        palm3FoilDatabase = new Palm3FoilDatabase(this);
        Log.v(LOG_TAG, "Congrats! MyService Created");
//        Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
        Log.d(LOG_TAG, "onCreate");


    }

    public static LocationProvider getLocationProvider(Context context, boolean showDialog) {
        if (mLocationProvider == null) {

            mLocationProvider = new LocationProvider(context, mLatLong -> latLong = mLatLong);

        }
        if (mLocationProvider.getLocation(showDialog)) {
            return mLocationProvider;
        } else {
            return null;
        }

    }

    public String getLatLong(Context context, boolean showDialog) {

        mLocationProvider = getLocationProvider(context, showDialog);

        if (mLocationProvider != null) {
            latLong = mLocationProvider.getLatitudeLongitude();


        }
        return latLong;
    }

    public void startLocationService(ApplicationThread.OnComplete onComplete) {
        Log.d(LOG_TAG, "start location service");
        String providerType = null;

        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            boolean gpsProviderEnabled = locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean networkProviderEnabled = locationManager != null && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (gpsProviderEnabled || networkProviderEnabled) {
                if (networkProviderEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        providerType = "network";
                        d(LOG_TAG, "network lbs provider:" + (location == null ? "null" : String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude())));

                    } else {
                        // Request network location permissions here
                        d(LOG_TAG, "network permission check");
                    }
                }
                if (gpsProviderEnabled && location == null) {

                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            providerType = "network";
                            d(LOG_TAG, "network lbs provider:" + (location == null ? "null" : String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude())));

                        } else {
                            // Request network location permissions here
                            d(LOG_TAG, "network permission check");
                        }
                    }
            }


        } catch (Exception e) {
            Log.e(LOG_TAG, "Cannot get location", e);
        }

        if (onComplete != null) {
            onComplete.execute(location != null, location, providerType);
        }
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        IMEINumber = CommonUtils.getIMEInumber(this);
        context = getApplicationContext();
//        viewModel = ViewModelProviders.of((FragmentActivity) context, viewModelFactory).get(AppViewModel.class);

//        appRepository = new AppRepository();
//        viewModel = new AppViewModel(appRepository);
        d(LOG_TAG, "start location service & location listener");
        ApplicationThread.nuiPost(LOG_TAG, "start lococation service", new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable = new Runnable() {
                    public void run() {
                        handler.postDelayed(runnable, delay);
//                        Toast.makeText(context, "This method is run every 10 seconds", Toast.LENGTH_SHORT).show();
                        startLocationService(null);
                    }
                }, delay);
                startLocationService(null);

            }
        });
// create db
//        try {
//            palm3FoilDatabase = Palm3FoilDatabase.getPalm3FoilDatabase(this);
//            palm3FoilDatabase.createDataBase();
//            dataAccessHandler = new DataAccessHandler(context);
//        } catch (Exception e) {
//            e.getMessage();
//        }


//        String query = Queries.getInstance().getUserDetailsNewQuery(CommonUtils.getIMEInumber(this));
//
//        DataAccessHandler dataAccessHandler = new DataAccessHandler(this);
//        final UserDetails userDetails = (UserDetails) dataAccessHandler.getUserDetails(query, 0);

//        if (null != userDetails) {
//            USER_ID_TRACKING = userDetails.getId();
//            Log.v(LOG_TAG, "Start Service userId" + USER_ID_TRACKING);
//        }

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appprefs", MODE_PRIVATE);
        boolean isFreshInstall = sharedPreferences.getBoolean(CommonConstants.IS_FRESH_INSTALL, true);

        if (location != null) {

//            if (latitude != 0.0 && longitude != 0.0) {

            if (isFreshInstall) {
                LocationDTO locationDTO = new LocationDTO();
                Doc doc = new Doc();
                doc.setLat(latitude);
                doc.setLon(longitude);
                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                Log.d("TAG", "onClick: date" + dateTime);
                doc.setCreatedDate(dateTime);
                ArrayList<Doc> docs = new ArrayList<>();
                docs.add(doc);
                locationDTO.setDoc(docs);
                Gson gsons = new Gson();
                String jsonArray = gsons.toJson(locationDTO, LocationDTO.class);
                appHelper.getSharedPrefObj().edit().putString(TestLoc, jsonArray).apply();
                Log.e("locationTest", "addded");
                Toast.makeText(context, "isFirst", Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putBoolean(CommonConstants.IS_FRESH_INSTALL, false).apply();
            }

            String latlong[] = getLatLong(FalogService.this, false).split("@");
            //   Toast.makeText(getApplicationContext(), "location "+ latitude+ "/" +longitude, Toast.LENGTH_SHORT).show();

            //  Toast.makeText(getApplicationContext(), "location "+ String.valueOf(location.getLatitude()) + "/" + String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();

            Log.d(LOG_TAG, "updateTracking location:" + String.valueOf(location.getLatitude()) + "/" + String.valueOf(location.getLongitude()));
            latitude = Double.parseDouble(latlong[0]);
            longitude = Double.parseDouble(latlong[1]);

            Log.d(LOG_TAG, "location latlongds " + latitude+ "/" +longitude);

            CommonConstants.Current_Latitude = latitude;
            CommonConstants.Current_Longitude = longitude;


            CreatedDate = CommonUtils.getcurrentDateTime(CommonConstants.DATE_FORMAT_DDMMYYYY_HHMMSS_SSS);
            UpdatedDate = CommonUtils.getcurrentDateTime(CommonConstants.DATE_FORMAT_DDMMYYYY_HHMMSS_SSS);
            ServerUpdatedStatus = CommonConstants.ServerUpdatedStatus;
            CreatedByUserId = USER_ID_TRACKING;
            UpdatedByUserId = USER_ID_TRACKING;

            IsActive = "1";

            String TestLo = appHelper.getSharedPrefObj().getString(TestLoc, "");
            Log.d(LOG_TAG, "Saved data from shared preferences: " + TestLo);
            LocationDTO locationDTOs = new LocationDTO();
            Gson gson = new Gson();
            gson.fromJson(TestLo, LocationDTO.class);
            locationDTOs = gson.fromJson(TestLo, LocationDTO.class);

            String selectedLatLong = "";

            if (locationDTOs != null) {

                if (locationDTOs.getDoc() != null && locationDTOs.getDoc().size() > 0) {

                    selectedLatLong = locationDTOs.getDoc().get(locationDTOs.getDoc().size() - 1).getLat() + "-" +
                            locationDTOs.getDoc().get(locationDTOs.getDoc().size() - 1).getLon();
                    Log.e("previously saved", locationDTOs.getDoc().get(locationDTOs.getDoc().size() - 1).getLat() + "-" +
                            locationDTOs.getDoc().get(locationDTOs.getDoc().size() - 1).getLon());
                } else {
                    selectedLatLong = latitude + "-" + longitude;
                    Log.e("current location", latitude + "-" + longitude);
                    LocationDTO locationDTO = new LocationDTO();
                    Doc doc = new Doc();
                    doc.setLat(latitude);
                    doc.setLon(longitude);
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    Log.d("TAG", "onClick: date" + dateTime);
                    doc.setCreatedDate(dateTime);
                    ArrayList<Doc> docs = new ArrayList<>();
                    docs = locationDTOs.getDoc();
                    docs.add(doc);
                    locationDTO.setDoc(docs);

                    Gson gsons = new Gson();//for adding new points
                    String jsonArray = gsons.toJson(locationDTOs, LocationDTO.class);
                    appHelper.getSharedPrefObj().edit().putString(TestLoc, jsonArray).apply();
                    Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                }

            }

            Log.d(LOG_TAG, "selectedLatLong ====359" + selectedLatLong);
            //fetch last inserted data.


//            String selectedLatLong = dataAccessHandler.getFalogLatLongs(Queries.getInstance().queryVerifyFalogDistance());
            if (!TextUtils.isEmpty(selectedLatLong)) {
                Log.v(LOG_TAG, "@@@@ data " + selectedLatLong);
                double actualDistance = 0;
                String[] yieldDataArr = selectedLatLong.split("-");

                if (yieldDataArr.length > 0 && !TextUtils.isEmpty(yieldDataArr[0]) && !TextUtils.isEmpty(yieldDataArr[1])) {

                    actualDistance = CommonUtils.distance(latitude, longitude,
                            Double.parseDouble(yieldDataArr[0]),
                            Double.parseDouble(yieldDataArr[1]), 'm');

                }

                Log.v(LOG_TAG, "@@@@ actual distance " + actualDistance);

//                if (actualDistance >= 10) {
//                if (actualDistance >= 30) {
                if (actualDistance >= 10) {

                    LocationDTO locationDTO = new LocationDTO();
                    Doc doc = new Doc();
                    doc.setLat(latitude);
                    doc.setLon(longitude);
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    Log.d("TAG", "onClick: date" + dateTime);
                    doc.setCreatedDate(dateTime);

                    ArrayList<Doc> docs = locationDTOs.getDoc();
                    docs.add(doc);

// Sort the Doc list based on the CreatedDate property
                    Collections.sort(docs, (doc1, doc2) -> {
                        String createdDate1 = doc1.getCreatedDate();
                        String createdDate2 = doc2.getCreatedDate();
                        return createdDate1.compareTo(createdDate2);
                    });

                    locationDTO.setDoc(docs);
                    Gson gsons = new Gson();
                    String jsonArray = gsons.toJson(locationDTO, LocationDTO.class);
                    appHelper.getSharedPrefObj().edit().putString(TestLoc, jsonArray).apply();
                    Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                    Log.e("testLocation", latitude + "-" + longitude);

                    Log.e("locationTest", "changed and added");
                    Log.e("new set", String.valueOf(locationDTOs.getDoc().size()));

                }
                else {
                    Log.e("locationTest", "not added");

//                    UiUtils.showCustomToastMessage("plz wiat for 250M", context, 0);

                }
            }
            else {

                LocationDTO locationDTO = new LocationDTO();
                Doc doc = new Doc();
                doc.setLat(latitude);
                doc.setLon(longitude);
                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                Log.d("TAG", "onClick: date" + dateTime);
                doc.setCreatedDate(dateTime);
                ArrayList<Doc> docs = new ArrayList<>();
                docs = locationDTOs.getDoc();
                docs.add(doc);
                locationDTO.setDoc(docs);

                locationDTOs.setDoc(docs);
                Gson gsons = new Gson();//for adding new points
                String jsonArray = gsons.toJson(locationDTOs, LocationDTO.class);

                appHelper.getSharedPrefObj().edit().putString(TestLoc, jsonArray).apply();
                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
                Log.e("testLocation", latitude + "-" + longitude);

                Log.e("locationTest", "changed and added");
                Log.e("new set", String.valueOf(locationDTOs.getDoc().size()));


            }
        }


    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public static void d(final String clazz, final String msg) {
        if (DEBUG) android.util.Log.d(clazz, ""+msg);
    }

//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return null;
//    }
}
