package com.e.gasserviceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.UserRegResponseGasaverTProperty;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.utils.CommonUtils;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignInActivity extends AppCompatActivity implements TextWatcher {

    EditText et_email, et_mobile, et_name;
    ImageView iv_back,btn_continue ;
//    Button btn_continue;
//    ImageView iv_back;
    TextView tv_terms_cond;
    LinearLayout ll_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_sign_in);
        setContentView(R.layout.activity_login_gstp);


        init();

    }

    private void init() {

        ll_terms = findViewById(R.id.ll_terms);
        tv_terms_cond = findViewById(R.id.tv_terms_cond);

//        et_email = findViewById(R.id.et_email);
//        et_name = findViewById(R.id.et_name);

        et_mobile = findViewById(R.id.et_mobile);

        btn_continue = findViewById(R.id.btn_continue);

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_terms_cond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
//                startActivity(i);
            }
        });

//        String text = "Name ";
        String text = "Mobile ";
        String redpart = "*";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text);
        SpannableString redColoredString = new SpannableString(redpart);
        redColoredString.setSpan(new ForegroundColorSpan(Color.RED), 0, redpart.length(), 0);
        builder.append(redColoredString);
//        et_name.setHint(builder);

//        text = "Mobile ";
        text = "EmailId or Mobile Number";
        builder = new SpannableStringBuilder();
        builder.append(text);
        builder.append(redColoredString);
        et_mobile.setHint(builder);

//        text = "EmailId ";
        builder = new SpannableStringBuilder();
        builder.append(text);
//        builder.append(redColoredString);

//        et_name.addTextChangedListener(this);
//        et_email.addTextChangedListener(this);
        et_mobile.addTextChangedListener(this);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsValidated()) {
                    getFCMToken();
                }
            }
        });

    }

    private boolean isAllFieldsValidated() {
//        if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
//            et_name.setError("Not Valid");
//            return false;
//        }

//        if (TextUtils.isEmpty(et_mobile.getText().toString().trim()) || et_mobile.getText().toString().length() != 10 || !Patterns.PHONE.matcher(et_mobile.getText().toString()).matches()) {
//            et_mobile.setError("Not Valid");
//            return false;
//        }

        if (TextUtils.isEmpty(et_mobile.getText().toString().trim()) || et_mobile.getText().toString().length() != 10 || !Patterns.PHONE.matcher(et_mobile.getText().toString()).matches()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(et_mobile.getText().toString()).matches()) {
//                et_email.setError("Not Valid");
                et_mobile.setError("Not Valid");
                return false;
            }
        }


        ////to

//                et_mobile.setError("Not Valid");
//                return false;
//            }
//        }


//        if (!TextUtils.isEmpty(et_email.getText().toString().trim())) {
//            if (!Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
//                et_email.setError("Not Valid");
//                return false;
//            }
//        }

        return true;
    }

    private void userLogin(String token) {
        CommonUtils.showLoading(UserSignInActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

//                .addFormDataPart("type", "login")
//                .addFormDataPart("username", et_name.getText().toString())

                .addFormDataPart("mobile", et_mobile.getText().toString())
                .addFormDataPart("mobile_number", et_mobile.getText().toString())
//                .addFormDataPart("email", et_email.getText().toString())
                .addFormDataPart("email", et_mobile.getText().toString())
                .addFormDataPart("fcm_token", token)

//                .addFormDataPart("role", String.valueOf(getIntent().getIntExtra("id", 6)))
//                .addFormDataPart("id", String.valueOf(getIntent().getIntExtra("id", 6)))
//                .addFormDataPart("id", String.valueOf(getIntent().getIntExtra("id", 1)))


                .build();

//        Call<UserRegResponse> call = apiInterface.userLogin(requestBody);
//        Call<UserRegResponseGasaver> call = apiInterface.userLogin(requestBody);
        Call<UserRegResponseGasaverTProperty> call = apiInterface.userLogin(requestBody);

//        call.enqueue(new Callback<UserRegResponse>() {
        call.enqueue(new Callback<UserRegResponseGasaverTProperty>() {

            @Override
            public void onResponse(Call<UserRegResponseGasaverTProperty> call, Response<UserRegResponseGasaverTProperty> response) {

//                if (response.body() != null && response.body().getStatus()) {
                if (response.body() != null && response.body().getStatusCode()) {
//                if (response.body() != null && response.body().getMessage()) {


                    CommonUtils.hideKeyboard(UserSignInActivity.this);
//                    getUserDetails Means result
//                    if (response.body().getUserDetails() != null && !response.body().getUserDetails().isEmpty()) {
                    if (response.body().getMessage() != null && !response.body().getMessage().isEmpty()) {

//                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_ID, response.body().getUserDetails().get(0).getUser_id());
//                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_MOBILE, response.body().getUserDetails().get(0).getUser_mobile());
//                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.API_KEY, response.body().getUserDetails().get(0).getApi_token());
//                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.TOKEN, response.body().getUserDetails().get(0).getApi_token());
//                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_EMAIL, et_mobile.getText().toString());
//                        or

                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.MOBILE_EMAIL, et_mobile.getText().toString());
                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_MOBILE, et_mobile.getText().toString());
                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_EMAIL, et_mobile.getText().toString());

                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.FCM_TOKEN, et_mobile.getText().toString());

//                        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_NAME, et_name.getText().toString());
//                        Intent intent = new Intent(UserSignInActivity.this, OtpActivitySonalNaidu.class);

                        Intent intent = new Intent(UserSignInActivity.this, OtpActivity.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(UserSignInActivity.this, "No User Details Found", Toast.LENGTH_SHORT).show();
                    }

                }
                CommonUtils.hideLoading();
//                if (response.body().getMsg() != null)
//                    Toast.makeText(UserSignInActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                if (response.body().getMessage() != null)
                    Toast.makeText(UserSignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


//                if (response.body().getMessage() == null)
//                    Toast.makeText(UserSignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserRegResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
                t.printStackTrace();
                Toast.makeText(UserSignInActivity.this, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getFCMToken() {
        FirebaseApp.initializeApp(UserSignInActivity.this);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.d("firebase", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.d("firebase", "token" + token);
                        userLogin(token);
                    }
                });
    }

//    private void enableButtonIfReady() {
//        if (!TextUtils.isEmpty(et_name.getText().toString().trim()) && !TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
//            btn_continue.setVisibility(View.VISIBLE);
//            ll_terms.setVisibility(View.VISIBLE);
//        } else {
//            btn_continue.setVisibility(View.GONE);
//            ll_terms.setVisibility(View.GONE);
//        }
//    }

    private void enableButtonIfReady() {
        if (!TextUtils.isEmpty(et_mobile.getText().toString().trim()) ) {
            btn_continue.setVisibility(View.VISIBLE);
            ll_terms.setVisibility(View.VISIBLE);
        } else {
            btn_continue.setVisibility(View.GONE);
            ll_terms.setVisibility(View.GONE);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        enableButtonIfReady();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}