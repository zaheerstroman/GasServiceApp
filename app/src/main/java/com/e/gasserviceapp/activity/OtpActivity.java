package com.e.gasserviceapp.activity;

import static com.e.gasserviceapp.utils.Constants.MOBILE_EMAIL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.BaseResponseGasaverTProperty;
import com.e.gasserviceapp.Response.OtpResponseGasaverTProperty;
import com.e.gasserviceapp.customviews.Pinview;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.utils.CommonUtils;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.SharedPrefs;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_submit, tv_otp_resend, tv_resend_otp_timer, tv_resend_code;
    ImageView iv_back;

    Pinview tv_pin_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
//        setContentView(R.layout.activity_otp);



        init();
    }

    private void init() {

        tv_pin_otp = findViewById(R.id.tv_pin_otp);
        tv_pin_otp.requestPinEntryFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tv_pin_otp.isVisibleToUserForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WAKE_LOCK},
//                    REQUEST_PERMISSION);
//
//        }


        iv_back = findViewById(R.id.iv_back);
        tv_otp_resend = findViewById(R.id.tv_otp_resend);
        tv_resend_otp_timer = findViewById(R.id.tv_resend_otp_timer);

        tv_submit = findViewById(R.id.tv_submit);

        iv_back.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        tv_otp_resend.setOnClickListener(this);
        startTimer(60000, 1000);
    }

//    @Override
//    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERMISSION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted.
//            } else {
//                // User refused to grant permission.
//            }
//        }
//    }

    public void startTimer(final long finish, long tick) {
        tv_otp_resend.setEnabled(false);
        tv_otp_resend.setClickable(false);
        new CountDownTimer(finish, tick) {

            public void onTick(long millisUntilFinished) {
                long remainedSecs = millisUntilFinished / 1000;
                String stringTime = String.format("%02d:%02d", (remainedSecs / 60), (remainedSecs % 60));
                tv_resend_otp_timer.setText(stringTime);// manage it according to you
            }

            public void onFinish() {
                tv_resend_otp_timer.setText("00:00");
                tv_otp_resend.setEnabled(true);
                tv_otp_resend.setClickable(true);
                cancel();
            }
        }
                .start();
    }

    private void verifyOtp() {
        CommonUtils.showLoading(OtpActivity.this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

//                .addFormDataPart("type", "otp_verify")
//                .addFormDataPart("id", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.ID))
//                .addFormDataPart("user_id", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_ID))
//                .addFormDataPart("name", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.NAME))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_MOBILE))
//                .addFormDataPart("mobile_number", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_MOBILE))

                .addFormDataPart("mobile", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.MOBILE))
                .addFormDataPart("mobile", SharedPrefs.getInstance(OtpActivity.this).getString(MOBILE_EMAIL))
                .addFormDataPart("mobile_number", SharedPrefs.getInstance(OtpActivity.this).getString(MOBILE_EMAIL))
                .addFormDataPart("email", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.EMAIL))
                .addFormDataPart("api_key", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.API_KEY))
                .addFormDataPart("token", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.TOKEN))
                .addFormDataPart("fcm_token", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.FCM_TOKEN))
                .addFormDataPart("user_code", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_CODE))
                .addFormDataPart("department_id", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.DEPARTMENT_ID))

                .addFormDataPart("otp", tv_pin_otp.getValue())
                .build();

