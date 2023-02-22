package com.e.gasserviceapp.fragment;

import static android.app.Activity.RESULT_OK;
import static com.e.gasserviceapp.utils.Constants.REQUEST_GALLERY_PHOTO;
import static com.e.gasserviceapp.utils.Constants.REQUEST_TAKE_PHOTO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

//import com.e.gasserviceapp.BuildConfig;
import com.e.gasserviceapp.Response.BaseResponseGasaverTProperty;
import com.e.gasserviceapp.Response.ProfileDetailsResponseGasaverT;
import com.e.gasserviceapp.Response.ProfileUserDataResponseGasaverT;
import com.e.gasserviceapp.activity.MainActivity;
import com.e.gasserviceapp.R;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.e.gasserviceapp.ApiInterface;
import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.BaseResponse;
import com.e.gasserviceapp.Response.ProfileDetailsResponse;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.utils.CommonUtils;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.FilePathUtils;
import com.e.gasserviceapp.utils.SharedPrefs;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class EditProfileFragment extends AppCompatActivity {
public class EditProfileFragment extends BottomSheetDialogFragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_edit_profile_fragment2);
//        setContentView(R.layout.activity_edit_profile_fragment);
//
//    }

    EditText et_email, et_mobile, et_first_name, et_last_name;
    ImageView iv_close;
    ShapeableImageView iv_profile;
    Button btn_save;

//    ProfileDetailsResponse.ProfileDetails profileDetails;
//    ProfileDetailsResponseGasaverT.Data profileDetailsT;
//    ProfileDetailsResponseGasaverT.Data data;
    ProfileUserDataResponseGasaverT.Data data;


    private DismissListener dismissListener;
    private Uri imageUri;
    private String selectedPath = " ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.edit_profile_fragment, container, false);
        View view = inflater.inflate(R.layout.activity_edit_profile_fragment, container, false);
//        profileDetails = new Gson().fromJson(getArguments().getString("ProfileDetails"), ProfileDetailsResponse.ProfileDetails.class);
//        profileDetailsT = new Gson().fromJson(getArguments().getString("data"), ProfileDetailsResponseGasaverT.Data.class);

