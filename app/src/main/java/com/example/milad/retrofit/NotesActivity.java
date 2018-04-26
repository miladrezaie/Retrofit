package com.example.milad.retrofit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milad.retrofit.adapter.MyRecyclerView;
import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.ResultsResponse;

import com.example.milad.retrofit.model.deleteResponseBodyModel;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.NoteInterface;

import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cn.pedant.SweetAlert.SweetAlertDialog.*;

public class NotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerView adapter;
    private LinearLayoutManager linearLayoutManager;
    private EditText edttitle;
    private EditText edtdescription;
    private TextView textviewsabt;
    private TextView textviewkhoroj;
    private String token = "Bearer " + ApiClient.getAccessToken();
    private Button btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        userRequest();

        coustomAlertDialog();


    }

    private void userRequest() {
        NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
        Call<ResultsResponse> call = noteInterface.allNotes(token, new Note(ApiClient.getUser_id()));
        call.enqueue(new Callback<ResultsResponse>() {
            @Override
            public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(NotesActivity.this, "نمایش کل داده ها", Toast.LENGTH_SHORT).show();
                    List<Note> notes = response.body().getAll();
                    Note note = new Note();
                    ApiClient.id = note.get_id();
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

    private void process_Dialog() {
        ProgressDialog progressDialog = new ProgressDialog(NotesActivity.this);
        progressDialog.setMessage("لطفا صبر کنید ");
        progressDialog.setTitle("نمایش داده شد ");
        progressDialog.setButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        progressDialog.show();
    }

    private void alertDiaog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this).setTitle("alertdialog")
                .setMessage("milad").setIcon(android.R.drawable.alert_light_frame)
                .setPositiveButton("yse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(NotesActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(NotesActivity.this, "No", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void coustomAlertDialog() {



        AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
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

                            Toast.makeText(NotesActivity.this, "عملیات موفقت آمیز بود", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Log.i("miladrezaie", String.valueOf(call.request().headers()));
                            Log.i("miladrezaie", String.valueOf(call.request().body()));

                            Toast.makeText(NotesActivity.this, "ارور دارید در ثبت یادداشت", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoteResponseModel> call, Throwable t) {
                        Toast.makeText(NotesActivity.this, "سرور ارور دارید", Toast.LENGTH_SHORT).show();
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
