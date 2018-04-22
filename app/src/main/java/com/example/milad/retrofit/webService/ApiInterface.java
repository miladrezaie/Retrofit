package com.example.milad.retrofit.webService;

import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by milad on 4/19/2018.
 */

public interface ApiInterface {


    //call by send request


    @GET("index.php")
    Call<UserResponse>getUsers();


}
