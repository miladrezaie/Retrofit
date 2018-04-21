package com.example.milad.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.milad.retrofit.model.User;
import com.example.milad.retrofit.model.UserResponse;
import com.example.milad.retrofit.webService.ApiClient;
import com.example.milad.retrofit.webService.ApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call=apiInterface.getUsers();

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    List<User> users=response.body().getUsers();
                    for (User user:users){
                        Log.i("RETROFIT", String.valueOf(user.getId()));
                        Log.i("RETROFIT",user.getName());
                        Log.i("RETROFIT",user.getEmail());
                    }

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                if (t instanceof IOException){
                    Toast.makeText(MainActivity.this,"errror",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
