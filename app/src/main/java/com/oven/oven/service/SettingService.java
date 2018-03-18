package com.oven.oven.service;

import com.oven.oven.model.SettingUserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sung9 on 2018-01-21.
 */

public interface SettingService {
    @FormUrlEncoded
    @POST("/User_setting")
    Call<SettingUserInfo> postGetUserInfo(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("/Update_user")
    Call<SettingUserInfo> postUpdateUserInfo(@Field("uid") int uid, @Field("password") String password, @Field("uname") String uname, @Field("hp") String hp, @Field("cname") String cname, @Field("address") String address, @Field("de_addr") String de_addr, @Field("zipcode") String zipcode);
}
