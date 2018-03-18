package com.oven.oven.service;

import com.oven.oven.model.LoginRes;
import com.oven.oven.model.SideUserInfo;

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
    Call<LoginRes> postJoin(@Field("email") String email, @Field("password") String password, @Field("uname") String uname, @Field("hp") String hp, @Field("cname") String cname, @Field("address") String address, @Field("de_addr") String de_addr, @Field("zipcode") String zipcode);

    @FormUrlEncoded
    @POST("/Signup")
    Call<LoginRes> postKakaoJoin(@Field("email") String email, @Field("uname") String uname);

    @FormUrlEncoded
    @POST("/Login")
    Call<LoginRes> postLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/Kakaotalk_Login")
    Call<LoginRes> postKakaoLogin(@Field("email") String email, @Field("uname") String uname);

    @FormUrlEncoded
    @POST("/Side_menu")
    Call<SideUserInfo> postSidemenu(@Field("uid") int uid);


}