//        Call<OtpResponse> call = apiInterface.verifyOtp(requestBody);
//        Call<OtpResponseGasaver> call = apiInterface.verifyOtp(requestBody);
        Call<OtpResponseGasaverTProperty> call = apiInterface.verifyOtp(requestBody);

        call.enqueue(new Callback<OtpResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<OtpResponseGasaverTProperty> call, Response<OtpResponseGasaverTProperty> response) {
                CommonUtils.hideLoading();
                CommonUtils.hideKeyboard(OtpActivity.this);

//                if (response.body().getStatus()) {
//                if (response.body().getMessage()) {
                if (response.body().getStatusCode()) {

                    saveDetailsInSharedPrefs(response.body());

//                    if (SharedPrefs.getInstance(OtpActivity.this).getString(USER_MOBILE).equals(USER_MOBILE)) {
//                        Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                    }

                    if (SharedPrefs.getInstance(OtpActivity.this).getString(MOBILE_EMAIL).equals(MOBILE_EMAIL)) {
//                        Intent intent = new Intent(OtpActivity.this, HomeActivity.class);
//                        Intent intent = new Intent(OtpActivity.this, MainActivityGeeks.class);
                        Intent intent = new Intent(OtpActivity.this, MainActivityGas.class);
//                        Intent intent = new Intent(OtpActivity.this, MainActivity.class);

                        //Intent intent = new Intent(OtpActivity.this, MainActivityGas.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
//                        Intent intent = new Intent(OtpActivity.this, UserRequirementActivity.class);
//                        Intent intent = new Intent(OtpActivity.this, MainActivityGeeks.class);
                        Intent intent = new Intent(OtpActivity.this, MainActivityGas.class);
//                        Intent intent = new Intent(OtpActivity.this, MainActivity.class);

                        startActivity(intent);
                    }
                }

//                else if (response.body().getMsg()!=null)
//                    Toast.makeText(OtpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                else if (response.body().getMessage() != null)
                    Toast.makeText(OtpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OtpResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();

//                Intent intent = new Intent(OtpActivity.this, RegInfo3Activity.class);
//                startActivity(intent);
            }
        });

    }

    private void saveDetailsInSharedPrefs(OtpResponseGasaverTProperty otpResponse) {
//    private void saveDetailsInSharedPrefs(OtpResponseGasaver otpResponse) {
        SharedPrefs sharedPrefs = SharedPrefs.getInstance(OtpActivity.this);

//        sharedPrefs.saveString(Constants.USER_CODE, otpResponse.getOtpResModels().get(0).getUserCode());
//        sharedPrefs.saveString(Constants.USER_PKG_TITLE, otpResponse.getOtpResModels().get(0).getTitle());
//        sharedPrefs.saveString(Constants.USER_PKG_DUR, otpResponse.getOtpResModels().get(0).getDuration());
//        sharedPrefs.saveInt(Constants.USER_MSG_ALLOWED, otpResponse.getOtpResModels().get(0).getMessagesAllowed());
//        sharedPrefs.saveInt(Constants.USER_PROP_ALLOWED, otpResponse.getOtpResModels().get(0).getPropertiesAllowed());
//        sharedPrefs.saveInt(Constants.USER_CALLS_ALLOWED, otpResponse.getOtpResModels().get(0).getCallsAllowed());
//        sharedPrefs.saveInt(Constants.USER_IMGS_ALLOWED, otpResponse.getOtpResModels().get(0).getImageAllowed());
//        sharedPrefs.saveInt(Constants.USER_VIDEOS_ALLOWED, otpResponse.getOtpResModels().get(0).getVideosAllowed());
//        sharedPrefs.saveInt(Constants.TOTAL_USER, otpResponse.getOtpResModels().get(0).getTotal_user());
//        getUserDetails
//        sharedPrefs.saveString(Constants.USER_PKG_TITLE, otpResponse.getUserDetails().get(0).getTitle());
//        sharedPrefs.saveString(Constants.USER_PKG_DUR, otpResponse.getUserDetails().get(0).getDuration());
//        sharedPrefs.saveInt(Constants.USER_MSG_ALLOWED, otpResponse.getUserDetails().get(0).getMessagesAllowed());
//        sharedPrefs.saveInt(Constants.USER_PROP_ALLOWED, otpResponse.getUserDetails().get(0).getPropertiesAllowed());
//        sharedPrefs.saveInt(Constants.USER_CALLS_ALLOWED, otpResponse.getUserDetails().get(0).getCallsAllowed());
//        sharedPrefs.saveInt(Constants.USER_IMGS_ALLOWED, otpResponse.getUserDetails().get(0).getImageAllowed());
//        sharedPrefs.saveInt(Constants.USER_VIDEOS_ALLOWED, otpResponse.getUserDetails().get(0).getVideosAllowed());
//        sharedPrefs.saveInt(Constants.TOTAL_USER, otpResponse.getUserDetails().get(0).getTotal_user());

        //--------------------------------------------------------------------------------------------------------

        sharedPrefs.saveString(Constants.FCM_TOKEN, (String) otpResponse.getUserDetails().get(0).getFcmToken());
        sharedPrefs.saveString(Constants.STATUS_CODE, String.valueOf(otpResponse.getStatusCode()));
//        sharedPrefs.saveString(Constants.STATUS_CODE,otpResponse.getStatusCode());
//        sharedPrefs.saveString(Constants.STATUS_CODE, otpResponse.get(0).getStatusCode());
//        sharedPrefs.saveString(Constants.ISVERIFIED, otpResponse.getUserDetails().get(0).getIsVerified());
//        sharedPrefs.saveString(Constants.MESSAGE, otpResponse.getUserDetails().get(0).getMessage());
        sharedPrefs.saveString(Constants.MESSAGE, otpResponse.getMessage());

        //V Says ID Not Required
//        sharedPrefs.saveString(Constants.ID, String.valueOf((Integer) otpResponse.getUserDetails().get(0).getId()));
        sharedPrefs.saveString(Constants.DEPARTMENT_ID, String.valueOf((Integer) otpResponse.getUserDetails().get(0).getDepartmentId()));
        sharedPrefs.saveString(Constants.USER_CODE, otpResponse.getUserDetails().get(0).getUserCode());
//        sharedPrefs.saveString(Constants.NAME, otpResponse.getUserDetails().get(0).getName());
        sharedPrefs.saveString(Constants.USER_MOBILE, otpResponse.getUserDetails().get(0).getMobile());
        sharedPrefs.saveString(MOBILE_EMAIL, otpResponse.getUserDetails().get(0).getMobile());
//        sharedPrefs.saveString(Constants.EMAIL, otpResponse.getUserDetails().get(0).getEmail());
//        sharedPrefs.saveString(Constants.GENDER, otpResponse.getUserDetails().get(0).getGender());
//        sharedPrefs.saveString(Constants.DOB, otpResponse.getUserDetails().get(0).getDob());
//        sharedPrefs.saveString(Constants.CITY, otpResponse.getUserDetails().get(0).getCity());
//        sharedPrefs.saveString(Constants.STATE, otpResponse.getUserDetails().get(0).getState());
//        sharedPrefs.saveString(Constants.COUNTRY, otpResponse.getUserDetails().get(0).getCountry());
        sharedPrefs.saveString(Constants.STATUS, otpResponse.getUserDetails().get(0).getStatus());
        sharedPrefs.saveString(Constants.MOBILE_VERIFIED, otpResponse.getUserDetails().get(0).getMobileVerified());
//        sharedPrefs.saveString(Constants.IS_VERIFIED, otpResponse.getUserDetails().get(0).getIsVerified());
        sharedPrefs.saveString(Constants.NO_VENDOR, otpResponse.getUserDetails().get(0).getNoVendor());

        //Vineela Vardhireddy
        sharedPrefs.saveString(Constants.USER_ID, String.valueOf(otpResponse.getUserDetails().get(0).getId()));
        sharedPrefs.saveString(Constants.IS_VERIFIED, otpResponse.getUserDetails().get(0).getIsVerified());
        sharedPrefs.saveString(Constants.LOGGEDIN, String.valueOf(otpResponse.getLoggedIn()));

        sharedPrefs.saveString(Constants.TOKEN, (String) otpResponse.getToken());

//        sharedPrefs.saveString(Constants.FCM_TOKEN, otpResponse.getUserDetails().get(0).getFcmToken());
//        sharedPrefs.saveString(Constants.TOKEN, otpResponse.getUserDetails().get(0).getToken());
    }

    private void resendOtp() {
        CommonUtils.showLoading(OtpActivity.this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)

//                .addFormDataPart("type", "resend_otp")

//                .addFormDataPart("user_id", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_ID))

//                -----------

//                .addFormDataPart("id", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.ID))

                .addFormDataPart("api_key", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.API_KEY))
                .addFormDataPart("token", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.TOKEN))
                .addFormDataPart("fcm_token", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.FCM_TOKEN))

                .addFormDataPart("mobile", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.MOBILE))
                .addFormDataPart("mobile_number", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_MOBILE))
                .addFormDataPart("mobile_number", SharedPrefs.getInstance(OtpActivity.this).getString(MOBILE_EMAIL))

                .addFormDataPart("email", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.EMAIL))

