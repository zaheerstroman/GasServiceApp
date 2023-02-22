package com.e.gasserviceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.databinding.ActivityLoginBinding;
import com.e.gasserviceapp.utility.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivityGas extends AppCompatActivity {

//    Context context;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_gas);
//    }


    private ActivityLoginBinding binding;
    private String email, password;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Stack Over Flow
//        getPackageManager().getLaunchIntentForPackage("com.e.gasserviceapp");
//        getPackageManager().getLaunchIntentForPackage("com.android.chrome");


//        PackageManager manager = context.getPackageManager();




        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = new LoadingDialog(this);

        binding.btnSignUp.setOnClickListener(view -> {
//            startActivity(new Intent(LoginActivityGas.this, SignUpActivity.class));
            startActivity(new Intent(LoginActivityGas.this, SignUpActivityGas.class));

        });

        binding.txtForgetPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivityGas.this, ForgetActivity.class));
//            startActivity(new Intent(LoginActivityGas.this, SignUpActivityGas.class));

        });
        binding.btnLogin.setOnClickListener(view -> {
            if (areFieldReady()) {
                login();
            }
        });
    }

    private void login() {
        loadingDialog.startLoading();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();




        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                        loadingDialog.stopLoading();
//                        Intent intent = new Intent(LoginActivityGas.this, MainActivity.class);
                        Intent intent = new Intent(LoginActivityGas.this, MainActivityGas.class);

                        startActivity(intent);
                        finish();

                    } else {

                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> email) {
                                if (email.isSuccessful()) {
                                    loadingDialog.stopLoading();
                                    Toast.makeText(LoginActivityGas.this, "Please verify email", Toast.LENGTH_SHORT).show();
                                } else {
                                    loadingDialog.stopLoading();
                                    Toast.makeText(LoginActivityGas.this, "Error : " + email.getException(), Toast.LENGTH_SHORT).show();

//                                    base64_signer_key: lt6vRgwZdC5aZaciIQvtUYFXl2Ic5UedPt4T8nE3PocXiv7wFsRYHg375bKISwqiZxjWLzh/IBN/MgdkA85GBQ==,
//                                            base64_salt_separator: Bw==,

                                }
                            }
                        });
                    }

                } else {
                    loadingDialog.stopLoading();
                    Toast.makeText(LoginActivityGas.this, "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean areFieldReady() {

        email = binding.edtEmail.getText().toString().trim();
        password = binding.edtPassword.getText().toString().trim();

        boolean flag = false;
        View requestView = null;

        if (email.isEmpty()) {
            binding.edtEmail.setError("Field is required");
            flag = true;
            requestView = binding.edtEmail;
        } else if (password.isEmpty()) {
            binding.edtPassword.setError("Field is required");
            flag = true;
            requestView = binding.edtPassword;
        } else if (password.length() < 8) {
            binding.edtPassword.setError("Minimum 8 characters");
            flag = true;
            requestView = binding.edtPassword;
        }

        if (flag) {
            requestView.requestFocus();
            return false;
        } else {
            return true;
        }

    }

//    firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
//        // Handle Errors here.
//        var errorCode = error.code;
//        var errorMessage = error.message;
//        // ...
//    });

}