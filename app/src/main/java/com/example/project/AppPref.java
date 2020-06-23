package com.example.project;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPref {
    private SharedPreferences sharedPreferences;
    private  static volatile AppPref instance;
    public AppPref(Context context){
        instance =this;
        sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }

    public static AppPref getInstance(Context context){
        if (instance == null) new AppPref(context);
        return instance;
    }

    public boolean isShown(){
        return sharedPreferences.getBoolean("isShown",false);
    }
    public void saveShown(){
        sharedPreferences.edit().putBoolean("isShown",true).apply();
    }
}















