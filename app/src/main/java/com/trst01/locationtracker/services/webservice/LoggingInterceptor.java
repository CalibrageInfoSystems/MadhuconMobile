package com.trst01.locationtracker.services.webservice;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class LoggingInterceptor   implements Interceptor {
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Log.i("LoggingInterceptor", "inside intercept callback");
//        Request request = chain.request();
//        long t1 = System.nanoTime();
//        String requestLog = String.format("Sending request %s on %s%n%s",
//                request.url(), chain.connection(), request.headers());
//        if (request.method().compareToIgnoreCase("post") == 0) {
//            requestLog = "\n" + requestLog + "\n" + bodyToString(request);
//        }
//        Log.d("TAG", "request" + "\n" + requestLog);
//        Response response = chain.proceed(request);
//        long t2 = System.nanoTime();
//
//        String responseLog = String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers());
//
//        String bodyString = response.body().string();
//
//        try{
//
//          //  Log.d("TAG", "response only" + "\n" + bodyString);
//
//       //     Log.d("TAG", "response" + "\n" + responseLog + "\n" + bodyString);
//        } catch (Exception e){
//
//        }
//
//
//        return response.newBuilder()
//                .body(ResponseBody.create(response.body().contentType(), bodyString))
//                .build();
//
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.i("LoggingInterceptor", "inside intercept callback");
        Request request = chain.request();
        long t1 = System.nanoTime();
        String requestLog = String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers());
        if (request.method().compareToIgnoreCase("post") == 0) {
            requestLog = "\n" + requestLog + "\n" + bodyToString(request);
        }
        Log.d("TAG", "request" + "\n" + requestLog);
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        String responseLog = String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers());

        // Log response headers
        Log.d("TAG", "response headers" + "\n" + responseLog);

        // Stream response body directly to log
        try (BufferedSource source = response.body().source()) {
            Buffer buffer = new Buffer();
            long bytesRead;
            while ((bytesRead = source.read(buffer, 8192)) != -1) {
                String chunk = buffer.clone().readUtf8();
                Log.d("TAG", "response body chunk" + "\n" + chunk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the original response
        return response;
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}