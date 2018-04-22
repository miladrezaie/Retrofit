package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.milad.retrofit.model.AuthenticationResponseModel;
import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.model.UserResponse;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.ApiInterface;
import com.example.milad.retrofit.webService.LoginApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private EditText input_username;
    private EditText input_password;
    private Button btn_login;
    private Button btn_register;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_username = (EditText) findViewById(R.id.input_username);
        input_password = (EditText) findViewById(R.id.input_password);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginApiInterface loginApiInterface = ApiClient.getClient().create(LoginApiInterface.class);

                Call<AuthenticationResponseModel> call = loginApiInterface.login(input_username.getText().toString(), input_password.getText().toString());
                call.enqueue(new Callback<AuthenticationResponseModel>() {
                    @Override
                    public void onResponse(Call<AuthenticationResponseModel> call, Response<AuthenticationResponseModel> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this,NotesActivity.class);
                            intent.putExtra("responseToken",response.body().getAccess_token());

                            Toast.makeText(MainActivity.this, response.body().getAccess_token(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "qqqqqqqqqqqqqqqqqq", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthenticationResponseModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "mmmmmmmmm", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

}