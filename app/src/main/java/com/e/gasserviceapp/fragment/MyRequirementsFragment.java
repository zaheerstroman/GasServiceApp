package com.e.gasserviceapp.fragment;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.BaseResponse;
import com.e.gasserviceapp.Response.BaseResponseGasaver;
import com.e.gasserviceapp.Response.BaseResponseGasaverTProperty;
import com.e.gasserviceapp.Response.SubCategoryDefaultDataResponse;
import com.e.gasserviceapp.databinding.FragmentMyRequirementsBinding;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.utils.CommonUtils;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.SharedPrefs;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.e.gasserviceapp.fragment.databinding.FragmentMyRequirementsBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class MyRequirementsFragment extends Fragment {
//public class MyRequirementsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    Spinner spinner_budget;
    Button btn_submit;

    private String[] budgetArrayList = {"", "0-1000000", "1000000-2000000", "2000000-3000000", "3000000-4000000", "4000000-5000000", "5000000-6000000", "6000000-7000000",
            "7000000-8000000", "8000000-9000000", "9000000-10000000", "10000000-100000000"};

//    HashMap<String, List<PropertyTypesResponse.SubCategory>> categoryArrayListHashMap = new HashMap<String, List<PropertyTypesResponse.SubCategory>>();

    private FragmentMyRequirementsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        binding = FragmentMyRequirementsBinding.inflate(inflater, container, false);
//        return binding.getRoot();

        View root = inflater.inflate(R.layout.fragment_my_requirements, container, false);


        init(root);
//        fetchProprtyTypes();

        return root;

    }
    private void init(View v) {

        spinner_budget = v.findViewById(R.id.spinner_budget);
        spinner_budget.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_units, getResources().getStringArray(R.array.budgets)));

        btn_submit = v.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMyRequirements();
            }
        });


    }

//    private boolean validateFields(Spinner spinner_budget) {
//        return false;
////        return true;
//    }


    private void postMyRequirements() {
        if (validateFields(spinner_budget)) {

            CommonUtils.showLoading(getActivity(), "Please Wait", false);
            ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("post_type", "insert_requiremt");
            jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
            jsonObject.addProperty("api_key", SharedPrefs.getInstance(getActivity()).getString(Constants.API_KEY));
            jsonObject.addProperty("mobile", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_MOBILE));

//            jsonObject.addProperty("add_location", et_location.getText().toString());
//            jsonObject.addProperty("location_status", et_prop_location.getText().toString());
//            jsonObject.addProperty("property_type", rg_prop_type.getCheckedRadioButtonId());

//            if (ll_unit_details.getVisibility() == View.VISIBLE) {
//                RadioButton radioButton = rg_bed_rooms.findViewById(rg_bed_rooms.getCheckedRadioButtonId());
//                jsonObject.addProperty("bed_rooms", radioButton.getTag().toString());
//                radioButton = rg_bed_rooms.findViewById(rg_bed_rooms.getCheckedRadioButtonId());
//                jsonObject.addProperty("bath_rooms", radioButton.getTag().toString());
//            }
            String[] budget = budgetArrayList[spinner_budget.getSelectedItemPosition()].split("-");
            jsonObject.addProperty("budget_min", budget[0]);
            jsonObject.addProperty("budget_max", budget[1]);

//            Call<BaseResponseGasaverTProperty> call = apiInterface.postMyRequirements(jsonObject);
            Call<SubCategoryDefaultDataResponse> call = apiInterface.postMyRequirements(jsonObject);

            call.enqueue(new Callback<SubCategoryDefaultDataResponse>() {
                @Override
                public void onResponse(Call<SubCategoryDefaultDataResponse> call, Response<SubCategoryDefaultDataResponse> response) {
//                    BaseResponse baseResponse = response.body();
                    SubCategoryDefaultDataResponse baseResponse = response.body();
//                    if (baseResponse != null && baseResponse.getStatus()) {
                    if (baseResponse != null && baseResponse.getStatusCode()) {
//                        Toast.makeText(getActivity(), baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        Toast.makeText((Context) getActivity(), (CharSequence) baseResponse.getData(), Toast.LENGTH_SHORT).show();
//                        fetchMyRequirements();
                    }
                    CommonUtils.hideLoading();
                }

                @Override
                public void onFailure(Call<SubCategoryDefaultDataResponse> call, Throwable t) {
                    t.printStackTrace();
                    CommonUtils.hideLoading();
                }
            });


        }
    }


    private boolean validateFields(Spinner spinner_budget) {
//        if (et_location.getText().toString().trim().isEmpty()) {
//            et_location.setError("Required");
//            return false;
//        }
//        if (et_prop_location.getText().toString().trim().isEmpty()) {
//            et_prop_location.setError("Required");
//            return false;
//        }
//        if (rg_prop_type.getCheckedRadioButtonId() == -1) {
//            Toast.makeText(getActivity(), "Select Property Type", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (ll_unit_details.getVisibility() == View.VISIBLE) {
//            if (rg_bed_rooms.getCheckedRadioButtonId() == -1) {
//                Toast.makeText(getActivity(), "Select Number of bedrooms", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//            if (rg_bath_rooms.getCheckedRadioButtonId() == -1) {
//                Toast.makeText(getActivity(), "Select Number of Bathrooms", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
        if (spinner_budget.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "Please Select Budget", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }




//    private void fetchProprtyTypes() {
//
//    }

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//    }
}