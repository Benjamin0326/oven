package com.oven.oven.component;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sung9 on 2018-01-21.
 */

public class JusoNetwork extends Application {

    public static Retrofit buildRetrofit() {

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl("http://www.juso.go.kr/");
        builder.addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }
}
