package com.example.milad.retrofit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.deleteResponseBodyModel;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.NoteInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class noteInfoActivity extends AppCompatActivity {

    private String token = "Bearer " + ApiClient.getAccessToken();
    private TextView name;
    private TextView description;
    private Button btnUpdate;
    private Button btnDelete;
    private String id;

    private EditText edttitle;
    private EditText edtdescription;
    private TextView textviewsabt;
    private TextView textviewkhoroj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);


        btnDelete = (Button) findViewById(R.id.btndelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDeleteRequest(id);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
                    Log.i("miladrezaie", " " + response.body().get_id());
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

    private void noteDeleteRequest(String id) {
        NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
        Call<String> call = noteInterface.deleteNote(token, id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(noteInfoActivity.this, "حذف شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(noteInfoActivity.this, "خطا در برنامه", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(noteInfoActivity.this, "ارتباط با سرور مقدور نیست", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void noteUpdateRequst(String id) {
        NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
        Call<Note> call =noteInterface.updateNote()
    }
    private void coustomAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(noteInfoActivity.this);
        View v = getLayoutInflater().inflate(R.layout.dialog, null);

        builder.setView(v);


        final AlertDialog dialog = builder.create();
        dialog.show();

        edttitle = (EditText) dialog.findViewById(R.id.edttitle);
        edtdescription = (EditText) dialog.findViewById(R.id.edtdescription);
        textviewsabt = (TextView) dialog.findViewById(R.id.textviewsabt);
        textviewkhoroj = (TextView) dialog.findViewById(R.id.textviewkhoroj);

        textviewsabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("miladrezaie", String.valueOf(edttitle));
                Log.i("miladrezaie", String.valueOf(edtdescription));


                NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
                Call<NoteResponseModel> call = noteInterface.createNote(token, new Note(
                                ApiClient.getUser_id(),
                                edttitle.getText().toString(),
                                edtdescription.getText().toString()
                        )
                );

                call.enqueue(new Callback<NoteResponseModel>() {
                    @Override
                    public void onResponse(Call<NoteResponseModel> call, Response<NoteResponseModel> response) {
                        if (response.isSuccessful()) {

                            Toast.makeText(noteInfoActivity.this, "عملیات موفقت آمیز بود", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Log.i("miladrezaie", String.valueOf(call.request().headers()));
                            Log.i("miladrezaie", String.valueOf(call.request().body()));

                            Toast.makeText(noteInfoActivity.this, "ارور دارید در ثبت یادداشت", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoteResponseModel> call, Throwable t) {
                        Toast.makeText(noteInfoActivity.this, "سرور ارور دارید", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        textviewkhoroj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}
