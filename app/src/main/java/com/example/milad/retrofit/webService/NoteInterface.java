package com.example.milad.retrofit.webService;


import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.ResultsResponse;
import com.example.milad.retrofit.model.deleteResponseBodyModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface NoteInterface {

    //create note

    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @POST("/object-storage/classes/notes")
    Call<NoteResponseModel> createNote(@Header("Authorization") String token, @Body Note note);



    //all note user
    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @POST("/object-storage/classes/query/notes/")
    Call<ResultsResponse> allNotes(@Header("Authorization") String token,@Body Note note);


    //delete note
    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @DELETE("/object-storage/classes/notes/{Note-Id}")
    Call<deleteResponseBodyModel> deleteGist(@Path("Note-Id") String noteId);





}
