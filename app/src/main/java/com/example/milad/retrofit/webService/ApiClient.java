package com.example.milad.retrofit.webService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by milad on 4/19/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "https://api.backtory.com/";
    public static final String XBacktoryAuthenticationId = "5a9314fbe4b04e579ee1edbe";
    public static final String XBacktoryAuthenticationKeyClient = "5a9314fbe4b05bb64131ee38";

    public static String access_token;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static String getAccessToken(){
        return access_token;
    }

}
