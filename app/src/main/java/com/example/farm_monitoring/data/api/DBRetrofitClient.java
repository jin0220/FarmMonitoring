package com.example.farm_monitoring.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DBRetrofitClient {
    private static final String BASE_URL = "http://easyfarm.dothome.co.kr/json/";

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static DBService api() {
        return getInstance().create(DBService.class);
    }
}
