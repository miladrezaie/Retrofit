package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milad.retrofit.adapter.MyRecyclerView;
import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.ResultsResponse;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.NoteInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerView adapter;
    private LinearLayoutManager linearLayoutManager;

    private String token = "Bearer " + ApiClient.getAccessToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        userRequest();

    }

    private void userRequest() {
        NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
        Call<ResultsResponse> call = noteInterface.allNotes(token);
        call.enqueue(new Callback<ResultsResponse>() {
            @Override
            public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(NotesActivity.this, "نمایش کل داده ها", Toast.LENGTH_SHORT).show();
                    List<Note> notes = response.body().getAll();
                    setupRecyclerView(notes);

                } else {
                    Toast.makeText(NotesActivity.this, "ارور دارید", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultsResponse> call, Throwable t) {
                Toast.makeText(NotesActivity.this, "ارتباط با سرور مقدور نیست", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView(List<Note> notes) {
        recyclerView = (RecyclerView) findViewById(R.id.myReceycler);
        adapter = new MyRecyclerView(NotesActivity.this, notes);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
