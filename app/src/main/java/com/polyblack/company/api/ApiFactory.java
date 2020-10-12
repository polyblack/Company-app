package com.polyblack.company.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiFactory apiFactory;
    private static Retrofit retrofit;
    private static String URL = "http://gitlab.65apps.com/65gb/static/raw/master/";


    private ApiFactory() {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .create();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL)
                .build();
    }

    public static ApiFactory getInstance() {
        if (apiFactory == null) {
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
