package com.e.gasserviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.e.gasserviceapp.AppSharedpref;
import com.e.gasserviceapp.R;

public class SignUpActivity extends AppCompatActivity {

    EditText firstnameid, lastnameid, mobilenumberid, Emailid, passwordid, conformpasswordid, createdatid;
    CheckBox checkboxid;
    Button loginAccount;
    RadioButton genderid, radioMale, radioFemale;
    RadioGroup radioGender;
    //    public String customers_gender="m";
//    ViewModal model;
    AppSharedpref appSharedpref;
    Context context = SignUpActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        appSharedpref = new AppSharedpref(context);

        firstnameid = findViewById(R.id.firstnameid);
        lastnameid = findViewById(R.id.lastnameid);
        mobilenumberid = findViewById(R.id.mobilenumberid);
        Emailid = findViewById(R.id.Emailid);

        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);

        passwordid = findViewById(R.id.passwordid);
        conformpasswordid = findViewById(R.id.conformpasswordid);

        checkboxid = findViewById(R.id.checkboxid);

        loginAccount = findViewById(R.id.loginAccount);

        radioGender = (RadioGroup) findViewById(R.id.radioGender);
        radioGender.clearCheck();

//        loginAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
//
//            }
//        });

        loginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName;
                String lastName;
                long mobileNumber;
                String eMail;
                String password;
                String conformpassword;
                String selectedSuperStar = null;
                if (radioMale.isChecked()) {
                    selectedSuperStar = radioMale.getText().toString();
                } else if (radioFemale.isChecked()) {
                    selectedSuperStar = radioFemale.getText().toString();
                }

                firstName = firstnameid.getText().toString();
                lastName = lastnameid.getText().toString().trim();
//                mobileNumber = Integer.parseInt(mobilenumberid.toString().trim());
                mobileNumber = Long.parseLong((mobilenumberid.getText().toString().trim()));
//                mobileNumber = mobilenumberid.getText().toString().trim();
                eMail = Emailid.getText().toString().trim();
                password = passwordid.getText().toString().trim();
                conformpassword = conformpasswordid.getText().toString().trim();

                if (checkboxid.isChecked()) {
                    //model.signupHomePageMethod(firstName, lastName, mobileNumber, gender, eMail, password, createdAt, selectedSuperStar);
                    createNewAcount(firstName, lastName, mobileNumber, eMail, password, selectedSuperStar);
                }
                if (password == conformpassword) {
//                     createNewAcount(firstname,email,lastname,password);
                } else {
                    Toast.makeText(getApplicationContext(), "please enter correct password", Toast.LENGTH_LONG).show(); // print the value of selected super star
                }
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);

            }
        });




    }

    private void createNewAcount(String firstName, String lastName, long mobileNumber, String eMail, String password, String selectedSuperStar) {
        if (TextUtils.isEmpty(firstName)) {
            firstnameid.setError("Please Enter Your First Name");
            firstnameid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            lastnameid.setError("Please Enter Your Last Name");
            lastnameid.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(mobileNumber))) {
            mobilenumberid.setError("Please Enter Mobile Number");
            mobilenumberid.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(eMail)) {
            Emailid.setError("Please Enter Email Id");
            Emailid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordid.setError("Please Enter Password");
            passwordid.requestFocus();
            return;
        }

//        model = ViewModelProviders.of(this).get(ViewModal.class);

//        String su = appSharedpref.getbilldata();
        String su = appSharedpref.getuserid();
//        String su = appSharedpref.getuserId();

//        Sample root = new Sample(firstName, lastName, mobileNumber, gender, email, password, createdAt, selectedSuperStar);




    }

}