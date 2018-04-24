package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        Intent intent = new Intent();
        String n = intent.getExtras().getString("username");
        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(n.toString());




    }
}
