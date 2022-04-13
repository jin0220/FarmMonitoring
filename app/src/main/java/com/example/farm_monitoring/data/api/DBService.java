package com.example.farm_monitoring.data.api;


import com.example.farm_monitoring.data.model.Comment;
import com.example.farm_monitoring.data.model.Community;
import com.example.farm_monitoring.data.model.Information;
import com.example.farm_monitoring.data.model.PlantCategory;
import com.example.farm_monitoring.data.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DBService {
    @GET("information_table.php")
    Call<List<Information>> getInfoPlantDetail(@Query("plant3") String plant3);

    @GET("plant_table.php")
    Call<List<PlantCategory>> getPlantCategory(@Query("plant1") String plant1);

    @POST("community_table.php")
    Call<List<Community>> getCommunity();

    @FormUrlEncoded
    @POST("community_detail.php")
    Call<Community> getCommunityDetail(@Field("num") String num);

    @GET("comment_table.php")
    Call<List<Comment>> getComment(@Query("num") String num);

    @FormUrlEncoded
    @POST("comment.php")
    Call<Comment> commentInsert(
            @Field("id") String id,
            @Field("num") String num,
            @Field("comment") String comment
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<User> register(
            @Field("id") String id,
            @Field("farm_id") String farm_id,
            @Field("name") String username,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("category1") String plant1,
            @Field("category2") String plant2,
            @Field("category3") String plant3
    );

    @FormUrlEncoded
    @POST("account.php")
    Call<User> account(
            @Field("id") String id,
            @Field("farm_id") String farm_id,
            @Field("name") String username,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user_table.php")
    Call<User> login(
            @Field("id") String id,
            @Field("password") String password
    );
}
