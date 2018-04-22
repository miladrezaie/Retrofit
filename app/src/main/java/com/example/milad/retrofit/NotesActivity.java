package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class NotesActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        textView = (TextView) findViewById(R.id.ttView);
        Intent intent = getIntent();

        String token = intent.getExtras().toString();
        textView.setText(token);


    }
}
