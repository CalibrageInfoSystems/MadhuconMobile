package com.trst01.locationtracker.livedata;

import static com.trst01.locationtracker.constant.AppConstant.MOBILE_CONNECTION;
import static com.trst01.locationtracker.constant.AppConstant.NO_INTERNET_CONNECTION;
import static com.trst01.locationtracker.constant.AppConstant.WIFI_CONNECTION;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.trst01.locationtracker.models.ConnectionModel;

//import com.trst.corecarbon.models.ConnectionModel;


public class ConnectionLiveData extends LiveData<ConnectionModel> {

    private Context context;

    public ConnectionLiveData(Context context) {
        this.context = context;
    }

    @Override
    protected void onActive() {
        super.onActive();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(networkReceiver,intentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        context.unregisterReceiver(networkReceiver);
    }


    private BroadcastReceiver networkReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null){
                NetworkInfo networkInfo=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                boolean isConnected=networkInfo!=null && networkInfo.isConnectedOrConnecting();
                if(isConnected){
                    switch (networkInfo.getType()){
                        case ConnectivityManager.TYPE_MOBILE:
                            postValue(new ConnectionModel(MOBILE_CONNECTION,true));
                            break;
                        case ConnectivityManager.TYPE_WIFI:
                            postValue(new ConnectionModel(WIFI_CONNECTION,true));
                            break;
                    }
                }else {
                    postValue(new ConnectionModel(NO_INTERNET_CONNECTION,false));
                }
            }
        }
    };
}
