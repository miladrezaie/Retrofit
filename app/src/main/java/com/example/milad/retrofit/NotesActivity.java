package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.NoteInterface;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);


    }
}
