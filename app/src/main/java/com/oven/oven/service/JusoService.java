package com.oven.oven.service;

import com.oven.oven.model.AddressSearchResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sung9 on 2018-01-21.
 */

public interface JusoService {
    @FormUrlEncoded
    @POST("/Search_addr")
    Call<AddressSearchResult> getJuso(@Field("keyword") String keyword);

}
