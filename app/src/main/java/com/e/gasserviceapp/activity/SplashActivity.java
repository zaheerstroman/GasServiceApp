package com.e.gasserviceapp.activity;

import static com.e.gasserviceapp.utils.Constants.REQUEST_LOCATION;
import static com.e.gasserviceapp.utils.Constants.USER_ID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.SharedPrefs;

public class SplashActivity extends AppCompatActivity {

    private String TAG = SplashActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("data") != null) {


            //here can get notification message
            String datas = bundle.get("data").toString();
            Toast.makeText(this, datas, Toast.LENGTH_SHORT).show();

        }
        if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            launchApp();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //launch app
                launchApp();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void launchApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefs.getInstance(SplashActivity.this).getString(USER_ID).isEmpty()) {
                    Intent intent = new Intent(SplashActivity.this, GettingStarted.class);
//                    Intent intent = new Intent(SplashActivity.this, UserSignInActivity.class);
//                    Intent intent = new Intent(SplashActivity.this, MainActivityGas.class);

                    startActivity(intent);
                } else {

//                    SharedPrefs.getInstance(SplashAcivity.this).saveString(USER_ID,"106");
//                    SharedPrefs.getInstance(SplashAcivity.this).saveString(API_KEY,"04e796db4537599c4cf524c695cb203c04e8a7146c809adc66240e303809a5f4");
//                    SharedPrefs.getInstance(SplashAcivity.this).saveString(USER_MOBILE,"7674834030");

                    Log.e(TAG, "user_id: " + SharedPrefs.getInstance(SplashActivity.this).getString(USER_ID));
                    Log.e(TAG, "api_key: " + SharedPrefs.getInstance(SplashActivity.this).getString(Constants.API_KEY));
//                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    Intent intent = new Intent(SplashActivity.this, MainActivityGas.class);
//                    Intent intent = new Intent(SplashActivity.this, UserSignInActivity.class);

                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
    }

}