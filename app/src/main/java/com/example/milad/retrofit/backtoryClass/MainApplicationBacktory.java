package com.example.milad.retrofit.backtoryClass;

import android.app.Application;

import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.KeyConfiguration;


public class MainApplicationBacktory extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initializing backtory

        BacktoryClient.init(KeyConfiguration.newBuilder().
                // Enabling User Services
                        setAuthKeys("5a9314fbe4b04e579ee1edbe",
                        "5a9314fbe4b05bb64131ee38").
                        setObjectStorageKey("5a9314fce4b092a32b632af9").
                // Finalizing sdk
                        build(), this);
    }
}
