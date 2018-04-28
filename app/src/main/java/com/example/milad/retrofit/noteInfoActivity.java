package com.example.milad.retrofit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.NoteResponseModel;
import com.example.milad.retrofit.model.ResultsResponse;
import com.example.milad.retrofit.model.deleteResponseBodyModel;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.NoteInterface;

import java.util.List;

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
    private EditText edt_name, edt_desc;
    private Button update;
    private EditText edttitle;
    private EditText edtdescription;
    private TextView textviewsabt;
    private TextView textviewkhoroj;

    private String nameUP;
    private String descUp;


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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDeleteRequest(id);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coustomDialogUpdate(id);
            }
        });
        noteInfoRequset(id);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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
        final NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
        Call<String> call = noteInterface.deleteNote(token, id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(noteInfoActivity.this, "حذف شد", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(noteInfoActivity.this, NotesActivity.class);
                    startActivity(intent);

                    Toast.makeText(noteInfoActivity.this, "خطا در برنامه", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(noteInfoActivity.this, "ارتباط با سرور مقدور نیست", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void coustomDialogUpdate(final String ids) {
        AlertDialog.Builder builder = new AlertDialog.Builder(noteInfoActivity.this);
        View v = getLayoutInflater().inflate(R.layout.dialogupdate, null);

        builder.setView(v);

        final AlertDialog dialog = builder.create();
        dialog.show();

        edt_name = (EditText) dialog.findViewById(R.id.name);
        edt_desc = (EditText) dialog.findViewById(R.id.desc);
        update = (Button) dialog.findViewById(R.id.update);

        final Intent intent = getIntent();
        nameUP = intent.getExtras().getString("name");
        descUp = intent.getExtras().getString("desc");

        edt_name.setText(nameUP);
        edt_desc.setText(descUp);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NoteInterface noteInterface = ApiClient.getClient().create(NoteInterface.class);
                Call<Note> call = noteInterface.updateNote(token, ids, new Note(
                        ApiClient.getUser_id(),
                        edt_name.getText().toString(),
                        edt_desc.getText().toString()
                ));
                call.enqueue(new Callback<Note>() {
                    @Override
                    public void onResponse(Call<Note> call, Response<Note> response) {
                        if (response.isSuccessful()) {
                            Intent intent1 = new Intent(noteInfoActivity.this, NotesActivity.class);
                            startActivity(intent);
                            Toast.makeText(noteInfoActivity.this, "اوکی", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i("miladrezaie", call.request().headers().toString());
                            Log.i("miladrezaie", response.message());
                            Log.i("miladrezaie", String.valueOf(response.code()));
                            Toast.makeText(noteInfoActivity.this, "خطا", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Note> call, Throwable t) {
                        Toast.makeText(noteInfoActivity.this, "سرور", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
