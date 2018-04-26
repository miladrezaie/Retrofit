package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.NoteInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class noteInfoActivity extends AppCompatActivity {

    private String token = "Bearer " + ApiClient.getAccessToken();
    private TextView name;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);


        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        noteInfoRequset(id);

    }


    private void noteInfoRequset(String id) {
        NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
        Call<Note> call = noteInterface.getNoteInfo(token, id);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()) {
                    name.setText(response.body().getName());
                    description.setText(response.body().getDescription());
                } else {
                    Toast.makeText(noteInfoActivity.this, "خطا در برنامه", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                Toast.makeText(noteInfoActivity.this, "fv", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
