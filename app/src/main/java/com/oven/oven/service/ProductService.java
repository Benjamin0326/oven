package com.oven.oven.service;

import com.oven.oven.model.CartProducts;
import com.oven.oven.model.DefaultModel;
import com.oven.oven.model.ProductDetailList;
import com.oven.oven.model.ProductFavorite;
import com.oven.oven.model.ProductResList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sung9 on 2017-09-09.
 */

public interface ProductService {
    @FormUrlEncoded
    @POST("/MainData")
    Call<ProductResList> getProductList(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("/Product_detail")
    Call<ProductDetailList> postProductDetail(@Field("pid") int pid, @Field("uid") int uid);

    @FormUrlEncoded
    @POST("/Favorite_toggle")
    Call<ProductFavorite> postProductFavorite(@Field("uid") int uid, @Field("pid") int pid);

    @FormUrlEncoded
    @POST("/Zzim_product")
    Call<ProductResList> postProductLikeList(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("/View_product")
    Call<ProductResList> postProductVIewList(@Field("uid") int uid);

    @FormUrlEncoded
    @POST("/Add_cart")
    Call<DefaultModel> postAddCart(@Field("pid") int pid, @Field("uid") int uid, @Field("cnt") int cnt);

    @FormUrlEncoded
    @POST("/Cart_product")
    Call<CartProducts> postCartProduct(@Field("uid") int uid);
}
