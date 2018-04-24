package com.example.milad.retrofit.webService;



import com.example.milad.retrofit.model.NoteResponseModel;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Headers;

/**
 * Created by milad on 4/22/2018.
 */

public interface NoteInterface {


    @SerializedName("/ds")
    @Headers({

    })
    Call<NoteResponseModel> Createnote();
}
