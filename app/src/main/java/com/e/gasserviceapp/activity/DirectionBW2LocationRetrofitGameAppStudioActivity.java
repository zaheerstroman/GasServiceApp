package com.e.gasserviceapp.activity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.e.gasserviceapp.ApiInterface;
import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Result;
import com.e.gasserviceapp.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectionBW2LocationRetrofitGameAppStudioActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private ApiInterface apiInterface;
    private List<LatLng> polylinelist;
    private PolylineOptions polylineOptions;
    //    private latlng origion, destination;
    private LatLng origion, dest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_bw2_location_game_app_studio);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://maps.googleapis.com/").build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://maps.googleapis.com/").build();

        apiInterface = retrofit.create(ApiInterface.class);

    }

//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        map = googleMap;
//        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        map.setTrafficEnabled(true);
//
////        getDirections();
//
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setTrafficEnabled(true);

        origion = new LatLng(17.3850, 78.4867);

        dest = new LatLng(19.0760, 72.8777);

        getDirection("17.3850" + "," + "78.4867", "19.0760" + "," + "72.8777");

    }


    private void getDirection(String origin, String destination) {
        //Own
        //    private void getDirection(String origion, String dest) {

        apiInterface.getDirection("driving", "less_driving", origin, destination, getString(R.string.API_KEY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Result>() {

//        apiInterface.getDirection("driving", "less_driving", origin, destination, getString(R.string.api_key)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Result>() {
            //apiInterface.getDirection("driving", "less_driving", origion, dest, getString(R.string.api_key)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Result>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Result result) {

                polylinelist = new ArrayList<>();

                List<Route> routeList = result.getRoutes();
                for (Route route : routeList) {
                    String polyline = route.getOveriewPolyline().getPoints();
//                            polylinelist.add(decodePoly(polyline));
                    polylinelist.addAll(decodePoly(polyline));
//                            polylinelist.addAll(decodePoly(polyline));
                }

                polylineOptions = new PolylineOptions();
//                        polylineOptions.color(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                polylineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.purple_200));
                polylineOptions.width(8);
                polylineOptions.startCap(new ButtCap());
                polylineOptions.jointType(JointType.ROUND);
//                PolylineOptions.addAll
                polylineOptions.addAll(polylinelist);
                map.addPolyline(polylineOptions);

                LatLngBounds.Builder builder= new LatLngBounds.Builder();
                builder.include(origion);
                builder.include(dest);
                //()
                //Padding
                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
                //Width/Height
//                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
                //(int duration)
//                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
//                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));




            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    //This is the method who decode string to latlang

    //    private ArrayList<LatLng> decodePoly(String encoded) {
    private List<LatLng> decodePoly(String encoded) {

//        Log.i("Location", "String received: "+encoded);

//        ArrayList<LatLng> poly = new ArrayList<LatLng>();
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

//            LatLng p = new LatLng((int) (((double) lat /1E5)* 1E6), (int) (((double) lng/1E5   * 1E6)));
            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));


            poly.add(p);
        }

//        for(int i=0;i<poly.size();i++){
//            Log.i("Location", "Point sent: Latitude: "+poly.get(i).latitude+" Longitude: "+poly.get(i).longitude);
//        }
        return poly;
    }


    //-------------------------------------------------------------

    //private ArrayList<LatLng> decodePoly(String encoded) {
    //
    //    Log.i("Location", "String received: "+encoded);
    //    ArrayList<LatLng> poly = new ArrayList<LatLng>();
    //    int index = 0, len = encoded.length();
    //    int lat = 0, lng = 0;
    //
    //    while (index < len) {
    //        int b, shift = 0, result = 0;
    //        do {
    //            b = encoded.charAt(index++) - 63;
    //            result |= (b & 0x1f) << shift;
    //            shift += 5;
    //        } while (b >= 0x20);
    //        int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
    //        lat += dlat;
    //
    //        shift = 0;
    //        result = 0;
    //        do {
    //            b = encoded.charAt(index++) - 63;
    //            result |= (b & 0x1f) << shift;
    //            shift += 5;
    //        } while (b >= 0x20);
    //        int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
    //        lng += dlng;
    //
    //        LatLng p = new LatLng((int) (((double) lat /1E5)* 1E6), (int) (((double) lng/1E5   * 1E6)));
    //        poly.add(p);
    //    }
    //
    //   for(int i=0;i<poly.size();i++){
    //       Log.i("Location", "Point sent: Latitude: "+poly.get(i).latitude+" Longitude: "+poly.get(i).longitude);
    //   }
    //    return poly;
    //}


//    private void getDirection(String origin, String destination) {
//        apiInterface.getDirection("driving", "less driving", origin, destination,
//                getString(R.string.api_key)
//        ).subscribeOn(Schedulers.io()).observeOn(AndroidScheldulers.mainThread()).subscribe(new SingleObserver<Result>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(Result result) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//    }

}
