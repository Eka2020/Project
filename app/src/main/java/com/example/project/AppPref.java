package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPref {
    private SharedPreferences sharedPreferences;
    private  static volatile AppPref place;
    public AppPref(Context context){
        place =this;
        sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }
    public static AppPref getInstance(Context context){
        if (place == null) new AppPref(context);
        return place;
    }

    public boolean isShown(){
        return sharedPreferences.getBoolean("isShown",false);
    }
    public void saveShown(){
        sharedPreferences.edit().putBoolean("isShown",true).apply();
    }
}















