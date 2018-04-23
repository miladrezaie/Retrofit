package com.example.milad.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.milad.retrofit.model.RegisterResponseModel;
import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.RegisterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText input_firstName;
    private EditText input_lastName;
    private EditText input_username;
    private EditText input_password;
    private EditText input_phoneNumber;
    private EditText input_email;

    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_firstName = (EditText) findViewById(R.id.input_firstName);
        input_lastName = (EditText) findViewById(R.id.input_lastName);
        input_username = (EditText) findViewById(R.id.input_username);
        input_password = (EditText) findViewById(R.id.input_password);
        input_phoneNumber = (EditText) findViewById(R.id.input_phoneNumber);
        input_email = (EditText) findViewById(R.id.input_email);

        btn_register=(Button)findViewById(R.id.btn_register);





        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterInterface registerInterface = ApiClient.getClient().create(RegisterInterface.class);
                Call<RegisterResponseModel> call = registerInterface.insertuser(
                        input_firstName.getText().toString(),
                        input_lastName.getText().toString(),
                        input_username.getText().toString(),
                        input_password.getText().toString(),
                        input_phoneNumber.getText().toString(),
                        input_email.getText().toString(),
                        null
                );

                call.enqueue(new Callback<RegisterResponseModel>() {
                    @Override
                    public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                        if (response.isSuccessful()) {

                        } else {
                            Toast.makeText(RegisterActivity.this, "متاسفانه سیستم قادر به پاسخ گویی نیسیت", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "متاسفانه سیستم قادر به پاسخ گویی نیسیت", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




    }
}