//        data = new Gson().fromJson(getArguments().getString("data"), ProfileDetailsResponseGasaverT.Data.class);
        data = new Gson().fromJson(getArguments().getString("data"), ProfileUserDataResponseGasaverT.Data.class);

        init(view);
        return view;
    }

    private void init(View v) {
        iv_profile = v.findViewById(R.id.iv_profile);
        iv_close = v.findViewById(R.id.iv_close);
        btn_save = v.findViewById(R.id.btn_save);
        et_email = v.findViewById(R.id.et_email);
        et_first_name = v.findViewById(R.id.et_first_name);
        et_last_name = v.findViewById(R.id.et_last_name);
        et_mobile = v.findViewById(R.id.et_mobile);

//        et_email.setText(profileDetails.getEmail());
//        et_email.setText(profileDetailsT.getEmail());
//        et_email.setText((CharSequence) profileDetailsT.getEmail());
        et_email.setText((CharSequence) data.getEmail());


//        et_first_name.setText(profileDetails.getFirst_name());
//        et_first_name.setText(data.getName());
        et_first_name.setText((CharSequence) data.getName());

//        et_last_name.setText(profileDetails.getLast_name());
//        et_last_name.setText(data.getLastName());
        et_last_name.setText((CharSequence) data.getLastName());

//        et_mobile.setText(profileDetailsT.getMobile());
        et_mobile.setText(data.getMobile());
//        Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileDetails.getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);
        Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + data.getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsValidated())
                    updateProfileDetails();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void updateProfileDetails() {
        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (!selectedPath.trim().isEmpty()) {
            File file = new File(selectedPath);
            RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("file", file.getName(), fbody);
        }
        builder

//                .addFormDataPart("post_type", "update_profile")

//                .addFormDataPart("post_type", "update_profile")

                .addFormDataPart("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID))
                .addFormDataPart("api_key", SharedPrefs.getInstance(getActivity()).getString(Constants.API_KEY))
                .addFormDataPart("first_name", et_first_name.getText().toString())
                .addFormDataPart("last_name", et_last_name.getText().toString())
                .addFormDataPart("mobile", et_mobile.getText().toString())
                .addFormDataPart("mobile_number", et_mobile.getText().toString())
                .addFormDataPart("email", et_email.getText().toString())

                .addFormDataPart("token", et_mobile.getText().toString())

                .build();

//        Call<BaseResponse> call = apiInterface.updateProfile(builder.build());
//        Call<BaseResponseGasaverTProperty> call = apiInterface.updateProfile(builder.build());
        Call<BaseResponseGasaverTProperty> call = apiInterface.updateProfile(builder.build());

//        Call<ProfileUserDataResponseGasaverT> call = apiInterface.updateProfile(builder.build());

        call.enqueue(new Callback<BaseResponseGasaverTProperty>() {
            @Override
            public void onResponse(Call<BaseResponseGasaverTProperty> call, Response<BaseResponseGasaverTProperty> response) {
                CommonUtils.hideLoading();
                if (response.body() != null && response.body().getStatusCode()) {
                    if (response.body().getStatusCode()) {
                        Toast.makeText(getActivity(), "Profile Details Updated successfully", Toast.LENGTH_SHORT).show();
                        dismissListener.onDismiss();
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseGasaverTProperty> call, Throwable t) {
                CommonUtils.hideLoading();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    private boolean isAllFieldsValidated() {

//        if (TextUtils.isEmpty(selectedPath.trim())){
//            Toast.makeText(getActivity(), "Please select Profile Image", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (TextUtils.isEmpty(et_first_name.getText().toString().trim())) {
            et_first_name.setError("Required");
            return false;
        }
        if (TextUtils.isEmpty(et_last_name.getText().toString().trim())) {
            et_last_name.setError("Required");
            return false;
        }
//        if (TextUtils.isEmpty(et_mobile.getText().toString().trim()) || et_mobile.getText().toString().length() != 10 || !Patterns.PHONE.matcher(et_mobile.getText().toString()).matches()) {
//            et_mobile.setError("Not Valid");
//            return false;
//        }
        if (TextUtils.isEmpty(et_email.getText().toString().trim()) || !Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            et_email.setError("Not Valid");
            return false;
        }
        return true;
    }

    //    public void updateDetails(ProfileDetailsResponse.ProfileDetails profiledetails) {
//    public void updateDetails(ProfileDetailsResponseGasaverT.Data profiledetails) {
//        this.profileDetailsT = profiledetails;
////        et_email.setText(profileDetails.getEmail());
////        et_first_name.setText(profileDetails.getFirst_name());
////        et_last_name.setText(profileDetails.getLast_name());
//        et_mobile.setText(profileDetailsT.getMobile());
////        Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profiledetails.getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);
//
//    }

//    public void updateDetails(ProfileDetailsResponseGasaverT.Data dat) {
//        this.data = dat;
////        et_email.setText(profileDetails.getEmail());
////        et_first_name.setText(profileDetails.getFirst_name());
////        et_last_name.setText(profileDetails.getLast_name());
//        et_mobile.setText(data.getMobile());
////        Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profiledetails.getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);
//
//    }

    public void updateDetails(ProfileUserDataResponseGasaverT.Data dat) {
        this.data = dat;
//        et_email.setText(data.getEmail());
        et_email.setText((CharSequence) data.getEmail());
//        et_first_name.setText(data.getName());
        et_first_name.setText((CharSequence) data.getName());
//        et_last_name.setText(data.getLastName());
        et_last_name.setText((CharSequence) data.getLastName());

        et_mobile.setText(data.getMobile());
//        Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profiledetails.getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);
        Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + data.getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile);

    }



    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Take Photo")) {
                if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "New Picture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                    imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

//                    imageUri = FileProvider.getUriForFile(
//                            getActivity(),
//                            BuildConfig.APPLICATION_ID + ".provider", //(use your app signature + ".provider" )
//                            photo);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, REQUEST_TAKE_PHOTO);

                }
            } else if (items[item].equals("Choose from Library")) {
                if ((Build.VERSION.SDK_INT >= 23) && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_GALLERY_PHOTO);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), REQUEST_GALLERY_PHOTO);
                }
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void setDismissListener(DismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public interface DismissListener {
        public void onDismiss();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_PHOTO || requestCode == REQUEST_TAKE_PHOTO) {
            Log.e("selected profile img", requestCode + " " + resultCode);
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = null;
                    String path = " ";
                    if (requestCode == REQUEST_TAKE_PHOTO) {
                        selectedPath = FilePathUtils.getRealPath(getActivity(), imageUri);

//                        selectedPath = imageUri.getPath();
                        bitmap = BitmapFactory.decodeFile(selectedPath);
                    } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                        selectedPath = FilePathUtils.getRealPath(getActivity(), data.getData());
                        bitmap = BitmapFactory.decodeFile(selectedPath);
                    }
                    Log.e("selected profile img", selectedPath);
                    iv_profile.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_TAKE_PHOTO) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //launch camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

//                imageUri = FileProvider.getUriForFile(
//                        getActivity(),
//                        BuildConfig.APPLICATION_ID + ".provider", //(use your app signature + ".provider" )
//                        photo);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_GALLERY_PHOTO) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //launch gallery
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), REQUEST_GALLERY_PHOTO);
            } else {
                Toast.makeText(getActivity(), "storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


}