//                .addFormDataPart("status", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.STATUS))
//                .addFormDataPart("mobile_verified", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.MOBILE_VERIFIED))
//                .addFormDataPart("is_verified", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.ISVERIFIED))
//                .addFormDataPart("is_verified", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.IS_VERIFIED))
//                .addFormDataPart("no_vendor", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.NO_VENDOR))
//                .addFormDataPart("loggedIn", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.LOGGEDIN))
//                .addFormDataPart("message", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.MESSAGE))

//-------

                //Send OTP Copy form to Resend OTP

                .addFormDataPart("fcm_token", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.FCM_TOKEN))
                .addFormDataPart("user_code", SharedPrefs.getInstance(OtpActivity.this).getString(Constants.USER_CODE))
                .addFormDataPart("otp", tv_pin_otp.getValue())
                .build();

//        Call<BaseResponse> call = apiInterface.resendOtp(requestBody);
//        Call<BaseResponseGasaver> call = apiInterface.resendOtp(requestBody);
        Call<BaseResponseGasaverTProperty> call = apiInterface.resendOtp(requestBody);

        call.enqueue(new Callback<BaseResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<BaseResponseGasaverTProperty> call, Response<BaseResponseGasaverTProperty> response) {
                CommonUtils.hideLoading();
                startTimer(60000, 1000);
            }

            @Override
            public void onFailure(Call<BaseResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_otp_resend:
                resendOtp();
                break;
            case R.id.tv_submit:
                if (tv_pin_otp.getValue().length() == 4)
                    verifyOtp();
                else
                    Toast.makeText(this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}