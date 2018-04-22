package com.example.milad.retrofit.webService;

import com.backtory.java.model.LoginResponse;
import com.example.milad.retrofit.model.AuthenticationResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface LoginApiInterface {
    @FormUrlEncoded
    @Headers({
            "X-Backtory-Authentication-Id: 5a9314fbe4b04e579ee1edbe",
            "X-Backtory-Authentication-Key: 5a9314fbe4b05bb64131ee38"
    })
    @POST("/auth/login")
    Call<AuthenticationResponseModel> login(@Field("username") String username, @Field("password") String pass);






}
