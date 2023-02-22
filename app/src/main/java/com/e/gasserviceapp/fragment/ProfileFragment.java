package com.e.gasserviceapp.fragment;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.ProfileDetailsResponse;
import com.e.gasserviceapp.Response.ProfileDetailsResponseGasaverT;
import com.e.gasserviceapp.Response.ProfileUserDataResponseGasaverT;
import com.e.gasserviceapp.activity.ProfileDetailsActivity;
import com.e.gasserviceapp.activity.SplashActivity;
import com.e.gasserviceapp.activity.SplashActivityGas;
import com.e.gasserviceapp.databinding.FragmentProfileBinding;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.utils.CommonUtils;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.SharedPrefs;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {
//public class ProfileFragment extends Fragment {

    ShapeableImageView iv_profile_img;
    ImageView iv_edit;
    LinearLayout ll_logout, layout_myResponses, layout_savedSearches, layout_shortListed, layout_contaced, layout_myRequirements, layout_myProperties;
    TextView tv_profile_name, tv_email, tv_phone, tv_current_plan, tv_role;

//    private ProfileDetailsResponse profileDetailsResponse;
//    private ProfileDetailsResponseGasaverT profileDetailsResponseGasaverT;
//    private ProfileDetailsResponseGasaverT profileDetailsResponse;
//    private ProfileDetailsResponseGasaverT profileDetailsResponseGasaverT;
    private ProfileUserDataResponseGasaverT profileUserDataResponseGasaverT;

    private EditProfileFragment editProfileFragment;
    Button btn_upgrade;

//    ----------------------------------------------------------

//    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        binding = FragmentProfileBinding.inflate(inflater, container, false);
//        return binding.getRoot();

        View view = inflater.inflate(R.layout.activity_profile, container, false);
        init(view);
        fetchProfileDetails();
        return view;

    }

    private void init(View view) {
        tv_role = view.findViewById(R.id.tv_role);
        iv_profile_img = view.findViewById(R.id.iv_profile_img);
        tv_profile_name = view.findViewById(R.id.tv_profile_name);

        tv_email = view.findViewById(R.id.tv_email);
        tv_phone = view.findViewById(R.id.tv_phone);

        tv_current_plan = view.findViewById(R.id.tv_current_plan);
        btn_upgrade = view.findViewById(R.id.btn_upgrade);

        iv_edit = view.findViewById(R.id.iv_edit);

        ll_logout = view.findViewById(R.id.ll_logout);

        layout_myResponses = view.findViewById(R.id.layout_myResponses);
        layout_savedSearches = view.findViewById(R.id.layout_savedSearches);
        layout_shortListed = view.findViewById(R.id.layout_shortListed);
        layout_contaced = view.findViewById(R.id.layout_contaced);
        layout_myRequirements = view.findViewById(R.id.layout_myRequirements);
        layout_myProperties = view.findViewById(R.id.layout_myProperties);

        ll_logout.setOnClickListener(this);

        layout_myResponses.setOnClickListener(this);
        layout_savedSearches.setOnClickListener(this);
        layout_shortListed.setOnClickListener(this);
        layout_contaced.setOnClickListener(this);
        layout_myRequirements.setOnClickListener(this);
        layout_myProperties.setOnClickListener(this);

        iv_edit.setOnClickListener(this);

    }

    private void fetchProfileDetails() {

        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)

//                .addFormDataPart("post_type", "profile_details")
//                .addFormDataPart("result", "profile_details")
//                .addFormDataPart("data", "data")

                //table_name
                //.addFormDataPart("table_name", "user")
                ////.addFormDataPart("user", "table_name")

