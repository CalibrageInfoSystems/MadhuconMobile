/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trst01.locationtracker.services.FaLogTracking;

import static com.trst01.locationtracker.constant.AppConstant.TestLoc;
import static com.trst01.locationtracker.dagger.App.context;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.trst01.locationtracker.R;
import com.trst01.locationtracker.activity.LoginActivity;
import com.trst01.locationtracker.constant.AppConstant;
import com.trst01.locationtracker.constant.CommonConstants;
import com.trst01.locationtracker.constant.CommonUtils;
import com.trst01.locationtracker.models.Doc;
import com.trst01.locationtracker.models.LocationDTO;
import com.trst01.locationtracker.uiLibrary.helpers.AppHelper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

//import androidx.annotation.NonNull;
//import androidx.core.app.NotificationCompat;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * A bound and started service that is promoted to a foreground service when location updates have
 * been requested and all clients unbind.
 *
 * For apps running in the background on "O" devices, location is computed only once every 10
 * minutes and delivered batched every 30 minutes. This restriction applies even to apps
 * targeting "N" or lower which are run on "O" devices.
 *
 * This sample show how to use a long-running service for location updates. When an activity is
 * bound to this service, frequent location updates are permitted. When the activity is removed
 * from the foreground, the service promotes itself to a foreground service, and location updates
 * continue. When the activity comes back to the foreground, the foreground service stops, and the
 * notification assocaited with that service is removed.
 */
public class LocationUpdatesService extends Service {

    private static final String PACKAGE_NAME =
            "com.google.android.gms.location.sample.locationupdatesforegroundservice";

    private static final String TAG = "resPOINT";

    /**
     * The name of the channel for notifications.
     */
    private static final String CHANNEL_ID = "channel_01";

    public  static  String ACTION_BROADCAST = PACKAGE_NAME + ".broadcast";

    public static final String EXTRA_LOCATION = PACKAGE_NAME + ".location";
    private static final String EXTRA_STARTED_FROM_NOTIFICATION = PACKAGE_NAME +
            ".started_from_notification";

    private final IBinder mBinder = new LocalBinder();

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    /**
     * The identifier for the notification displayed for the foreground service.
     */
    private static final int NOTIFICATION_ID = 12345678;

    /**
     * Used to check whether the bound activity has really gone away and not unbound as part of an
     * orientation change. We create a foreground service notification only if the former takes
     * place.
     */
    private boolean mChangingConfiguration = false;

    private NotificationManager mNotificationManager;

    /**
     * Contains parameters used by {@link com.google.android.gms.location.FusedLocationProviderApi}.
     */
    private LocationRequest mLocationRequest;

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Callback for changes in location.
     */
    private LocationCallback mLocationCallback;

    private Handler mServiceHandler;


    /**
     * The current location.
     */
    private Location mLocation;




    public AppHelper appHelper;

    @SuppressWarnings("deprecation")
    public LocationUpdatesService() {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {

        appHelper = new AppHelper(getApplicationContext());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                onNewLocation(locationResult.getLastLocation());
            }
        };

