package com.e.gasserviceapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.e.gasserviceapp.AppSharedpref;
import com.e.gasserviceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtpScreenTwo extends AppCompatActivity {
    //    textmobilenumbershow
    EditText inputnumberone, inputnumbertwo, inputnumberthree, inputnumberfour, inputnumberfive, inputnumbersix;
    //        inputotp1
    String getotpbackend;
    AppSharedpref appSharedpref;
    Context context = VerifyOtpScreenTwo.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp_screen_two);
        appSharedpref = new AppSharedpref(context);

        final Button verifybuttonclick = findViewById(R.id.buttongetotp);

        inputnumberone = findViewById(R.id.inputotp1);
        inputnumbertwo = findViewById(R.id.inputotp2);
        inputnumberthree = findViewById(R.id.inputotp3);
        inputnumberfour = findViewById(R.id.inputotp4);
        inputnumberfive = findViewById(R.id.inputotp5);
        inputnumbersix = findViewById(R.id.inputotp6);


        TextView textView = findViewById(R.id.textmobilenumbershow);
        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")));

        getotpbackend = getIntent().getStringExtra("backendotp");

        final ProgressBar progressBarverifyotp = findViewById(R.id.progressbar_verify_otp);

        verifybuttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputnumberone.getText().toString().trim().isEmpty() && !inputnumbertwo.getText().toString().trim().isEmpty() && !inputnumberthree.getText().toString().trim().isEmpty() && !inputnumberfour.getText().toString().trim().isEmpty() && !inputnumberfive.getText().toString().trim().isEmpty() && !inputnumbersix.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(VerifyOtpScreenTwo.this,"otp verify",Toast.LENGTH_SHORT).show();

                    String entercodeotp = inputnumberone.getText().toString() +
                            inputnumbertwo.getText().toString() +
                            inputnumberthree.getText().toString() +
                            inputnumberfour.getText().toString() +
                            inputnumberfive.getText().toString() +
                            inputnumbersix.getText().toString();

                    if (getotpbackend != null) {
                        progressBarverifyotp.setVisibility(View.VISIBLE);
                        verifybuttonclick.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend, entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarverifyotp.setVisibility(View.GONE);
                                        verifybuttonclick.setVisibility(View.VISIBLE);
                                        if (task.isSuccessful()) {
//                                            Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
//                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                            Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
//                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            Intent intent = new Intent(getApplicationContext(), MainActivityGas.class);



                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(VerifyOtpScreenTwo.this, "enter correct otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(VerifyOtpScreenTwo.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(VerifyOtpScreenTwo.this, "please enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberotpmove();
//        findViewById(R.id.textresendotp).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        TextView resendlabel = findViewById(R.id.textresendotp);
        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, VerifyOtpScreenTwo.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                progressBar.setVisibility(View.VISIBLE);
//                                getotpbutton.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                progressBar.setVisibility(View.VISIBLE);
//                                getotpbutton.setVisibility(View.INVISIBLE);
//                                        Toast.makeText(EnterOtpScreenOne.this,"Error Please check internet connection",Toast.LENGTH_SHORT).show();
                                Toast.makeText(VerifyOtpScreenTwo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        super.onCodeSent(s, forceResendingToken);

//                                progressBar.setVisibility(View.VISIBLE);
//                                getotpbutton.setVisibility(View.INVISIBLE);
//                                Intent intent =new Intent(getApplicationContext(),VerifyOtpScreenTwo.class);
////                                intent.putExtra("mobile",enternumber.getText().toString());
//                                intent.putExtra("mobile",backendotp);
//
//                                startActivity(intent);
                                getotpbackend = newbackendotp;
                                Toast.makeText(VerifyOtpScreenTwo.this, "otp sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void numberotpmove() {
        inputnumberone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    inputnumbertwo.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumbertwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    inputnumberthree.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumberthree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    inputnumberfour.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumberfour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    inputnumberfive.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumberfive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    inputnumbersix.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}