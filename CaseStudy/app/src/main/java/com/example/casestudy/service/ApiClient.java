package com.example.casestudy.service;

import com.example.casestudy.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final int CONNECTION_TIMEOUT = 120;
    private static String BASE_URL = Constants.BASE_URL;
    private static Retrofit retrofit = null;
    private static OkHttpClient client = null;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
            return retrofit;
        }
        return retrofit;
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }
}
