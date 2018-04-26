package com.example.milad.retrofit.webService;


import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.ResultsResponse;
import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.model.deleteResponseBodyModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    Call<ResultsResponse> allNotes(@Header("Authorization") String token, @Body Note note);


    //delete note
    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @DELETE("/object-storage/classes/notes/{Note-Id}")
    Call<String> deleteNote(@Header("Authorization") String token, @Path("Note-Id") String noteId);

    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @GET("/object-storage/classes/notes/{noteId}")
    Call<Note> getNoteInfo(@Header("Authorization") String token, @Path("noteId") String noteId);

//    @Headers({
//            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
//            "Content-Type: application/json"
//    })
//    @FormUrlEncoded
//    @POST("/object-storage/classes/notes/{noteId}")
//    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);

    @Headers({
            "X-Backtory-Object-Storage-Id: 5a9314fce4b092a32b632af9",
            "Content-Type: application/json"
    })
    @Multipart
    @PUT("/object-storage/classes/notes/{noteId}")
    Call<Note> updateNote(@Header("Authorization") String token,
                          @Path("noteId") String noteId,
                          @Body Note note);

}
