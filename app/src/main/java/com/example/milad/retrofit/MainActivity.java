package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.milad.retrofit.model.AuthenticationResponseModel;
import com.example.milad.retrofit.model.Note;
import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.LoginApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private EditText input_username;
    private EditText input_password;

    private Button btn_login;
    private Button btngo_register;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_username = (EditText) findViewById(R.id.input_username);
        input_password = (EditText) findViewById(R.id.input_password);

        btn_login = (Button) findViewById(R.id.btn_login);
        btngo_register = (Button) findViewById(R.id.btngo_register);

        btngo_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginApiInterface loginApiInterface = ApiClient.getClient().create(LoginApiInterface.class);
                Call<AuthenticationResponseModel> call = loginApiInterface.login(input_username.getText().toString(), input_password.getText().toString());

                call.enqueue(new Callback<AuthenticationResponseModel>() {
                    @Override
                    public void onResponse(Call<AuthenticationResponseModel> call, Response<AuthenticationResponseModel> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                            User user = new User();
                            user.setUsername(input_username.getText().toString());

//                            Log.i("miladrezaie","username: "+user.getUsername());
//                            intent.putExtra("username", user.getUsername());
                            Note note = new Note(input_username.getText().toString());

                            ApiClient.access_token = response.body().getAccess_token();
                            ApiClient.user_id = note.getUser_id();

                            Log.i("miladrezaie", "token: " + ApiClient.access_token);
//                            Toast.makeText(MainActivity.this, response.body().getAccess_token(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "خطا ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthenticationResponseModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "متاسفانه سیستم قادر به پاسخ گویی نیسیت", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
