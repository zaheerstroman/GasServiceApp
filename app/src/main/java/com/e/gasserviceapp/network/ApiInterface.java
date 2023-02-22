package com.e.gasserviceapp.network;

import com.e.gasserviceapp.Response.AutoSearchGetStationDataResponse;
import com.e.gasserviceapp.Response.BaseResponseGasaverTProperty;
import com.e.gasserviceapp.Response.OtpResponseGasaverTProperty;
import com.e.gasserviceapp.Response.ProfileUserDataResponseGasaverT;
import com.e.gasserviceapp.Response.StationData_0;
import com.e.gasserviceapp.Response.SubCategoryDefaultDataResponse;
import com.e.gasserviceapp.Response.UnitsResponse;
import com.e.gasserviceapp.Response.UserRegResponseGasaverTProperty;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    //    String BASE_URL = "https://jsonplaceholder.typicode.com/";
//    String BASE_URL = "https://houseofspiritshyd.in/gasaver/admin/api/";
    String BASE_URL_API = "https://houseofspiritshyd.in/gasaver/admin/api/";


//    public static final String BASE_URL = "https://tproperty.in/";
//    public static final String BASE_URL_API = "https://tproperty.in/api/";




    //    // for user roles
//    @POST("Common_controller/index")
//    Call<UserRolesResponse> fetchUserRoles(@Body RequestBody postObj);

    // for user login
//    @POST("login_controller/index")
//    Call<UserRegResponse> userLogin(@Body RequestBody postObj);


    //    @POST("login_controller/index")
    @POST("sendOtp")
//    Call<UserRegResponseGasaver> userLogin(@Body RequestBody postObj);
    Call<UserRegResponseGasaverTProperty> userLogin(@Body RequestBody postObj);

    // for otp verification
//    @POST("login_controller/index")
//    Call<OtpResponse> verifyOtp(@Body RequestBody postObj);

    //    @POST("login_controller/index")
    @POST("verifyOtp")
//    Call<OtpResponseGasaver> verifyOtp(@Body RequestBody postObj);
    Call<OtpResponseGasaverTProperty> verifyOtp(@Body RequestBody postObj);


    //    // for otp verification
//    @POST("login_controller/index")
    @POST("sendOtp")
//    Call<BaseResponse> resendOtp(@Body RequestBody postObj);
//    Call<BaseResponseGasaver> resendOtp(@Body RequestBody postObj);
    Call<BaseResponseGasaverTProperty> resendOtp(@Body RequestBody postObj);


//    // fetch Amenities
//    @POST("Common_controller/index")
//    Call<AmenitiesResponse> fetchAmenities(@Body RequestBody postObj);
//
//    // fetch property  types
//    @POST("Common_controller/index")
//    Call<PropertyTypesResponse> fetchPropertyTypes(@Body RequestBody postObj);
//
//    // fetch popular cities
//    @POST("Common_controller/index")
//    Call<PopularCitiesResponse> fetchPopularCities(@Body RequestBody postObj);
//
//    // fetch plans
//    @POST("Common_controller/index")
//    Call<UserPlansResponse> fetchPlans(@Body RequestBody postObj);
//
//    // fetch latest properties
//    @POST("projects_controller/index")
//    Call<LatestPropertiesResponse> fetchLatestProperties(@Body RequestBody postObj);
//
//    // fetch my responses
//    @POST("my_activities_controller/index")
//    Call<MyResResponse> fetchMyResponses(@Body RequestBody postObj);
//
//    // fetch saved searches
//    @POST("my_activities_controller/index")
//    Call<SavedSearchesResponse> fetchSavedSearches(@Body RequestBody postObj);
//
//    // fetch saved searches
//    @POST("my_activities_controller/saved_searches")
//    Call<BaseResponse> postSavedSearches(@Body JsonObject postObj);

//    ---------------

//    // fetch profile details
//    @POST("my_activities_controller/index")
//    Call<ProfileDetailsResponse> fetchProfileDetails(@Body RequestBody postObj);

//    @POST("getDefaultData")
//    Call<ProfileDetailsResponseGasaverT> fetchProfileDetails(@Body RequestBody postObj);


    // update profile
//    @POST("My_activities_controller/index")
//    Call<BaseResponse> updateProfile(@Body RequestBody postObj);

//    @POST("getDefaultData")
//    Call<BaseResponseGasaverTProperty> updateProfile(@Body RequestBody postObj);


    //-------------------

    @POST("getUserData")
//    Call<ProfileUserDataResponseGasaverT> fetchProfileDetails(@Body RequestBody postObj);
    Call<ProfileUserDataResponseGasaverT> fetchProfileDetails(@Body RequestBody postObj);
