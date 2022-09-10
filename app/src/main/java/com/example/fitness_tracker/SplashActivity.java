package com.example.fitness_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SaveSharedPreference.getUserName(SplashActivity.this).length() == 0)
                {
                    // User isn't logged in -> create account screen
                    startActivity(new Intent(SplashActivity.this, CreateAccountActivity.class));
                }
                else
                {
                    // User already logged in -> main activity
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 1500);
    }
}