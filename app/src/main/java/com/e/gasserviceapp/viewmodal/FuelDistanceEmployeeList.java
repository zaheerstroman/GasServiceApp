package com.e.gasserviceapp.viewmodal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.gasserviceapp.Response.StationData_0;
import com.e.gasserviceapp.network.ApiInterface;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuelDistanceEmployeeList extends ViewModel {

    private MutableLiveData<StationData_0.Data> loginlist;

    public LiveData<StationData_0.Data> getsample() {
        //if the list is null
        if (loginlist == null) {
            loginlist = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            setsample();
        }

        //finally we will return the list
        return loginlist;
    }

    public void setsample() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .baseUrl(ApiInterface.BASE_URL)
                .baseUrl(ApiInterface.BASE_URL_API)

                .addConverterFactory(GsonConverterFactory.create())
                .build();


//        Api api = retrofit.create(Api.class);
        ApiInterface api = retrofit.create(ApiInterface.class);


//        Call<ResponseBody> call = api.getsampleapi("14");
//        Call<ResponseBody> call = api.getemployeelistapi();

        //Body As Per Bill And Restaurent App:----------------------------------------------------------
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        Call<StationData_0.Data> call = api.getFuelDistanceemployeelistapi(builder.build());
//        Call<ResponseBody> call = api.getFuelDistanceemployeelistapi(builder.build());
//        Call<ResponseBody> call = api.getFuelDistanceemployeelistapi();

        call.enqueue(new Callback<StationData_0.Data>() {
            @Override
            public void onResponse(Call<StationData_0.Data> call, Response<StationData_0.Data> response) {
                loginlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StationData_0.Data> call, Throwable t) {

            }
        });
    }
}
