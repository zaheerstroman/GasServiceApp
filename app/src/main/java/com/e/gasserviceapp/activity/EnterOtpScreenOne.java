package com.e.gasserviceapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.e.gasserviceapp.AppSharedpref;
import com.e.gasserviceapp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterOtpScreenOne extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;
    AppSharedpref appSharedpref;
    Context context = EnterOtpScreenOne.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp_screen_one);
        appSharedpref = new AppSharedpref(context);
        enternumber = findViewById(R.id.input_mobile_number);
        getotpbutton = findViewById(R.id.buttongetotp);

        ProgressBar progressBar = findViewById(R.id.progressbar_sending_otp);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enternumber.getText().toString().trim().isEmpty()){
                    if ((enternumber.getText().toString().trim()).length()==10){
                        appSharedpref.saveSerarchnumberurl(enternumber.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + enternumber.getText().toString(), 60, TimeUnit.SECONDS, EnterOtpScreenOne.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
//                                        Toast.makeText(EnterOtpScreenOne.this,"Error Please check internet connection",Toast.LENGTH_SHORT).show();
                                        Toast.makeText(EnterOtpScreenOne.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        super.onCodeSent(s, forceResendingToken);
                                        progressBar.setVisibility(View.VISIBLE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
                                        Intent intent =new Intent(getApplicationContext(), VerifyOtpScreenTwo.class);
                                        intent.putExtra("mobile",enternumber.getText().toString());
                                        intent.putExtra("mobile",backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );
                        Intent intent =new Intent(getApplicationContext(), VerifyOtpScreenTwo.class);
                        intent.putExtra("mobile",enternumber.getText().toString());
                        startActivity(intent);
                    }else {
                        Toast.makeText(EnterOtpScreenOne.this,"Please enter the correct text",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(EnterOtpScreenOne.this,"enter mobile number",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}