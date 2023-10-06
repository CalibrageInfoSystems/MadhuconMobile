package com.trst01.locationtracker.services.interceptors;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;


public class HttpInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //HttpLoggingInterceptor.Logger.d(BuildConfig.APPLICATION_ID, "call ==> " + request.url());
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
      //  Buffer buffer = source.buffer();
     //   HttpLoggingInterceptor.Logger.d(BuildConfig.APPLICATION_ID, "ret ==> " + buffer.clone().readString(UTF8).toString());
        return response;
    }
}