package com.e.gasserviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.e.gasserviceapp.R;

public class SplashTaksikuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashTaksikuActivity.this, OnBoardingTaksikuActivity.class));
                startActivity(new Intent(SplashTaksikuActivity.this, HomeTaksikuActivity.class));

                finish();
            }
        }, 5 * 1000);
    }
}