//                .addFormDataPart("table_name", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_TABLE))
//                .addFormDataPart("id", SharedPrefs.getInstance(getActivity()).getString(Constants.ID))
                .addFormDataPart("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_MOBILE))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(getActivity()).getString(Constants.MOBILE))
//                .addFormDataPart("mobile_number", SharedPrefs.getInstance(getActivity()).getString(Constants.MOBILE_EMAIL))
                .addFormDataPart("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN)).build();
//                .addFormDataPart("api_key", SharedPrefs.getInstance(getActivity()).getString(Constants.API_KEY)).build();


//        Call<ProfileDetailsResponse> call = apiInterface.fetchProfileDetails(requestBody);

        //getDefaultData
//        Call<ProfileDetailsResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);

        //getUserData
        Call<ProfileUserDataResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);
//        Call<List<ProfileUserDataResponseGasaverT>> call = apiInterface.fetchProfileDetails(requestBody);


        call.enqueue(new Callback<ProfileUserDataResponseGasaverT>() {
            //        call.enqueue(new Callback<List<ProfileUserDataResponseGasaverT>>() {
            @Override
            public void onResponse(Call<ProfileUserDataResponseGasaverT> call, Response<ProfileUserDataResponseGasaverT> response) {
//            public void onResponse(Call<List<ProfileUserDataResponseGasaverT>> call, Response<List<ProfileUserDataResponseGasaverT>> response) {
                CommonUtils.hideLoading();

//                profileDetailsResponseGasaverT = response.body();
//                profileUserDataResponseGasaverT = response.body();
                profileUserDataResponseGasaverT = (ProfileUserDataResponseGasaverT) response.body();

//                ----------------------------

//                if (profileDetailsResponse != null && profileDetailsResponse.getProfileDetails() != null) {
//                    tv_email.setText(profileDetailsResponse.getProfileDetails().getEmail());
//                    tv_phone.setText(profileDetailsResponse.getProfileDetails().getMobile());
//                    tv_profile_name.setText("Welcome " + profileDetailsResponse.getProfileDetails().getFirst_name() + " " + profileDetailsResponse.getProfileDetails().getLast_name() + "!");
//                    tv_current_plan.setText(profileDetailsResponse.getProfileDetails().getUser_plan());
//                    tv_role.setText("Role: " + profileDetailsResponse.getProfileDetails().getUser_role());
//                    Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileDetailsResponse.getProfileDetails().getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);
//                    if (editProfileFragment != null && editProfileFragment.isVisible()) {
//                        editProfileFragment.updateDetails(profileDetailsResponse.getProfileDetails());
//                    }
//                }

//                ---------------------------------------

//                if (profileDetailsResponseGasaverT != null && new ProfileDetailsResponseGasaverT.Data() != null) {
//                if (profileDetailsResponseGasaverT != null && ProfileDetailsResponseGasaverT.Data.class != null{
//                if (profileDetailsResponseGasaverT != null && profileDetailsResponseGasaverT.getData() != null) {
                if (profileUserDataResponseGasaverT != null && profileUserDataResponseGasaverT.getData() != null) {

//                    tv_email.setText(profileDetailsResponseGasaverT.getData().getEmail());
                    tv_email.setText((CharSequence) profileUserDataResponseGasaverT.getData().getEmail());
//                    tv_profile_name.setText("Welcome " + profileDetailsResponse.getData().getFirst_name() + " " + profileDetailsResponse.getProfileDetails().getLast_name() + "!");
                    tv_profile_name.setText("Welcome " + profileUserDataResponseGasaverT.getData().getName() + " " + profileUserDataResponseGasaverT.getData().getLastName() + "!");

//                    tv_phone.setText(profileDetailsResponseGasaverT.getData().getMobile());
//                    tv_phone.setText(new ProfileDetailsResponseGasaverT.Data().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data().getMobile());

                    tv_phone.setText(profileUserDataResponseGasaverT.getData().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data.getMobile());
//                    tv_phone.setText(profileUserDataResponseGasaverT.Data.getMobile());

                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());
//                    tv_role.setText("Role: " + profileDetailsResponse.getData().getUser_role());
                    tv_role.setText("Code: " + profileUserDataResponseGasaverT.getData().getCode());
//                    tv_current_plan.setText(profileDetailsResponse.getData().getUser_plan());
                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());

//                    Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileDetailsResponse.getData().getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);
                    Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileUserDataResponseGasaverT.getData().getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);

                    if (editProfileFragment != null && editProfileFragment.isVisible()) {

//                        editProfileFragment.updateDetails(profileDetailsResponse.getProfileDetails());
//                        editProfileFragment.updateDetails(profileDetailsResponseGasaverT.Data.getData());
//                        editProfileFragment.updateDetails(ProfileUserDataResponseGasaverT.Data.getData());

                        editProfileFragment.updateDetails(profileUserDataResponseGasaverT.getData());
//                        editProfileFragment.updateDetails((ProfileUserDataResponseGasaverT.Data) profileUserDataResponseGasaverT.getData());


                    }
                }

            }

            @Override
            public void onFailure(Call<ProfileUserDataResponseGasaverT> call, Throwable t) {
//            public void onFailure(Call<List<ProfileUserDataResponseGasaverT>> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_myResponses:
                Intent intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra("SELECTED_POS", 0);
                startActivity(intent);
                break;
            case R.id.layout_savedSearches:
                intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra("SELECTED_POS", 5);
                startActivity(intent);
                break;
            case R.id.layout_shortListed:
                intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra("SELECTED_POS", 4);
                startActivity(intent);
                break;
            case R.id.layout_contaced:
                intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra("SELECTED_POS", 3);
                startActivity(intent);
                break;
            case R.id.layout_myRequirements:
                intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra("SELECTED_POS", 2);
                startActivity(intent);
                break;
            case R.id.layout_myProperties:
                intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                intent.putExtra("SELECTED_POS", 1);
                startActivity(intent);
                break;
            case R.id.iv_edit:
                //getData
//                if (profileDetailsResponse.getProfileDetails() != null) {
//                if (profileDetailsResponseGasaverT.getData() != null) {
                if (profileUserDataResponseGasaverT.getData() != null) {

                    editProfileFragment = new EditProfileFragment();
                    Bundle bundle = new Bundle();

//                    bundle.putString("ProfileDetails", new Gson().toJson(profileDetailsResponse.getProfileDetails()));
//                    bundle.putString("data", new Gson().toJson(profileDetailsResponseGasaverT.getData()));
                    bundle.putString("data", new Gson().toJson(profileUserDataResponseGasaverT.getData()));

                    editProfileFragment.setArguments(bundle);
                    editProfileFragment.setDismissListener(new EditProfileFragment.DismissListener() {
                        @Override
                        public void onDismiss() {
                            fetchProfileDetails();
                        }
                    });
                    editProfileFragment.show(getParentFragmentManager(), "");
                }
                break;
            case R.id.ll_logout:
                SharedPrefs.getInstance(getActivity()).clearSharedPrefs();
//                Intent intent1 = new Intent(getActivity(), SplashActivityGas.class);
                Intent intent1 = new Intent(getActivity(), SplashActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;

        }

    }

}