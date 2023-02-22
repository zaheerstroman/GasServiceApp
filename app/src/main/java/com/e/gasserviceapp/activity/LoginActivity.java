package com.e.gasserviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.gasserviceapp.AppSharedpref;
import com.e.gasserviceapp.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class LoginActivity extends AppCompatActivity {

    Button register;
    EditText username, password, email_phone;
    Button loginButton, login_fuil_Btn, signupAccount;
    String user, pass;
    TextView show_psd_tv;
//    ImageView show_psd_tv, login_show;

    //    FACEBOOK
//    private LoginButton b;
//    private TextView e;
//    private CallbackManager c;
//    GoogleSignInClient mGoogleSignInClient;
//    private static int RC_SIGN_IN = 100;
    public LoginButton b;
    public TextView e;
    public CallbackManager c;
    GoogleSignInClient mGoogleSignInClient;
    public static int RC_SIGN_IN = 100;

    CallbackManager callbackManager;
    ImageView fbBtn;

    //    private static final String EMAIL = "email";
    public static final String EMAIL = "email";


    public static String email_phone_userName, password_password;
    AppSharedpref appSharedpref;
    Context context = LoginActivity.this;
    String a = "add";


    ImageView login_facebook, login_instagram, login_twitter, fuil_Image, fuil_Image1;
    TextView gdfghfjfg, login_Text2, login_Text1, forget_TextView, or_textView2, login_with_social_media_textView1;
//    EditText email_phone, password;
//    Button login_fuil_Btn, signupAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        appSharedpref = new AppSharedpref(context);

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        FacebookSdk.sdkinitialize(getApplicationContext());


        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);


        login_facebook = findViewById(R.id.login_facebook);
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


//        fuil_Image1 = findViewById(R.id.fuil_Image1);

//        ---------------

//        register = findViewById(R.id.register);

//        username = findViewById(R.id.username);
        username = findViewById(R.id.email_phone);

        password = findViewById(R.id.password);
//        loginButton = findViewById(R.id.login_fuil_Btn);


        //-------------------

        // My Online Pet Clinic
        final TextView showPasswordTv = (TextView) findViewById(R.id.show_psd_tv);
//        final ImageView showPasswordTv = (ImageView) findViewById(R.id.show_psd_tv);


        //------------------


        //   FACEBOOK     --------------------------

//        e=findViewById(R.id.e);

        b = findViewById(R.id.b);
//fb
        c = CallbackManager.Factory.create();
//    b.setPermissions(Arrays.asList("Email","public_profile"));


        callbackManager = CallbackManager.Factory.create();

//        Facebook Button
//        loginButton = (LoginButton) findViewById(R.id.b);
//        b = findViewById(R.id.b);

//        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        b.setReadPermissions(Arrays.asList(EMAIL));

//        try{
////            PackageInfo info = getPackageManager().getPackageInfo("MY PACKAGE NAME", PackageManager.GET_SIGNATURES);
//            PackageInfo info = getPackageManager().getPackageInfo("com.e.gasserviceapp.activity.loginactivity", PackageManager.GET_SIGNATURES);
//            for(android.content.pm.Signature signature : info.signatures)
//            {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String sign=Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                Log.e("MY KEY HASH:", sign);
//            }
//        } catch (PackageManager.NameNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (NoSuchAlgorithmException ex) {
//            ex.printStackTrace();
//        }

//        --------------

//        try {
//                PackageInfo info =     getPackageManager().getPackageInfo("MY PACKAGE NAME",     PackageManager.GET_SIGNATURES);
////            PackageInfo info =     getPackageManager().getPackageInfo("com.e.gasserviceapp.activity.loginactivity",     PackageManager.GET_SIGNATURES);
//            for (android.content.pm.Signature signature : info.signatures) {
//                    MessageDigest md = MessageDigest.getInstance("SHA");
//                    md.update(signature.toByteArray());
//                    String sign=Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                    Log.e("MY KEY HASH:", sign);
//
//                }
//        } catch (PackageManager.NameNotFoundException e) {
//        } catch (NoSuchAlgorithmException e) {
//        }

        //        --------------


        try {
//            PackageInfo info =     getPackageManager().getPackageInfo("MY PACKAGE NAME",     PackageManager.GET_SIGNATURES);
            PackageInfo info =     getPackageManager().getPackageInfo("com.e.gasserviceapp.activity.loginactivity",     PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
                MessageDigest md = MessageDigest.getInstance("41:9E:0F:C6:BC:CF:E1:BF:87:E5:99:3C:4B:29:3E:60:85:EA:35:97");
                md.update(signature.toByteArray());
                String sign=Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                Log.e("MY KEY HASH:", sign);
//                Log.e("d98iPnBzgIwWwNOyGLUhPMMBrMM=", sign);
                Log.e("QZ4PxrzP4b+H5Zk8Syk+YIXqNZc=", sign);



            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException ignored) {
        }

//        public void Get_hash_key("d98iPnBzgIwWwNOyGLUhPMMBrMM=") {
////        public void Get_hash_key("QZ4PxrzP4b+H5Zk8Syk+YIXqNZc=") {
//            PackageInfo info;
//            try {
//                info = getPackageManager().getPackageInfo("your_package_name", PackageManager.GET_SIGNATURES);
//                for (Signature signature : info.signatures) {
//                    MessageDigest md;
//                    md = MessageDigest.getInstance("SHA");
//                    md.update(signature.toByteArray());
//                    String something = new String(Base64.encode(md.digest(), 0));
//                    //String something = new String(Base64.encodeBytes(md.digest()));
//                    Log.e("hash key", something);
//                }
//            } catch (PackageManager.NameNotFoundException e1) {
//                Log.e("name not found", e1.toString());
//            } catch (NoSuchAlgorithmException e) {
//                Log.e("no such an algorithm", e.toString());
//            } catch (Exception e) {
//                Log.e("exception", e.toString());
//            }
//        }





        // If you are using in a fragment, call loginButton.setFragment(this);

//        Facebook Image OnClick:--------------------------------------
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        startActivity(new Intent(LoginActivity.this, SecondActivity.class));
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });


