package com.e.gasserviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.gasserviceapp.AppSharedpref;
import com.e.gasserviceapp.R;

public class SigninActivity extends AppCompatActivity {

    //    public static String userName, password;
    public static String email_phone_userName, password_password;
    AppSharedpref appSharedpref;
    Context context = SigninActivity.this;
    String a = "add";


    ImageView login_facebook, login_instagram, login_twitter, fuil_Image;
    TextView gdfghfjfg, login_Text2, login_Text1, forget_TextView, or_textView2, login_with_social_media_textView1;
    EditText email_phone, password;
    Button login_fuil_Btn, signupAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signin);
        setContentView(R.layout.activity_login1);

        appSharedpref = new AppSharedpref(context);


//        login_facebook = findViewById(R.id.login_facebook);
//        login_instagram = findViewById(R.id.login_instagram);
//        login_twitter = findViewById(R.id.login_twitter);


        login_Text2 = findViewById(R.id.login_Text2);
        login_Text1 = findViewById(R.id.login_Text1);
        forget_TextView = findViewById(R.id.forget_TextView);
        or_textView2 = findViewById(R.id.or_textView2);
        login_with_social_media_textView1 = findViewById(R.id.login_with_social_media_textView1);

        email_phone = findViewById(R.id.email_phone);
        password = findViewById(R.id.password);

        signupAccount = findViewById(R.id.signupAccount);


        fuil_Image = findViewById(R.id.fuil_Image);
        fuil_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_phone_userName = email_phone.getText().toString();
                password_password = password.getText().toString().trim();
//                loginAcount(userName, password);
                appSharedpref.savepassworddata(password_password);
//                Intent intent = new Intent(getApplicationContext(), SetProfile.class);
                Intent loginIntent = new Intent(SigninActivity.this, CountryActivity.class);
//                Intent loginIntent = new Intent(SigninActivity.this, CountryActivity.class);
//                Intent loginIntent = new Intent(SigninActivity.this, OtpAuthentication.class);
                SigninActivity.this.startActivity(loginIntent);
                SigninActivity.this.finish();
            }


        });


//        login_fuil_Btn = findViewById(R.id.login_fuil_Btn);

//        login_fuil_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                email_phone_userName = email_phone.getText().toString();
//                password_password = password.getText().toString().trim();
////                loginAcount(userName, password);
//                appSharedpref.savepassworddata(password_password);
////                Intent intent = new Intent(getApplicationContext(), SetProfile.class);
//                Intent loginIntent = new Intent(SigninActivity.this, CountryActivity.class);
////                Intent loginIntent = new Intent(SigninActivity.this, CountryActivity.class);
////                Intent loginIntent = new Intent(SigninActivity.this, OtpAuthentication.class);
//                SigninActivity.this.startActivity(loginIntent);
//                SigninActivity.this.finish();
//            }
//
//
//        });

        signupAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

//        Intent mainIntent = new Intent(SigninActivity.this, MainActivity.class);
//        Intent mainIntent = new Intent(SigninActivity.this, CountryActivity.class);


        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        login_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/intl/en-GB/gmail/about/#"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        login_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://twitter.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

//        Intent mainIntent = new Intent(SigninActivity.this, MainActivity.class);
        Intent mainIntent = new Intent(SigninActivity.this, NavigationActivity.class);




    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) this).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) this).getSupportActionBar().show();
    }


}