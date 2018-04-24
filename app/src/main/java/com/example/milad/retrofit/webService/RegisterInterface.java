package com.example.milad.retrofit.webService;

import com.example.milad.retrofit.model.RegisterResponseModel;
import com.example.milad.retrofit.model.User;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;


import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by milad on 4/22/2018.
 */

public interface RegisterInterface {

    @Headers({
            "X-Backtory-Authentication-Id: 5a9314fbe4b04e579ee1edbe",
            "Content-Type: application/json",
    })
    @POST("/auth/users")
    Call<RegisterResponseModel> InsertUser(@Body User user
    );

//    @Field("firstName") String firstName,
//    @Field("lastName") String lastName,
//    @Field("username") String username,
//    @Field("password") String password,
//    @Field("email") String email,
//    @Field("phoneNumber") String phoneNumber,
//    @Field("avatar") String avatar
}