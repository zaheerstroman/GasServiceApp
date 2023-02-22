package com.e.gasserviceapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.gasserviceapp.AppSharedpref;
import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Response.ProfileUserDataResponseGasaverT;
import com.e.gasserviceapp.Response.StationData_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.e.gasserviceapp.activity.ProfileMainActivity;
import com.e.gasserviceapp.activity.UserSignInActivity;
import com.e.gasserviceapp.fragment.EditProfileFragment;
import com.e.gasserviceapp.network.APIClient;
import com.e.gasserviceapp.network.ApiInterface;
import com.e.gasserviceapp.userdata.UserPreference;
import com.e.gasserviceapp.utils.CommonUtils;
import com.e.gasserviceapp.utils.Constants;
import com.e.gasserviceapp.utils.SharedPrefs;
import com.e.gasserviceapp.viewmodal.FuelDistanceEmployeeList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FuelDistanceEmployeeListActivity extends AppCompatActivity {

    //HTTPs:--
    //https://github.com/zaheerstroman/GasServiceApp.git
    //SSH:--
    //git@github.com:zaheerstroman/GasServiceApp.git

    //    List<Root> list = new ArrayList<>();
//    List<StationDataJsonSchema2PojoResponseGasaver> list = new ArrayList<>();
//    List<StationDataJsonSchema2PojoResponseGasaver> list = new List<>();
//    List<StationDataJsonSchema2PojoResponseGasaver.Data> list = new ArrayList<>();
//    List<StationDataJsonSchema2PojoResponseGasaver.Data> list = new ArrayList<>();
//    List<StationDataJsonSchema2PojoResponseGasaver.Data._0> list = new ArrayList<>();
//    List<StationData_0> list = new ArrayList<>();

    List<StationData_0.Data> list = new ArrayList<>();

    private FuelDistanceEmployeeListAdapter employeeListAdapter;

    private StationData_0 stationData_0;
    private FuelDistanceEmployeeDetailsActivity fuelDistanceEmployeeDetailsActivity;
    Context context = FuelDistanceEmployeeListActivity.this;
    RecyclerView recy_employeelist;

    //Ariqt Restaurent App
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    UserPreference appPreference;

    AppSharedpref appSharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_distance_employee_list_activityity);
//        setContentView(R.layout.activity_fuel_distance_employee_list_activityity2);

//        fetchProfileDetails();
        getFuelDistanceemployeelistapi();
//        init();

//        CommonUtils.showLoading(FuelDistanceEmployeeListActivity.this, "Please Wait", false);


        recy_employeelist = findViewById(R.id.recy_employeelist);
        FuelDistanceEmployeeList model = ViewModelProviders.of(this).get(FuelDistanceEmployeeList.class);
