package com.e.gasserviceapp.webservices;

import com.e.gasserviceapp.model.directionplacemodel.DirectionResponseModel;
import com.e.gasserviceapp.model.googleplacemodel.GoogleResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {


    @GET
    Call<GoogleResponseModel> getNearByPlaces(@Url String url);

    @GET
    Call<DirectionResponseModel> getDirection(@Url String url);

}
