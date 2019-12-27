package com.imuons.shopntrips.retrofit;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    private static final String DEV_BASE_URL = "https://www.shopntripsindia.com/replica/shopntripsindia/public/api/";

    private static final long HTTP_TIMEOUT = TimeUnit.SECONDS.toMillis(60);
    private static ShopNTrips apiService;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(DEV_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static ShopNTrips getApiService() {
        if (apiService == null) {
            httpClient.connectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClient.writeTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClient.readTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClient.retryOnConnectionFailure(true);
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
            Retrofit retrofit = builder.client(httpClient.build()).build();
            apiService = retrofit.create(ShopNTrips.class);
            return apiService;
        } else {
            return apiService;
        }
    }

}