        createLocationRequest();
        getLastLocation();

        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        mServiceHandler = new Handler(handlerThread.getLooper());
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            // Create the channel for the notification
            NotificationChannel mChannel =
                    new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);

            // Set the Notification Channel for the Notification Manager.
            mNotificationManager.createNotificationChannel(mChannel);
        }


    }



    @SuppressWarnings("deprecation")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service started");
        boolean startedFromNotification = intent.getBooleanExtra(EXTRA_STARTED_FROM_NOTIFICATION,
                false);
        if (startedFromNotification) {
            removeLocationUpdates();
            stopSelf();
        } else {
            // Start the service as a foreground service.
            startForeground(NOTIFICATION_ID, getNotification());
        }

        // Tells the system to not try to recreate the service after it has been killed.
        return START_NOT_STICKY;
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mChangingConfiguration = true;
    }


    @SuppressWarnings("deprecation")
    @Override
    public IBinder onBind(Intent intent) {
        // Called when a client (MainActivity in case of this sample) comes to the foreground
        // and binds with this service. The service should cease to be a foreground service
        // when that happens.
        Log.i(TAG, "in onBind()");
        stopForeground(true);
        mChangingConfiguration = false;

        // Register Firestore when service will restart
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseFirestore = FirebaseFirestore.getInstance();
        return mBinder;
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onRebind(Intent intent) {
        // Called when a client (MainActivity in case of this sample) returns to the foreground
        // and binds once again with this service. The service should cease to be a foreground
        // service when that happens.
        Log.i(TAG, "in onRebind()");
        stopForeground(true);
        mChangingConfiguration = false;

        // Register Firestore when service will restart
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseFirestore = FirebaseFirestore.getInstance();
        super.onRebind(intent);
    }


    @SuppressWarnings("deprecation")
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "Last client unbound from service");

        // Called when the last client (MainActivity in case of this sample) unbinds from this
        // service. If this method is called due to a configuration change in MainActivity, we
        // do nothing. Otherwise, we make this service a foreground service.
        if (!mChangingConfiguration && Utils.requestingLocationUpdates(this)) {
            Log.d(TAG, "Starting foreground service");
            /*
            // TODO(developer). If targeting O, use the following code.
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
                mNotificationManager.startServiceInForeground(new Intent(this,
                        LocationUpdatesService.class), NOTIFICATION_ID, getNotification());
            } else {
                startForeground(NOTIFICATION_ID, getNotification());
            }
             */

            startForeground(NOTIFICATION_ID, getNotification());


        }
        return true; // Ensures onRebind() is called when a client re-binds.
    }



    @SuppressWarnings("deprecation")
    @Override
    public void onDestroy() {
        mServiceHandler.removeCallbacksAndMessages(null);
    }

    /**
     * Makes a request for location updates. Note that in this sample we merely log the
     * {@link SecurityException}.
     */
    public void requestLocationUpdates() {
        Log.i(TAG, "Requesting location updates");
        Utils.setRequestingLocationUpdates(this, true);
        startService(new Intent(getApplicationContext(), LocationUpdatesService.class));
        try {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback, Looper.myLooper());
        } catch (SecurityException unlikely) {
            Utils.setRequestingLocationUpdates(this, false);
            Log.d(TAG, "Lost location permission. Could not request updates. " + unlikely);
        }
    }

    /**
     * Removes location updates. Note that in this sample we merely log the
     * {@link SecurityException}.
     */
    public void removeLocationUpdates() {
        Log.i(TAG, "Removing location updates");
        try {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            Utils.setRequestingLocationUpdates(this, false);
            stopSelf();
        } catch (SecurityException unlikely) {
            Utils.setRequestingLocationUpdates(this, true);
            Log.d(TAG, "Lost location permission. Could not remove updates. " + unlikely);
        }
    }

    /**
     * Returns the {@link NotificationCompat} used as part of the foreground service.
     */
    private Notification getNotification() {
        Intent intent = new Intent(this, LocationUpdatesService.class);

        CharSequence text = Utils.getLocationText(mLocation);

        // Extra to help us figure out if we arrived in onStartCommand via the notification or not.
        intent.putExtra(EXTRA_STARTED_FROM_NOTIFICATION, true);

        // The PendingIntent that leads to a call to onStartCommand() in this service.
//        PendingIntent servicePendingIntent =
//                PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent servicePendingIntent = PendingIntent.getService(this, 0, intent,
                PendingIntent.FLAG_MUTABLE);

        // The PendingIntent to launch activity.
        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, LoginActivity.class),  PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .addAction(R.drawable.ic_launch, getString(R.string.launch_activity),
                        activityPendingIntent)
                .addAction(R.drawable.ic_cancel, getString(R.string.remove_location_updates),
                        servicePendingIntent)
                .setContentText(text)
                .setContentTitle(Utils.getLocationTitle(this))
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(text)
                .setWhen(System.currentTimeMillis());

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID); // Channel ID
        }


        return builder.build();
    }

    private void getLastLocation() {
        try {
            mFusedLocationClient.getLastLocation()
                    .addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                mLocation = task.getResult();
                            } else {
                                Log.w(TAG, "Failed to get location.");
                            }
                        }
                    });
        } catch (SecurityException unlikely) {
            Log.d(TAG, "Lost location permission." + unlikely);
        }
    }
