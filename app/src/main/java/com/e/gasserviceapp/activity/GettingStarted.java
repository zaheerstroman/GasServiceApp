package com.e.gasserviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.gasserviceapp.R;
import com.google.firebase.firestore.auth.User;

public class GettingStarted extends AppCompatActivity {

    TextView welcome_text, ca_1, login_1;
    ImageView fuil_Image;
    Button getting_Started_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        welcome_text = findViewById(R.id.welcome_text);
        ca_1 = findViewById(R.id.ca_1);
//        login_1 = findViewById(R.id.login_1);

        fuil_Image = findViewById(R.id.fuil_Image);
        getting_Started_Button = findViewById(R.id.getting_Started_Button);
        getting_Started_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GettingStarted.this, UserSignInActivity.class);
//                Intent intent = new Intent(GettingStarted.this, SigninActivity.class);
//                Intent intent = new Intent(GettingStarted.this, LoginActivity.class);
//                Intent intent = new Intent(GettingStarted.this, CountryActivity.class);
//                Intent intent = new Intent(GettingStarted.this, EnterOtpScreenOne.class);

                startActivity(intent);
                finish();

            }
        });


        login_1 = findViewById(R.id.login_1);
        login_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(GettingStarted.this, SigninActivity.class);
//                Intent intent = new Intent(GettingStarted.this, LoginActivity.class);
//                Intent intent = new Intent(GettingStarted.this, CountryActivity.class);
//                Intent intent = new Intent(GettingStarted.this, EnterOtpScreenOne.class);

                Intent intent = new Intent(GettingStarted.this, UserSignInActivity.class);


                startActivity(intent);
                finish();

            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)this).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)this).getSupportActionBar().show();
    }
}