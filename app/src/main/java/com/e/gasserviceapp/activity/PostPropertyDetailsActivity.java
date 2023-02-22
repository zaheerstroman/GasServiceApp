package com.e.gasserviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.CategoryDefaultDataResponse;
import com.e.gasserviceapp.Response.SubCategoryDefaultDataResponse;
import com.e.gasserviceapp.Response.UnitsResponse;
import com.e.gasserviceapp.model.PostPropertyModel;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.utils.CommonUtils;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPropertyDetailsActivity extends AppCompatActivity {

    LinearLayout ll_facing, ll_flooring, ll_about_prop,ll_furnishing, ll_commercial,ll_prop_age,
            ll_layout_type, ll_window_type,ll_plot_details, ll_unit_details, ll_floor_details, ll_const_status,ll_land_type;

    Spinner spinner_facing,spinner_flooring,spinner_layout_type,spinner_window_type,spinner_land_type,spinner_plot_units;

    private PostPropertyModel postPropertyModel;

    private CategoryDefaultDataResponse categoryDefaultDataResponse;
    private SubCategoryDefaultDataResponse subCategoryDefaultDataResponse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property_details);

        init();

        fetchUnits();


    }

    private void init() {

        ll_facing = findViewById(R.id.ll_facing);
        ll_flooring = findViewById(R.id.ll_flooring);

        ll_facing.setVisibility(View.GONE);
        ll_flooring.setVisibility(View.GONE);
    }

    private void fetchUnits() {
        CommonUtils.showLoading(this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("post_type", "unit_type");

        Call<UnitsResponse> call = apiInterface.fetchUnits(jsonObject);
        call.enqueue(new Callback<UnitsResponse>() {
            @Override
            public void onResponse(Call<UnitsResponse> call, Response<UnitsResponse> response) {
                UnitsResponse unitsResponse = response.body();
                if (unitsResponse != null && !unitsResponse.getUnits().isEmpty()) {
//                    spinner_plot_units.setAdapter(new ArrayAdapter<>(PostPropertyDetailsActivity.this, R.layout.list_units, unitsResponse.getUnits()));
                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<UnitsResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

    private void setDetailsToPropType(PostPropertyModel postPropertyModel) {
        if (postPropertyModel.getPropertyFor().equalsIgnoreCase("resale"))
            ll_prop_age.setVisibility(View.VISIBLE);
        else
            ll_prop_age.setVisibility(View.GONE);
        switch (postPropertyModel.getProperty_type()) {
            case "1":
            case "9":
//                et_floor_no.setVisibility(View.GONE);
                ll_about_prop.setVisibility(View.VISIBLE);
                ll_window_type.setVisibility(View.VISIBLE);
                ll_const_status.setVisibility(View.VISIBLE);
                ll_layout_type.setVisibility(View.VISIBLE);
                ll_facing.setVisibility(View.VISIBLE);
                ll_flooring.setVisibility(View.VISIBLE);
                ll_unit_details.setVisibility(View.VISIBLE);
                ll_furnishing.setVisibility(View.VISIBLE);
                ll_floor_details.setVisibility(View.VISIBLE);
                break;
            case "2":
                ll_about_prop.setVisibility(View.VISIBLE);
                ll_const_status.setVisibility(View.VISIBLE);
                ll_layout_type.setVisibility(View.VISIBLE);
                ll_facing.setVisibility(View.VISIBLE);
                ll_flooring.setVisibility(View.VISIBLE);
                ll_unit_details.setVisibility(View.VISIBLE);
                ll_furnishing.setVisibility(View.VISIBLE);
                ll_floor_details.setVisibility(View.VISIBLE);
                break;
            case "3":
                ll_about_prop.setVisibility(View.VISIBLE);
                ll_layout_type.setVisibility(View.VISIBLE);
                ll_facing.setVisibility(View.VISIBLE);
                ll_plot_details.setVisibility(View.VISIBLE);
                break;
            case "4":
                ll_about_prop.setVisibility(View.VISIBLE);
                ll_layout_type.setVisibility(View.VISIBLE);
                ll_facing.setVisibility(View.VISIBLE);
                ll_plot_details.setVisibility(View.VISIBLE);
                ll_furnishing.setVisibility(View.VISIBLE);
                ll_floor_details.setVisibility(View.VISIBLE);
                ll_commercial.setVisibility(View.VISIBLE);
                break;
            case "5":

                ll_about_prop.setVisibility(View.VISIBLE);
                ll_facing.setVisibility(View.VISIBLE);
                break;
            case "6":
                ll_plot_details.setVisibility(View.VISIBLE);
                ll_about_prop.setVisibility(View.VISIBLE);
                ll_facing.setVisibility(View.VISIBLE);
                ll_land_type.setVisibility(View.VISIBLE);
                break;
            case "7":
                break;
            case "8":
                break;

        }
    }



}