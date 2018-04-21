package com.example.milad.retrofit.webService;

import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by milad on 4/19/2018.
 */

public interface ApiInterface {


    //call by send request

    @GET("index.php")
    Call<UserResponse>getUsers();
}
