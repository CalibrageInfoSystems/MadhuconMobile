package com.trst01.locationtracker.repositories;

//import static com.trst.corecarbon.services.webservice.AppWebService.apiBaseUrl;

import static com.trst01.locationtracker.services.webservice.AppWebService.apiBaseUrl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trst01.locationtracker.services.interceptors.NullOnEmptyConverterFactory;
import com.trst01.locationtracker.services.webservice.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_funtion_class {

    private static Retrofit retrofit = null;



    private static OkHttpClient httpClient =  new OkHttpClient.Builder()
            .readTimeout(150, TimeUnit.SECONDS)
            .connectTimeout(150, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
//            .addInterceptor(new LoggingInterceptor())
            .build();
    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder().baseUrl(apiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }
}