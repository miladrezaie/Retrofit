package com.example.milad.retrofit.webService;


import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.ResultsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by milad on 4/22/2018.
 */

public interface NoteInterface {


    @Headers({
            "X-Backtory-Object-Storage-Id:5a9314fce4b092a32b632af9",
            "Content-Type:application/json"
    })
    @POST("/object-storage/classes/notes")
    Call<NoteResponseModel> CreateNote(@Header("Authorization") String authorization, @Body Note note);


    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @POST("/object-storage/classes/query/notes/")
    Call<ResultsResponse> allNotes(
            @Header("Authorization") String token);

}
