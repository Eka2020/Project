package com.example.project.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.project.App;
import com.example.project.data.AppPref;
import com.example.project.R;
import com.example.project.data.BoredApiClient;
import com.example.project.model.BoredAction;
import com.example.project.presentation.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isShow = AppPref.getInstance(this).isShown();
        if (!isShow) {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        App.boredApiClient.getAction(new BoredApiClient.BoredActionCallback() {
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.d("ololo", boredAction.toString());
            }

            @Override
            public void onFailure(Exception exception) {
                Log.d("ololo", exception.getMessage());
            }
        });
    }
}