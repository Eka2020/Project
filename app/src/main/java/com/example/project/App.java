package com.example.project;

import android.app.Application;

import com.example.project.data.AppPref;
import com.example.project.data.BoredApiClient;

public class App extends Application {
    public static AppPref appPref;
    public static BoredApiClient boredApiClient;
    @Override
    public void onCreate() {
        super.onCreate();

        boredApiClient=new BoredApiClient();
    }
}
