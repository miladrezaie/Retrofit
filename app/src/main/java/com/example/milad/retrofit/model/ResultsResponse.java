package com.example.milad.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by milad on 4/24/2018.
 */

public class ResultsResponse {

    @SerializedName("results")
    private List<Note> results;

    public List<Note> getAll() {
        return results;
    }
}