//
//    @Override
    public void onNewLocation(Location location) {
        if (location != null) {
            SharedPreferences sharedPreferences = getSharedPreferences("appprefs", MODE_PRIVATE);
            boolean isFreshInstall = sharedPreferences.getBoolean(CommonConstants.IS_FRESH_INSTALL, true);

            if (isFreshInstall) {
                // Store the first location when the app is freshly installed
                LocationDTO locationDTO = new LocationDTO();
                Doc doc = new Doc();
                doc.setLat(location.getLatitude());
                doc.setLon(location.getLongitude());
                String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                doc.setCreatedDate(dateTime);
                ArrayList<Doc> docs = new ArrayList<>();
                docs.add(doc);
                locationDTO.setDoc(docs);
                Gson gson = new Gson();
                String jsonArray = gson.toJson(locationDTO, LocationDTO.class);
                appHelper.getSharedPrefObj().edit().putString(TestLoc, jsonArray).apply();
                Toast.makeText(context, "isFirst", Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putBoolean(CommonConstants.IS_FRESH_INSTALL, false).apply();
            } else {
                // Handle subsequent location updates
                String testLocationData = appHelper.getSharedPrefObj().getString(TestLoc, "");
                LocationDTO locationDTO = new LocationDTO();
                if (!testLocationData.isEmpty()) {
                    // Parse the stored data from shared preferences
                    locationDTO = new Gson().fromJson(testLocationData, LocationDTO.class);
                }

                // Check the distance from the last stored location
                double selectedLat = 0.0;
                double selectedLon = 0.0;

                if (locationDTO.getDoc() != null && !locationDTO.getDoc().isEmpty()) {
                    Doc lastDoc = locationDTO.getDoc().get(locationDTO.getDoc().size() - 1);
                    selectedLat = lastDoc.getLat();
                    selectedLon = lastDoc.getLon();
                }

                double actualDistance = CommonUtils.distance(
                        location.getLatitude(), location.getLongitude(), selectedLat, selectedLon, 'm');

                if (actualDistance >= 20) {
                    // If the distance is greater than 10 meters, store the new location
                    Doc newDoc = new Doc();
                    newDoc.setLat(location.getLatitude());
                    newDoc.setLon(location.getLongitude());
                    String dateTime = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
                    newDoc.setCreatedDate(dateTime);
                    locationDTO.getDoc().add(newDoc);

                    // Sort the Doc list based on the CreatedDate property
                    Collections.sort(locationDTO.getDoc(), (doc1, doc2) -> {
                        String createdDate1 = doc1.getCreatedDate();
                        String createdDate2 = doc2.getCreatedDate();
                        return createdDate1.compareTo(createdDate2);
                    });

                    // Store the updated location data
                    String updatedLocationData = new Gson().toJson(locationDTO, LocationDTO.class);
                    appHelper.getSharedPrefObj().edit().putString(TestLoc, updatedLocationData).apply();
                    Toast.makeText(context, "Added new location", Toast.LENGTH_SHORT).show();
                    Log.e("LocationTest", "Changed and added");
                    Log.e("New size", String.valueOf(locationDTO.getDoc().size()));
                } else {
                    Log.e("LocationTest", "Location not added");
                }
            }
        }
    }




    /**
     * Sets the location request parameters.
     */
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    public class LocalBinder extends Binder {
        public LocationUpdatesService getService() {
            return LocationUpdatesService.this;
        }
    }





}
