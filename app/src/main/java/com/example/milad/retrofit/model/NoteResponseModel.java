package com.example.milad.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by milad on 4/24/2018.
 */

public class NoteResponseModel {
    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("_id")
    private String _id;

    public NoteResponseModel(String createdAt,String _id){
        this.createdAt=createdAt;
        this._id=_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