//    Call <List<ProfileUserDataResponseGasaverT>> fetchProfileDetails(@Body RequestBody postObj);


    @POST("getUserData")
    Call<BaseResponseGasaverTProperty> updateProfile(@Body RequestBody postObj);
//    Call<List<BaseResponseGasaverTProperty>> updateProfile(@Body RequestBody postObj);


//    Call<ProfileUserDataResponseGasaverT> updateProfile(@Body RequestBody postObj);
//        Call<List<ProfileUserDataResponseGasaverT>> updateProfile(@Body RequestBody postObj);

//-------------------------------

//    @FormUrlEncoded
//    @POST("getStationsData")
////    Call<StationDataJsonSchema2PojoResponse> getdisplayStationDetails(@FieldMap Map<String, String> fields);
//    Call<StationDataJsonSchema2PojoResponseGasaver> getdisplayStationDetails(@FieldMap Map<String, String> fields);

//

    @POST("getStationsData")
//    @GET("getStationsData")
//    Call<ResponseBody> getemployeelistapi();
//    Call<ResponseBody> getFuelDistanceemployeelistapi();
//    Call<ResponseBody> getFuelDistanceemployeelistapi(@Body RequestBody postObj);
    Call<StationData_0.Data> getFuelDistanceemployeelistapi(@Body RequestBody postObj);


    //    // fetch auto search
//    @POST("search_controller/index")
    @POST("getDefaultData")
    Call<AutoSearchGetStationDataResponse> fetchAutoSearch(@Body JsonObject postObj);

    //    // post my req
    @POST("search_controller/index")
//Call<BaseResponseGasaverTProperty> postMyRequirements(@Body JsonObject postObj);
    Call<SubCategoryDefaultDataResponse> postMyRequirements(@Body JsonObject postObj);


//Spinner DropDown
    //    // fetch units
    @POST("search_controller/index")
    Call<UnitsResponse> fetchUnits(@Body JsonObject postObj);


//
//    // fetch project details
//    @POST("my_activities_controller/index")
//    Call<ProjectDetailsResponse> fetchProjectDetails(@Body RequestBody postObj);
//
//    // post contact/book/req
//    @POST("my_activities_controller/index")
//    Call<BaseResponse> postReqForProp(@Body RequestBody postObj);
//
//    // fetch new projects
//    @POST("Property_details_controller/index")
//    Call<NewProjectsResponse> fetchProjects(@Body RequestBody postObj);
//
//    // post new property
//    @POST("Common_controller/post_property")
//    @Headers({"Content-Type: application/json"})
//    Call<PropPostedResponse> postProperty(@Body PostPropertyModel postObj);
//
//    // update profile
//    @POST("My_activities_controller/index")
//    Call<BaseResponse> updateProfile(@Body RequestBody postObj);
//
//    // shortlist property
//    @POST("My_activities_controller/index")
//    Call<BaseResponse> wishlistProperty(@Body RequestBody postObj);
//
//    // shortlist property
//    @POST("My_activities_controller/index")
//    Call<BaseResponse> removeProperty(@Body RequestBody postObj);
//
//    // fetch banners
//    @POST("search_controller/index")
//    Call<BannersResponse> fetchBanners(@Body JsonObject postObj);
//
//    // fetch my req
//    @POST("search_controller/index")
//    Call<MyReqResponse> fetchMyRequirements(@Body JsonObject postObj);
//
//    // post my req
//    @POST("search_controller/index")
//    Call<BaseResponse> postMyRequirements(@Body JsonObject postObj);
//
//    // post my req
//    @POST("search_controller/index")
//    Call<BaseResponse> postFeedback(@Body JsonObject postObj);
//
//    // fetch units
//    @POST("search_controller/index")
//    Call<UnitsResponse> fetchUnits(@Body JsonObject postObj);
//
//    // fetch search resuts
//    @POST("search_controller/index")
//    Call<NewProjectsResponse> fetchSearchResults(@Body PostFilterSearch postObj);
//
//    // fetch auto search
//    @POST("search_controller/index")
//    Call<AutoSearchResponse> fetchAutoSearch(@Body JsonObject postObj);
//
//    // fetch help
//    @POST("search_controller/index")
//    Call<HelpResponse> fetchHelp(@Body JsonObject postObj);
//
//    // fetch location table
//    @POST("Common_controller/dynamic_table")
//    Call<LocationTableResponse> fetchLocationTable(@Body JsonObject postObj);
//    // fetch notifications
//    @POST("Common_controller/index")
//    Call<NotificationsResponse> fetchNotifications(@Body MultipartBody postObj);
//    // fetch agents
//    @POST("Common_controller/index")
//    Call<AgentsResponse> fetchAgents(@Body RequestBody postObj);
//    // fetch agents
//    @POST("Common_controller/index")
//    Call<LinksResponse> fetchLinks(@Body RequestBody postObj);


}
