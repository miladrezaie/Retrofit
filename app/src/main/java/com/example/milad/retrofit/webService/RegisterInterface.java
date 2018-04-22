package com.example.milad.retrofit.webService;

import com.example.milad.retrofit.model.RegisterResponseModel;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by milad on 4/22/2018.
 */

public interface RegisterInterface {


    @Headers({
            "X-Backtory-Authentication-Id: 5a9314fbe4b04e579ee1edbe",
            "Content-Type: application/json"
    })
    @POST("/auth/users")
    Call<RegisterResponseModel> InsertUser(@Header("Authorization") String token);

}