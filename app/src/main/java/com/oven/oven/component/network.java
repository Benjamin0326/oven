package com.oven.oven.component;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class network extends Application {

    public static Retrofit buildRetrofit() {

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl("http://13.124.227.53:9000");
        builder.addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }
}
