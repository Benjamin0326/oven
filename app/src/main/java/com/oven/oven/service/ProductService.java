package com.oven.oven.service;

import com.oven.oven.model.ProductDetailList;
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
    @GET("/MainData")
    Call<ProductResList> getProductList(@Query("page") int page);

    @FormUrlEncoded
    @POST("/Product_detail")
    Call<ProductDetailList> postProductDetail(@Field("pid") int pid);
}
