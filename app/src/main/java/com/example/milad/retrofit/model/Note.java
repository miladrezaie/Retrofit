package com.example.milad.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by milad on 4/24/2018.
 */

public class Note {


    @SerializedName("user_id")
    private String user_id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public Note(String user_id, String name, String description) {
        this.user_id = user_id;
        this.name = name;
        this.description = description;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

