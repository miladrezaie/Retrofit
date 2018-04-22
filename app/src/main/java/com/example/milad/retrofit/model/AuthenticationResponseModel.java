package com.example.milad.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by milad on 4/22/2018.
 */

public class AuthenticationResponseModel {

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("token_type")
    public String token_type;

    @SerializedName("refresh_token")
    public String refresh_token;

    @SerializedName("expires_in")
    public String expires_in;

    public String scope;
    public String jti;


    public AuthenticationResponseModel(String access_token, String token_type) {
        this.access_token = access_token;
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getJti() {
        return jti;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

}
