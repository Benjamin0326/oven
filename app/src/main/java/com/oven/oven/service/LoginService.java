package com.oven.oven.service;

import com.oven.oven.model.LoginRes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sung9 on 2017-08-12.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("/Signup")
    Call<LoginRes> postJoin(@Field("email") String email, @Field("password") String password, @Field("uname") String uname, @Field("hp") String hp, @Field("cname") String cname, @Field("address") String address);
}