//        String userid = appSharedpref.getmybillsactivityuserid();
//        model.setsample(userid);
//        model.getsample().observe(this, new Observer<ResponseBody>() {
        model.getsample().observe(this, new Observer<StationData_0.Data>() {

//            @Override
//            public void onChanged(StationData_0.Data data) {
//                //            public void onChanged(ResponseBody responseBody) {
//            }

            @Override
            public void onChanged(StationData_0.Data data) {
                try {
//                    String s = responseBody.string();
//                    String s = data.string();
                    String s = data.getAddress();

//                    list = new Gson().fromJson(s, new TypeToken<List<Root>>() {
                    list = new Gson().fromJson(s, new TypeToken<List<StationData_0.Data>>() {

                    }.getType());
//                    Sample software = gson.fromJson(s, Sample.class);
//                    List<Bill> bill = software.getBills();
                    EmployeeListResponse(list);
//                    FuelDistanceEmployeeListResponse(list);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

//    private void init() {
//
//        recy_employeelist =findViewById(R.id.recy_employeelist);
//        recy_employeelist.setOnClickListener((View.OnClickListener) this);
//
//    }

    //    private void EmployeeListResponse(List<Root> list) {
    private void EmployeeListResponse(List<StationData_0.Data> list) {

//    employeeListAdapter = new EmployeeListAdapter(context, list);
        employeeListAdapter = new FuelDistanceEmployeeListAdapter(context, list);

        recy_employeelist.setLayoutManager(new LinearLayoutManager(context));
        recy_employeelist.setNestedScrollingEnabled(true);
        recy_employeelist.setHasFixedSize(true);
        recy_employeelist.setAdapter(employeeListAdapter);
    }


    public class FuelDistanceEmployeeListAdapter extends RecyclerView.Adapter<FuelDistanceEmployeeListAdapter.ViewHolder> {

        //        List<Root> billdatalist = new ArrayList<>();
        List<StationData_0.Data> billdatalist = new ArrayList<>();

        Context context;
        //        List<Root> samplelist;
        List<StationData_0.Data> samplelist;


        public FuelDistanceEmployeeListAdapter(Context context, List<StationData_0.Data> samplelist) {
            this.samplelist = samplelist;
            this.context = context;
        }

        @NonNull
        @Override
        public FuelDistanceEmployeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fueldistanceemployeelistlayout, parent, false);
            return new FuelDistanceEmployeeListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FuelDistanceEmployeeListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            try {

                List<StationData_0.Data> samplelistList = samplelist;
                List<StationData_0.Data.Prices> itemList = samplelist.get(position).getPrices();

                appPreference.setvegcategoryItem(samplelist);


//                holder.usertext.setText(samplelistList.get(position).getMessage());

//                holder.usertext.setText(samplelistList.get(position).getClass());
//                holder.usertext.setText((CharSequence) samplelistList.get(position).get_0());
//                holder.usertext.setText(samplelistList.get(position).get_0());

                holder.usertext.setText(samplelistList.get(position).getId());

//                holder.usertext.setText(samplelistList.get(position).getName());
                holder.emailtext.setText(samplelistList.get(position).getAddress().toLowerCase(Locale.ROOT));

//                ---------------

//                holder.usertext.setText(samplelistList.get(position).getLatitude());
//                holder.emailtext.setText(samplelistList.get(position).getLongitude());


//                holder.emailtext.setText(samplelistList.get(position).getEmail().toLowerCase(Locale.ROOT));
//                holder.emailtext.setText(samplelistList.get(position).getStatusCode().toLowerCase(Locale.ROOT));
//                holder.emailtext.setText(samplelistList.get(position).getStatusCode());


//                holder.emailtext.setText(samplelistList.get(position).getData().toLowerCase(Locale.ROOT));

                holder.layoutid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        billdatalist.add(samplelistList.get(position));
                        appSharedpref.setemployeelistdetailsdata(billdatalist);
                        Intent intent = new Intent(context, FuelDistanceEmployeeDetailsActivity.class);
                        startActivity(intent);
                    }
                });

                holder.emailtext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
//                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{samplelistList.get(position).getEmail().toLowerCase(Locale.ROOT)});
//                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{samplelistList.get(position).getStationid().toLowerCase(Locale.ROOT)});
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{samplelistList.get(position).getAddress().toLowerCase(Locale.ROOT)});

                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                        startActivity(Intent.createChooser(intent, "Email via..."));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return samplelist.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView usertext, emailtext;
            LinearLayout layoutid;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                usertext = itemView.findViewById(R.id.usertext);
                emailtext = itemView.findViewById(R.id.emailtext);
                layoutid = itemView.findViewById(R.id.layoutid);
            }
        }
    }

    private void getFuelDistanceemployeelistapi() {
//    private void fetchProfileDetails() {


        CommonUtils.showLoading(FuelDistanceEmployeeListActivity.this, "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("user_id", SharedPrefs.getInstance(FuelDistanceEmployeeListActivity.this).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(FuelDistanceEmployeeListActivity.this).getString(Constants.TOKEN))
                .build();

//        Call<ProfileUserDataResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);
        Call<StationData_0.Data> call = apiInterface.getFuelDistanceemployeelistapi(requestBody);
//        Call<ResponseBody> call = apiInterface.getFuelDistanceemployeelistapi(requestBody);

//        call.enqueue(new Callback<RequestBody>() {
        call.enqueue(new Callback<StationData_0.Data>() {

            @Override
//            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
            public void onResponse(Call<StationData_0.Data> call, Response<StationData_0.Data> response) {

                CommonUtils.hideLoading();

//                profileUserDataResponseGasaverT = (ProfileUserDataResponseGasaverT) response.body();
//                stationData_0 = (RequestBody) response.body();
//                stationData_0 = (RequestBody) response.body();


                if (stationData_0 != null && stationData_0.getData() != null) {

//                    tv_email.setText(profileDetailsResponseGasaverT.getData().getEmail());
//                    recy_employeelist.setText((CharSequence) stationData_0.getData().getEmail());
//                    tv_profile_name.setText("Welcome " + profileDetailsResponse.getData().getFirst_name() + " " + profileDetailsResponse.getProfileDetails().getLast_name() + "!");
//                    recyclerViewCategoryList.setText("Welcome " + stationData_0.getData().getName() + " " + profileUserDataResponseGasaverT.getData().getLastName() + "!");

//                    tv_phone.setText(profileDetailsResponseGasaverT.getData().getMobile());
//                    tv_phone.setText(new ProfileDetailsResponseGasaverT.Data().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data().getMobile());

//                    recyclerViewPopularList.setText(stationData_0.getData().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data.getMobile());
//                    tv_phone.setText(profileUserDataResponseGasaverT.Data.getMobile());

//                    tv_current_plan.setText(stationData_0.getData().getUserCode());
//                    tv_role.setText("Role: " + profileDetailsResponse.getData().getUser_role());
//                    tv_role.setText("Code: " + stationData_0.getData().getCode());
//                    tv_current_plan.setText(profileDetailsResponse.getData().getUser_plan());
//                    tv_current_plan.setText(stationData_0.getData().getUserCode());

//                    Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileDetailsResponse.getData().getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);
//                    Glide.with(FuelDistanceEmployeeListActivity.this).load(Constants.PROFILE_IMG_URL + stationData_0.getData().getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);

//                    if (fuelDistanceEmployeeDetailsActivity != null && fuelDistanceEmployeeDetailsActivity.isVisible()) {
//                    if (fuelDistanceEmployeeDetailsActivity != null && fuelDistanceEmployeeDetailsActivity.isVisible()) {
//
////                        editProfileFragment.updateDetails(profileDetailsResponse.getProfileDetails());
////                        editProfileFragment.updateDetails(profileDetailsResponseGasaverT.Data.getData());
////                        editProfileFragment.updateDetails(ProfileUserDataResponseGasaverT.Data.getData());
//
//                        fuelDistanceEmployeeDetailsActivity.updateFuelDistanceDetails(stationData_0.getData());
////                        editProfileFragment.updateDetails((ProfileUserDataResponseGasaverT.Data) profileUserDataResponseGasaverT.getData());
//
//
//                    }
                }


            }

            @Override
            public void onFailure(Call<StationData_0.Data> call, Throwable t) {
                CommonUtils.hideLoading();
            }

        });

    }

}