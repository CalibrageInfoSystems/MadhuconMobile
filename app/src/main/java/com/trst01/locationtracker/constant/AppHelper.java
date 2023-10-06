package com.trst01.locationtracker.constant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.trst01.locationtracker.uiLibrary.dialogs.DialogHelper;
import com.trst01.locationtracker.uiLibrary.helpers.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppHelper {

    private static final String TAG = AppHelper.class.getCanonicalName();
    private Context context;
    private DialogHelper dialogHelper;

    public AppHelper(Context context) {
        this.context = context;
    }

    public DialogHelper getDialogHelper() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper(context);
        }
        return dialogHelper;
    }

    public String getCurrentDateTime(String strDateFormat) {
        String strCurrDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Calendar c1 = Calendar.getInstance(); // today
            strCurrDate = sdf.format(c1.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strCurrDate;

    }

    public boolean isNetworkAvailable() {
        boolean isAvailable = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                @SuppressLint("MissingPermission")
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    isAvailable = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAvailable;
    }

    public SharedPreferences getSharedPrefObj() {
        SharedPreferences sharedPrefObj = null;
        try {
            sharedPrefObj = context.getSharedPreferences(AppConstants.App_SHARED_PREF_NAME , Context.MODE_PRIVATE);
        } catch(Exception ex) {
            Log.e(TAG, "Exception getAndCreateSessionObj" + ex);
        }
        return sharedPrefObj;
    }
}
