package com.example.milad.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milad on 4/19/2018.
 */

public class UserResponse {

    @SerializedName("users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