//----------------------------

//        Facebook Button OnClick:--------------------------------------

        b.registerCallback(c, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });


        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


//        ----------------------


        showPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPasswordTv.getText().equals("Hide")) {
                    showPasswordTv.setText("Show");
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else if (showPasswordTv.getText().equals("Show")) {
                    showPasswordTv.setText("Hide");
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        loginButton = findViewById(R.id.loginButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user = username.getText().toString();
//                pass = password.getText().toString();
//
//                if (user.equals("")) {
//                    username.setError("can't blank");
//                } else if (pass.equals("")) {
//                    password.setError("can't blank");
//                } else {
//
//                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
////                    Intent intent=new Intent(LoginActivity2.this, SetProfile.class);
//
//                    startActivity(intent);
//                    finish();
//                }
//
//            }
//        });

//----------------------------

//        fuil_Image = findViewById(R.id.fuil_Image);

        fuil_Image = (ImageView) findViewById(R.id.fuil_Image);
        fuil_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("")) {
                    username.setError("can't blank");
                } else if (pass.equals("")) {
                    password.setError("can't blank");
                } else {
//
//                    Intent lgoin_intent=new Intent(LoginActivity.this, BottomNavigationLayoutActivity.class);
//                    Intent intent=new Intent(LoginActivity.this, DashboardNearbyFuelStation.class);

                    Intent lgoin_intent = new Intent(LoginActivity.this, EnterOtpScreenOne.class);

//                    Intent intent=new Intent(LoginActivity.this, NavigationActivity.class);
//                    Intent lgoin_intent = new Intent(LoginActivity.this, MainActivity.class);
//                    Intent intent=new Intent(LoginActivity2.this, SetProfile.class);
                    startActivity(lgoin_intent);
                    finish();
                }

            }
        });

//        login_fuil_Btn = (Button) findViewById(R.id.fuil_Btn);
//        login_fuil_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user = username.getText().toString();
//                pass = password.getText().toString();
//
//                if (user.equals("")) {
//                    username.setError("can't blank");
//                } else if (pass.equals("")) {
//                    password.setError("can't blank");
//                } else {
////                    Intent intent=new Intent(LoginActivity.this, NavigationActivity.class);
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                    Intent intent=new Intent(LoginActivity2.this, SetProfile.class);
//
//                    startActivity(intent);
//                    finish();
//                }
//
//            }
//        });

        signupAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                //Intent intent = new Intent(getApplicationContext(), SignUpActivityGas.class);
                startActivity(intent);
            }
        });


        //        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity2.this, RegisterActivity.class));
//                finish();
//            }
//        });


//        login_facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("https://www.facebook.com/"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });
//
//        login_instagram.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri uri = Uri.parse("https://www.instagram.com/");
////                Uri uri = Uri.parse("https://www.google.com/intl/en-GB/gmail/about/#"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });
//
//        login_twitter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("https://twitter.com/"); // missing 'http://' will cause crashed
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });

//        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
//        Intent mainIntent = new Intent(LoginActivity.this, NavigationActivity.class);
//        Intent mainIntent = new Intent(LoginActivity.this, BottomNavigationLayoutActivity.class);


    }


//    ----------------------------------------------------------------------------------------------------


//    FACEBOOK


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        c.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);


            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(this, "User email :" + personEmail, Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(LoginActivity.this, SecondActivity.class));
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
            Log.d("Message", e.toString());
        }
    }

    AccessTokenTracker t = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken == null) {
                e.setText("");
                Toast.makeText(LoginActivity.this, "logout", Toast.LENGTH_SHORT).show();
            } else {
                loaduserprofile(currentAccessToken);
            }

        }
    };

    private void loaduserprofile(AccessToken newAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                if (object != null) {
                    try {
                        String email = object.getString("email");
                        String id = object.getString("id");

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }


    //--------


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