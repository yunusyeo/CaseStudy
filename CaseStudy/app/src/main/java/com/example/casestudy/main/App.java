package com.example.casestudy.main;

import android.app.Application;

import com.example.casestudy.service.ApiClient;
import com.example.casestudy.service.ApiInterface;

public class App extends Application {
    public static ApiInterface apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        initialApiService();
    }

    private void initialApiService() {
        apiService = ApiClient.getRetrofitClient().create(ApiInterface.class);
    }
}
