package com.e.gasserviceapp.fragment;

//import static androidx.core.app.AppOpsManagerCompat.Api23Impl.getSystemService;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.e.gasserviceapp.ApiInterface;
import com.e.gasserviceapp.GooglePlaceModel;
import com.e.gasserviceapp.NearLocationInterface;
import com.e.gasserviceapp.PlaceModel;
import com.e.gasserviceapp.R;
import com.e.gasserviceapp.Result;
import com.e.gasserviceapp.SavedPlaceModel;
import com.e.gasserviceapp.activity.DirectionActivity;
import com.e.gasserviceapp.activity.MainActivityGas;
import com.e.gasserviceapp.adapter.GooglePlaceAdapter;
import com.e.gasserviceapp.adapter.InfoWindowAdapter;
import com.e.gasserviceapp.constant.AllConstant;
import com.e.gasserviceapp.databinding.FragmentHomeGasaverBinding;
import com.e.gasserviceapp.model.googleplacemodel.GoogleResponseModel;
import com.e.gasserviceapp.permissions.AppPermissions;
import com.e.gasserviceapp.utility.LoadingDialog;
import com.e.gasserviceapp.webservices.RetrofitAPI;
import com.e.gasserviceapp.webservices.RetrofitClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.content.Context;
import android.util.DisplayMetrics;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

//FragmentActivity
//public class HomeFragmentGasaver extends FragmentActivity implements OnMapReadyCallback,
//        GoogleMap.OnMarkerClickListener, NearLocationInterface {

//NearMe
//public class HomeFragmentGasaver extends Fragment implements OnMapReadyCallback,
//        GoogleMap.OnMarkerClickListener, NearLocationInterface {

//FindRoutesTechMirrors
public class HomeFragmentGasaver extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, NearLocationInterface,
        GoogleApiClient.OnConnectionFailedListener, RoutingListener {

    //   private FragmentHomeBinding binding;
    private FragmentHomeGasaverBinding binding;

    //NearMe
    private GoogleMap mGoogleMap;
    private AppPermissions appPermissions;
    private boolean isLocationPermissionOk, isTrafficEnable;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;
    private FirebaseAuth firebaseAuth;
    private Marker currentMarker;
    private LoadingDialog loadingDialog;
    private int radius = 5000;
    private RetrofitAPI retrofitAPI;
    private List<GooglePlaceModel> googlePlaceModelList;
    private PlaceModel selectedPlaceModel;
    private GooglePlaceAdapter googlePlaceAdapter;
    private GoogleMap.InfoWindowAdapter infoWindowAdapter;
    private ArrayList<String> userSavedLocationId;
    private DatabaseReference locationReference, userLocationReference;

    //Game App Studio Map Direction:----

    //   private GoogleMap map;
    private ApiInterface apiInterface;
    private List<LatLng> polylinelist;
    private PolylineOptions polylineOptions;
    //    private latlng origion, destination;
    private LatLng origion, dest;
    private GoogleMap map;


    //MapoDirectionCodeWorked:----
//    private GoogleMap mMap;
    //    private ActivityMapsBinding binding;
    private Context mContext;

    //FINDROUTESTECHMIRRORS:----
    //google map object
    private GoogleMap mMap;

    //current and destination location objects
    Location myLocation = null;
    Location destinationLocation = null;
    protected LatLng start = null;
    protected LatLng end = null;

    //to get location permissions.
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;

    //polyline object
    private List<Polyline> polylines = null;

    View view;

    public HomeFragmentGasaver() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        View rootView = inflater.inflate(R.layout.fragment_fitness_centers, container, false);

        //Activity
//        binding = FragmentHomeGasaverBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        //Fragment
        binding = FragmentHomeGasaverBinding.inflate(inflater, container, false);

        appPermissions = new AppPermissions();
        firebaseAuth = FirebaseAuth.getInstance();
        loadingDialog = new LoadingDialog(requireActivity());
        retrofitAPI = RetrofitClient.getRetrofitClient().create(RetrofitAPI.class);
        googlePlaceModelList = new ArrayList<>();
        userSavedLocationId = new ArrayList<>();
        locationReference = FirebaseDatabase.getInstance().getReference("Places");

//      ((MainActivity) requireActivity()).getSupportActionBar().hide();
        ((MainActivityGas) requireActivity()).getSupportActionBar().hide();

        //StackOverFloww
//        Display display = ((AppCompatActivity) mContext).getWindowManager().getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        display.getMetrics(outMetrics);

        //
//        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        ((MainActivityGas) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);

//        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
//                .getDefaultDisplay().getMetrics(metrics);


//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int height = metrics.heightPixels;
//        int width = metrics.widthPixels;
//        // use these height and width here onwards..


//        display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//        display.getWidth(); // to get width of the screen
//        display.getHeight(); // to get height of the Screen


//        ------------------------------------------------------------------------------------------

        //Game App Studio Map Direction

//      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

//      SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.homeMap);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.homeMap);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://maps.googleapis.com/").build();
        apiInterface = retrofit.create(ApiInterface.class);

//        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://maps.googleapis.com/").build();
//        apiInterface = retrofit.create(ApiInterface.class);
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.homeMap);
//        assert mapFragment != null;
//        mapFragment.getMapAsync(this);

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.homeMap);


//        ---------------------------------------------------------------

        //request location permission.
        requestPermision();

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.homeMap);
//        assert mapFragment != null;
//        mapFragment.getMapAsync(this);


//        ------------------------------------------------------------------

//      userLocationReference = FirebaseDatabase.getInstance().getReference("Users")
//              .child(firebaseAuth.getUid()).child("Saved Locations");


        binding.btnMapType.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.map_type_menu, popupMenu.getMenu());


            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {


                    case R.id.btnMenu:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;

                    case R.id.btnNormal:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;

                    case R.id.btnSatellite:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;

                    case R.id.btnTerrain:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;

                    case R.id.btnHybrid:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

//               case R.id.btnGraph:
//                  mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                  break;

                    case R.id.btnSearchPlus:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnReward:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnSetting:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnGalary:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnPlus:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;

                    case R.id.btnMaines:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;


                }
                return true;
            });

            popupMenu.show();
        });

        binding.enableTraffic.setOnClickListener(view -> {

            if (isTrafficEnable) {
                if (mGoogleMap != null) {
                    mGoogleMap.setTrafficEnabled(false);
                    isTrafficEnable = false;
                }
            } else {
                if (mGoogleMap != null) {
                    mGoogleMap.setTrafficEnabled(true);
                    isTrafficEnable = true;
                }
            }

        });


        binding.currentLocation.setOnClickListener(currentLocation -> getCurrentLocation());


        binding.placesGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {

                if (checkedId != -1) {
                    PlaceModel placeModel = AllConstant.placesName.get(checkedId - 1);
                    binding.edtPlaceName.setText(placeModel.getName());
                    selectedPlaceModel = placeModel;
                    getPlaces(placeModel.getPlaceType());
                }
            }
        });
        return binding.getRoot();
    }

//    private Object getSystemService(String windowService) {
//        return null;
//    }

    //-----------------------------------------------

    private void requestPermision() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
        } else {
            locationPermission = true;
        }
    }

////   FindRoutesTechMirrors

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted.
                    locationPermission=true;
                    getMyLocation();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    ////Near Me

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == AllConstant.LOCATION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                isLocationPermissionOk = true;
//                setUpGoogleMap();
//            } else {
//                isLocationPermissionOk = false;
//                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    //Copy
    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == AllConstant.LOCATION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                isLocationPermissionOk = true;
//                setUpGoogleMap();
//            } else {
//                isLocationPermissionOk = false;
//                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    ////   FindRoutesTechMirrors

    //to get user location
    private void getMyLocation() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                myLocation = location;
                LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                        ltlng, 16f);
                mMap.animateCamera(cameraUpdate);
            }
        });

        //get destination location when user click on map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                end = latLng;

                mMap.clear();

                start = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                //start route finding
                Findroutes(start, end);
            }
        });

    }


//    ------------------------------------------


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//      // Get the SupportMapFragment and request notification when the map is ready to be used.
//      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//      mapFragment.getMapAsync(this);


        // Get the SupportMapFragment and request notification when the map is ready to be used.
//      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.homeMap);
//      mapFragment.getMapAsync(this);

        //All
//      SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment


        //
//      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//      mapFragment.getMapAsync(this);

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.homeMap);

//        ------------------------------------------------------------------------------------

//        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://maps.googleapis.com/").build();
//        apiInterface = retrofit.create(ApiInterface.class);
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.homeMap);
//

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.homeMap);

//        assert mapFragment != null;
//
//
//        mapFragment.getMapAsync(this);

//      ---------------------------------------------------------

        //mapFragment.getMapAsync(new OnMapReadyCallback() {

        for (PlaceModel placeModel : AllConstant.placesName) {

            Chip chip = new Chip(requireContext());
            chip.setText(placeModel.getName());
            chip.setId(placeModel.getId());
            chip.setPadding(8, 8, 8, 8);
            chip.setTextColor(getResources().getColor(R.color.white, null));
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.primaryColor, null));
            chip.setChipIcon(ResourcesCompat.getDrawable(getResources(), placeModel.getDrawableId(), null));
            chip.setCheckable(true);
            chip.setCheckedIconVisible(false);

            binding.placesGroup.addView(chip);


        }

        setUpRecyclerView();
//      getUserSavedLocations();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

//        -----------------------

        //FindRoutesTechMirrors

        mMap = googleMap;

        if (locationPermission) {
            getMyLocation();
        }


        //---------------------

        //MapoDirectionCodeWorked
//        direction();

        //----------------------

        //Game App Studio Map Direction:------

//      map = googleMap;
//      map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//      map.setTrafficEnabled(true);
//      origion = new LatLng(17.3850, 78.4867);
//      dest = new LatLng(19.0760, 72.8777);
//      getDirection("17.3850" + "," + "78.4867", "19.0760" + "," + "72.8777");

//        ----

//        mGoogleMap = googleMap;
//        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        mGoogleMap.setTrafficEnabled(true);
//        origion = new LatLng(17.3850, 78.4867);
//        dest = new LatLng(19.0760, 72.8777);
//        getDirection("17.3850" + "," + "78.4867", "19.0760" + "," + "72.8777");


//      --------------------------------------------------------------
        //Double
        mGoogleMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()


//                        .target(new LatLng(17.393522,78.472950))
                .target(new LatLng(17.43539564025845, 78.44543771341806))

                .zoom(10).bearing(0).tilt(45).build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.438226164864947, 78.44320559487082)).title("SUPER GAS AUTO LPG STATION AMEERPET, 8-3-217, Srinivas Nagar Ameerpet(West Hyderabad Town Survey No. 6/1, Ward A, Block 5, Telangana 500038"));
        //.icon(bitmapDescriptorFromVector(getActivity(),R.drawable.spider)));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.43790154937477, 78.44273280167631)).title("BP-AMEERPET BPCL COMPANY RETAIL OUTLET, 8-3-217, BPCL Retail Outlet, Srvs Nagar Colony, Srinagar Colony Main Rd, Srinagar Colony, Hyderabad, Telangana 500038"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.427572819468452, 78.45143405308845)).title("Indian Oil Petrol Pump, Nagarjuna Cir Rd, Mothi Nagar, Dwarakapuri, Banjara Hills, Hyderabad, Telangana 500082").snippet("His Talent : Plenty of money"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.428790128563907, 78.43954284464363)).title("Rameshwar Filling Station - IOCL Retail Outlet, 8-2-268/A/1/2, Rd No 2,, Road No. 2, Sri Nagar Colony, Aurora Colony, Banjara Hills, Hyderabad, Telangana 500873"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.431398795025764, 78.43195843670263)).title("Metro Fuel Point - IOCL Retail Outlet, 833, #8-3, 233&234, Kamalapuri Gas Station Road, Sri Nagar Colony, Hyderabad, Telangana 500045"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.43491998911282, 78.43015599236239)).title("Hindustan Petroleum Corporation Limited, Survey No, 123, Yousufguda Police Lines, Sri Krishna Nagar, Yousufguda, Hyderabad, Telangana 500045"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.432299572074314, 78.4227745536356)).title("Hindustan Petroleum Corporation Limited, Old Survey No 403/1, New Survey No 120 Jubilee Hills CHS, Road No 5, Shaikpet, Hyderabad, Telangana 500033"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.43078974437147, 78.41826975129736)).title("Indian Oil, Plot No 1354, Road No. 1, & 68, Jubilee Hills, Hyderabad, Telangana 500033"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.425630645677938, 78.41208994213076)).title("Indian Oil, Plot No 1354, Road No. 1, & 68, Jubilee Hills, Hyderabad, Telangana 500033"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.425631735067814, 78.45937895477593)).title("Indian Oil, H No 6/3/927/C & D, Raj Bhavan Rd, Somajiguda, Hyderabad, Telangana 500082"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.429916672644104, 78.45713340468335)).title("Hindustan Petroleum Corporation Limited, D No, 6/3/1106, Raj Bhavan Rd, Nishat Bagh Colony, Somajiguda, Hyderabad, Telangana 500082"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.432448633924427, 78.45560234780203)).title("Indian Oil, No 6 & 3 & 873 Somajiguda, Ameerpet, Hyderabad, Telangana 500016"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.459351519073444, 78.43467688505594)).title("Indian Oil, D No, 7/2/1816, Erragadda Main Rd, Sanath Nagar, Hyderabad, Telangana 500018"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.4501200187884, 78.44969965547091)).title("S G S S Enterprises Indian oil petrol bunk, 7-1, 276/2, Balkampet Rd, Neemkar Nagar, Jaya Prakash Nagar, Balkampet, Hyderabad, Telangana 500018"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.45030808245249, 78.450199571975)).title("Indian Oil, Ground Floor, Suprabath Nagar Colony, Balkampet, Hyderabad, Telangana 500016, 09440422338"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.445518008242086, 78.46165796813808)).title("Indian Oil, Ground Floor, Begumpet, Hyderabad, Telangana 500016, 09246531399"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.438212, 78.367566)).title("Indian Oil Petrol Pump (Company Owned Company Operated COCO), A, Plot No1-11-258, 29, Begumpet Rd, Prakash Nagar, Hyderabad, Telangana 500016, 04027768685"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.560328372467396, 78.49536301906204)).title("Indubala Fuel Needs, HF3V+CCM, Unnamed Road, Laxmi Nagar Colony, Gundlapochampalli, Secunderabad, Telangana 500014, "));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.551323792489605, 78.47438396114599)).title("Bharat Petroleum, Petrol Pump -Nikhil Fuel Station, Dulapally, Hyderabad, Telangana 500074, 09347546647"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.522904475630348, 78.47969162500118)).title("Bhagyanagar Gas Limited CNG Station, Medchal Rd, Petbasheerabad, Dandamudi Enclave, Jeedimetla, Hyderabad, Telangana 500067, 18002333555"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.517843034268836, 78.46540176077565)).title("Bharat Petroleum Corporation ltd, Survey No. 236, Suchitra, Junction, Quthbullapur Main Rd, Quthbullapur, Hyderabad, Telangana 500055, 09885911072"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.51161337423542, 78.50949619895731)).title("Eco Energy Fuels, H.NO:8-75, Chatanpally Rd, Ram Nagar Colony, Telangana 509216"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.49136550344982, 78.53399310905823)).title("Goshamahal Service Station Bharat Petroleum, FGJM+7R5, RK Puram Rd, Sri colony, Neredmet, Secunderabad, Telangana 500056, 1800224344"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.4609894695474, 78.51317073547244)).title("Hindustan Petroleum Corporation Limited, Survey No 73, Plot No 21,53 & 54 Secunderabad Cantonment, East Marredpally, Hyderabad, Telangana 500026, 09440834923"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.524851146258737, 78.47846677949615)).title("Bhagyanagar Gas Limited CNG Station, Medchal Rd, Petbasheerabad, Dandamudi Enclave, Jeedimetla, Hyderabad, Telangana 500067, 18002333555"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.516285639300584, 78.46621832444569)).title("Bharat Petroleum Corporation ltd, Survey No. 236, Suchitra, Junction, Quthbullapur Main Rd, Quthbullapur, Hyderabad, Telangana 500055, 09885911072"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.477335658125874, 78.41202139944807)).title("Shiva Shakti Fuel Station - BPCL Retail Outlet, Survey No 1011, 10, Moosapet Rd, Kukatpally, Hyderabad, Telangana 500018, 09885900667"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.45513657475918, 78.40058950806763)).title("BOOK MY FUEL - Doorstep Diesel Delivery, 73p, Beverly Hills, Guttala_Begumpet, Kavuri Hills, Jubilee Hills, Hyderabad, Telangana 500033. 09169484848"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.47889338689438, 78.41487937229316)).title("Shiva Shakti Fuel Station - BPCL Retail Outlet, Survey No 1011, 10, Moosapet Rd, Kukatpally, Hyderabad, Telangana 500018, 09885900667"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.50654085239905, 78.38711620751211)).title("Indian Oil Fuel Station (Ganesh Service Station), Service Rd, Dharma Reddy Colony Phase II, Kukatpally Housing Board Colony, Kukatpally, Hyderabad, Telangana 500085, 08857242701"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.51122310525919, 78.3502330757176)).title("BPCL Diesel Pump, F8VX+GHF, Indra Reddy Allwyn Colony, Hafeezpet, Hyderabad, Telangana 500049"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.481472196998137, 78.31975895084517)).title("L Fuel HPCL, F8H9+GRH, Nallagandla Bypass Rd, Gulmohar Park Colony, Serilingampalle (M), Telangana 500019"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.48783980661841, 78.31939480209783)).title("Venkat Sai Fuels, F8P9+5WP, Lingampally Rd, Sivaji Nagar, Chanda Nagar, Serilingampalle (M), Telangana 500019"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.498041976471974, 78.31665832672361)).title("Mallikarjuna Fuel Station, NH65, Gouthami Nagar Colony, Jawahar Colony, Chanda Nagar, Hyderabad, Telangana 500050"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.499106141173762, 78.3150275437491)).title("Krishna Fuel Station - IOCL Retail Outlet, F8W8+Q38, near Bajaj Electronics, Lingampally, Bharat Heavy Electricals Limited, Hyderabad, Telangana 500032"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.395505546655734, 78.4308513315793)).title("Indian Oil, No 12/2, 279, A/B, Rethibowli, Mehdipatnam, Hyderabad, Telangana 500008, 09849815219"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.382337058526485, 78.43881355880269)).title("Hindustan Petroleum Corporation Limited, H No 13/6/457/5, 6, 7, Karwan Rd, Gudimalkapur, Hyderabad, Telangana 500006, 09000000400"));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.41998515510161, 78.40998747455713)).title("Indian Oil, Road No 1, Sy No 120 & 102/1, Block No 2 Ward No 8, Film Nagar, Shaikpet Rd, Hakimpet, Hyderabad, Telangana 500033, 07702003513"));


//      ---------------------------

        //GoogleMapMyApplication1
        LatLng globical = new LatLng(17.435392154622992, 78.44546021228582);

//        googleMap.addMarker(new MarkerOptions().position(globical).title("Globicle E-commerce,Malakpet"));
        googleMap.addMarker(new MarkerOptions().position(globical).title("Aakruti Software Solutions, Ameerpet"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(globical));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


//   }
//});
//        return rootView;


//      ---------------------------


        if (appPermissions.isLocationOk(requireContext())) {
            isLocationPermissionOk = true;

            setUpGoogleMap();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission")
                    .setMessage("Near me required location permission to show you near by places")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestLocation();
                        }
                    })
                    .create().show();
        } else {
            requestLocation();
        }

    }


    //-------------------------------------------------------

    //FindRoutesTechMirrors:-----

    // function to find Routes.
    public void Findroutes(LatLng Start, LatLng End) {
        if (Start == null || End == null) {
            Toast.makeText(getApplicationContext(), "Unable to get location", Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivityGas.this,"Unable to get location", Toast.LENGTH_LONG).show();
        } else {

            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(Start, End)
//                    .key("AIzaSyD4uStbluZBnwKADWRtCPalZoddDXdNQbs")  //also define your api key here.
//                    .key("AIzaSyDBzAYFjsc8GvWgJ-od4oDlS7SMR_PyVSE")  //also define your api key here.

                    //zaheerstroman@gmail.com
                    .key("AIzaSyBrBCjR96NhwdK1RM5S00y-8edeAuMuA0g")  //also define your api key here.

                    .build();
            routing.execute();
        }
    }

    //Routing call back functions.
    @Override
    public void onRoutingFailure(RouteException e) {
        View parentLayout = view.findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(parentLayout, e.toString(), Snackbar.LENGTH_LONG);
        snackbar.show();
//        Findroutes(start,end);
    }

    @Override
    public void onRoutingStart() {
//        Toast.makeText(MainActivityGas.this,"Finding Route...",Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Finding Route...", Toast.LENGTH_LONG).show();

    }

    //If Route finding success..
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        if (polylines != null) {
            polylines.clear();
        }
        PolylineOptions polyOptions = new PolylineOptions();
        LatLng polylineStartLatLng = null;
        LatLng polylineEndLatLng = null;


        polylines = new ArrayList<>();
        //add route(s) to the map using polyline
        for (int i = 0; i < route.size(); i++) {

            if (i == shortestRouteIndex) {
                polyOptions.color(getResources().getColor(R.color.purple_500));
                polyOptions.width(7);
                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylineStartLatLng = polyline.getPoints().get(0);
                int k = polyline.getPoints().size();
                polylineEndLatLng = polyline.getPoints().get(k - 1);
                polylines.add(polyline);

            } else {

            }

        }

        //Add Marker on route starting position
        MarkerOptions startMarker = new MarkerOptions();
        startMarker.position(polylineStartLatLng);
        startMarker.title("My Location");
        mMap.addMarker(startMarker);

        //Add Marker on route ending position
        MarkerOptions endMarker = new MarkerOptions();
        endMarker.position(polylineEndLatLng);
        endMarker.title("Destination");
        mMap.addMarker(endMarker);
    }


    @Override
    public void onRoutingCancelled() {
        Findroutes(start, end);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Findroutes(start, end);

    }


//    ---------------------------------------------------------------------------

    //MapodirrectionCodeWorkded

//    private void direction() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        String url = Uri.parse("https:/maps.googleapis.com/api/directions/json")
//                .buildUpon()
//
////  Hyderabad  origion -    origin 17.3850, 78.4867
//
//
////   Mumbai   dest -     destination   19.0760, 72.8777
//
//
////                .appendQueryParameter("destination", "-6.9218571, 107.604854")
//                .appendQueryParameter("destination", "19.0760, 72.8777")
//
//
////                .appendQueryParameter("origin", "-6.9249233, 167.6345122")
//                .appendQueryParameter("origin", "17.3850, 78.4867")
//
//                .appendQueryParameter("mode", "driving")
//
////                -------------------------------------
//
////                #Game App Studio Map Direction:
////        ----
////                zaheerkhanx143 @gmail.com
////
////        Normal With OutPayment Api
////                .appendQueryParameter("key", "AIzaSyDZH-VfTHKaCNa4Plks-gTGZ8wGusneuNQ")
////        After Payment Api(Game App Studio Map Direction)
////                .appendQueryParameter("key", "AIzaSyBJLlc30x4ahrdvNVHO18ImUtaWdvGmfPQ")
//
////                ----------------------------
//
////                #CodeWorked:-----
//
////                #zaheerkhanx143 @gmail.com
////        #After Payment Api(CodeWorked)
////        MAPS_API_KEY = "AIzaSyBRYISNdhVoL18_Qh73adJfMrD7KgVDItY"
//
////                Before Payment
////                .appendQueryParameter("key", "AIzaSyDZH-VfTHKaCNa4Plks-gTGZ8wGusneuNQ")
//
////                After Payment
////                .appendQueryParameter("key", "AIzaSyBRYISNdhVoL18_Qh73adJfMrD7KgVDItY")
//
////                zaheerstroman@gmail.com
//
//                .appendQueryParameter("key", "AIzaSyBrBCjR96NhwdK1RM5S00y-8edeAuMuA0g")
//
//
////        AIzaSyBJLlc30x4ahrdvNVHO18ImUtaWdvGmfPQ
//
//                .toString();
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                try {
//
//                    String status = response.getString("status");
//                    if (status.equals("Ok")) {
//                        JSONArray routes = response.getJSONArray("routes");
//                        ArrayList<LatLng> points;
//                        PolylineOptions polylineOptions = null;
//
//                        for (int i = 0; i < routes.length(); i++) {
//                            points = new ArrayList<>();
//                            polylineOptions = new PolylineOptions();
//                            JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");
//
//                            for (int j = 0; j < legs.length(); j++) {
//                                JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");
//
//                                for (int k = 0; k < steps.length(); k++) {
//                                    String polyline = steps.getJSONObject(k).getJSONObject("polyline").getString("points");
//                                    List<LatLng> list = decodePoly(polyline);
//
//                                    for (int l = 0; l < list.size(); l++) {
//                                        LatLng position = new LatLng((list.get(l)).latitude, (list.get(l)).longitude);
//                                        points.add(position);
//
//
//                                    }
//                                }
//                            }
//
//                            polylineOptions.addAll(points);
//                            polylineOptions.width(10);
////                            polylineOptions.color(ContextCompat.getColor(MapsActivity.this, R.color.purple_500));
//                            polylineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
//
//                            polylineOptions.geodesic(true);
//
//                        }
//
////                        mGoogleMap:-----
//                        mGoogleMap.addPolyline(polylineOptions);
////                        mMap.addMarker(new MarkerOptions().position(new LatLng(-6.9249233, 167.6345122)).title("Marker 1"));
//                        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(17.3850, 78.4867)).title("Marker 1"));
//
//
////                        mMap.addMarker(new MarkerOptions().position(new LatLng(-6.9218571, 107.604854)).title("Marker 1"));
//                        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(19.0760, 72.8777)).title("Marker 1"));
//
//
//                        LatLngBounds bounds = new LatLngBounds.Builder()
////                                .include(new LatLng(-6.9249233, 167.6345122))
//                                .include(new LatLng(17.3850, 78.4867))
//
////                                .include(new LatLng(-6.9218571, 107.604854)).build();
//                                .include(new LatLng(19.0760, 72.8777)).build();
//
//
//                        Point point = new Point();
//                        //Important
//                        getWindowManager().getDefaultDisplay().getSize(point);
//                        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, point.x, 150, 30));
//
////                        mMap.animateCamera();
//
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(3000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        jsonObjectRequest.setRetryPolicy(retryPolicy);
//        requestQueue.add(jsonObjectRequest);
//    }


//------------------------------------------------------------------------------

//    Game App Studio Map Direction


//    private void getDirection(String origin, String destination) {
//        //My Own
//        //    private void getDirection(String origion, String dest) {
//
////        apiInterface.getDirection("driving", "less_driving", origin, destination, getString(R.string.api_key)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Result>() {
//        apiInterface.getDirection("driving", "less_driving", origin, destination, getString(R.string.API_KEY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Result>() {
//
//            //MyOwn
//            //        apiInterface.getDirection("driving", "less_driving", origion, dest, getString(R.string.API_KEY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Result>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(Result result) {
//
//                polylinelist = new ArrayList<>();
//
//                List<Route> routeList = result.getRoutes();
//                for (com.e.gasserviceapp.Route route : routeList) {
//                    String polyline = route.getOveriewPolyline().getPoints();
////                            polylinelist.add(decodePoly(polyline));
//
//                    polylinelist.addAll(decodePoly(polyline));
////                            polylinelist.addAll(decodePoly(polyline));
//                }
//
//                polylineOptions = new PolylineOptions();
////             lylineOptions.color(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
//                polylineOptions.color(ContextCompat.getColor(getApplicationContext(), R.color.purple_200));
//                polylineOptions.width(8);
//                polylineOptions.startCap(new ButtCap());
//                polylineOptions.jointType(JointType.ROUND);
////                PolylineOptions.addAll
//                polylineOptions.addAll(polylinelist);
//                mGoogleMap.addPolyline(polylineOptions);
////                map.addPolyline(polylineOptions);
//
//                LatLngBounds.Builder builder= new LatLngBounds.Builder();
//                builder.include(origion);
//                //builder.include(origin);
//                builder.include(dest);
//                //builder.include(destination);
//                //()
//                //Padding
////                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
//                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
//
//                //Width/Height
////                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
////                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
//
//                //(int duration)
////                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
////                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
//
////                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
////                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
//
//
//
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//    }


    //    Game App Studio Map Direction & MapoDirectionCodeWorked


    //This is the method who decode string to latlang

    //        private ArrayList<LatLng> decodePoly(String encoded) {
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

//    -----------------------------------------------------------------------------------------------------


    private void requestLocation() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_BACKGROUND_LOCATION}, AllConstant.LOCATION_REQUEST_CODE);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == AllConstant.LOCATION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                isLocationPermissionOk = true;
//                setUpGoogleMap();
//            } else {
//                isLocationPermissionOk = false;
//                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void setUpGoogleMap() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(true);
        mGoogleMap.setOnMarkerClickListener(this::onMarkerClick);

        setUpLocationUpdate();
    }

    private void setUpLocationUpdate() {

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    for (Location location : locationResult.getLocations()) {
                        Log.d("TAG", "onLocationResult: " + location.getLongitude() + " " + location.getLatitude());
                    }
                }
                super.onLocationResult(locationResult);
            }
        };
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        startLocationUpdates();


    }

    private void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Location updated started", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        getCurrentLocation();
    }

    private void getCurrentLocation() {

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation = location;
                infoWindowAdapter = null;
                infoWindowAdapter = new InfoWindowAdapter(currentLocation, requireContext());
//            infoWindowAdapter = new GoogleMap.InfoWindowAdapter(currentLocation, requireContext());

                mGoogleMap.setInfoWindowAdapter(infoWindowAdapter);
                moveCameraToLocation(location);


            }
        });
    }

    private void moveCameraToLocation(Location location) {

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new
                LatLng(location.getLatitude(), location.getLongitude()), 17);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .snippet(firebaseAuth.getCurrentUser().getDisplayName());

        if (currentMarker != null) {
            currentMarker.remove();
        }

        currentMarker = mGoogleMap.addMarker(markerOptions);
        currentMarker.setTag(703);
        mGoogleMap.animateCamera(cameraUpdate);

    }

    private void stopLocationUpdate() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        Log.d("TAG", "stopLocationUpdate: Location Update stop");
    }

    @Override
    public void onPause() {
        super.onPause();

        if (fusedLocationProviderClient != null)
            stopLocationUpdate();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (fusedLocationProviderClient != null) {

            startLocationUpdates();
            if (currentMarker != null) {
                currentMarker.remove();
            }
        }
    }

    private void getPlaces(String placeName) {

        if (isLocationPermissionOk) {


            loadingDialog.startLoading();
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                    + currentLocation.getLatitude() + "," + currentLocation.getLongitude()
                    + "&radius=" + radius + "&type=" + placeName + "&key=" +
                    getResources().getString(R.string.API_KEY);

            if (currentLocation != null) {


                retrofitAPI.getNearByPlaces(url).enqueue(new Callback<GoogleResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<GoogleResponseModel> call, @NonNull Response<GoogleResponseModel> response) {
                        Gson gson = new Gson();
                        String res = gson.toJson(response.body());
                        Log.d("TAG", "onResponse: " + res);
                        if (response.errorBody() == null) {
                            if (response.body() != null) {
                                if (response.body().getGooglePlaceModelList() != null && response.body().getGooglePlaceModelList().size() > 0) {

                                    googlePlaceModelList.clear();
                                    mGoogleMap.clear();
                                    for (int i = 0; i < response.body().getGooglePlaceModelList().size(); i++) {

                                        if (userSavedLocationId.contains(response.body().getGooglePlaceModelList().get(i).getPlaceId())) {
                                            response.body().getGooglePlaceModelList().get(i).setSaved(true);
                                        }
                                        googlePlaceModelList.add(response.body().getGooglePlaceModelList().get(i));
                                        addMarker(response.body().getGooglePlaceModelList().get(i), i);
                                    }

                                    googlePlaceAdapter.setGooglePlaceModels(googlePlaceModelList);

                                } else if (response.body().getError() != null) {
                                    Snackbar.make(binding.getRoot(),
                                            response.body().getError(),
                                            Snackbar.LENGTH_LONG).show();
                                } else {

                                    mGoogleMap.clear();
                                    googlePlaceModelList.clear();
                                    googlePlaceAdapter.setGooglePlaceModels(googlePlaceModelList);
                                    radius += 1000;
                                    Log.d("TAG", "onResponse: " + radius);
                                    getPlaces(placeName);

                                }
                            }

                        } else {
                            Log.d("TAG", "onResponse: " + response.errorBody());
                            Toast.makeText(requireContext(), "Error : " + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }

                        loadingDialog.stopLoading();

                    }

                    @Override
                    public void onFailure(Call<GoogleResponseModel> call, Throwable t) {

                        Log.d("TAG", "onFailure: " + t);
                        loadingDialog.stopLoading();

                    }
                });
            }
        }

    }

    private void addMarker(GooglePlaceModel googlePlaceModel, int position) {

        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(googlePlaceModel.getGeometry().getLocation().getLat(),
                        googlePlaceModel.getGeometry().getLocation().getLng()))
                .title(googlePlaceModel.getName())
                .snippet(googlePlaceModel.getVicinity());
        markerOptions.icon(getCustomIcon());
        mGoogleMap.addMarker(markerOptions).setTag(position);
    }

    private BitmapDescriptor getCustomIcon() {

        Drawable background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_location);
        background.setTint(getResources().getColor(R.color.quantum_googred900, null));
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    //GoogleMapMyApplication1
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void setUpRecyclerView() {

        binding.placesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.placesRecyclerView.setHasFixedSize(false);
        googlePlaceAdapter = new GooglePlaceAdapter(this);
        binding.placesRecyclerView.setAdapter(googlePlaceAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(binding.placesRecyclerView);

        binding.placesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (position > -1) {
                    GooglePlaceModel googlePlaceModel = googlePlaceModelList.get(position);
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlePlaceModel.getGeometry().getLocation().getLat(),
                            googlePlaceModel.getGeometry().getLocation().getLng()), 20));
                }
            }
        });

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        int markerTag = (int) marker.getTag();
        Log.d("TAG", "onMarkerClick: " + markerTag);

        binding.placesRecyclerView.scrollToPosition(markerTag);
        return false;
    }

    @Override
    public void onSaveClick(GooglePlaceModel googlePlaceModel) {

        if (userSavedLocationId.contains(googlePlaceModel.getPlaceId())) {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Remove Place")
                    .setMessage("Are you sure to remove this place?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removePlace(googlePlaceModel);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        } else {
            loadingDialog.startLoading();

            locationReference.child(googlePlaceModel.getPlaceId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {

                        SavedPlaceModel savedPlaceModel = new SavedPlaceModel(googlePlaceModel.getName(), googlePlaceModel.getVicinity(),
                                googlePlaceModel.getPlaceId(), googlePlaceModel.getRating(),
                                googlePlaceModel.getUserRatingsTotal(),
                                googlePlaceModel.getGeometry().getLocation().getLat(),
                                googlePlaceModel.getGeometry().getLocation().getLng());

                        saveLocation(savedPlaceModel);
                    }

                    saveUserLocation(googlePlaceModel.getPlaceId());

                    int index = googlePlaceModelList.indexOf(googlePlaceModel);
                    googlePlaceModelList.get(index).setSaved(true);
                    googlePlaceAdapter.notifyDataSetChanged();
                    loadingDialog.stopLoading();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    private void removePlace(GooglePlaceModel googlePlaceModel) {

        userSavedLocationId.remove(googlePlaceModel.getPlaceId());
        int index = googlePlaceModelList.indexOf(googlePlaceModel);
        googlePlaceModelList.get(index).setSaved(false);
        googlePlaceAdapter.notifyDataSetChanged();

        Snackbar.make(binding.getRoot(), "Place removed", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userSavedLocationId.add(googlePlaceModel.getPlaceId());
                        googlePlaceModelList.get(index).setSaved(true);
                        googlePlaceAdapter.notifyDataSetChanged();

                    }
                })
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);

                        userLocationReference.setValue(userSavedLocationId);
                    }
                }).show();

    }

    private void saveUserLocation(String placeId) {

        userSavedLocationId.add(placeId);
        userLocationReference.setValue(userSavedLocationId);
        Snackbar.make(binding.getRoot(), "Place Saved", Snackbar.LENGTH_LONG).show();
    }

    private void saveLocation(SavedPlaceModel savedPlaceModel) {
        locationReference.child(savedPlaceModel.getPlaceId()).setValue(savedPlaceModel);
    }

    @Override
    public void onDirectionClick(GooglePlaceModel googlePlaceModel) {

        String placeId = googlePlaceModel.getPlaceId();
        Double lat = googlePlaceModel.getGeometry().getLocation().getLat();
        Double lng = googlePlaceModel.getGeometry().getLocation().getLng();

        Intent intent = new Intent(requireContext(), DirectionActivity.class);
        intent.putExtra("placeId", placeId);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);

        startActivity(intent);

    }

//   private void getUserSavedLocations() {
//      DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//              .child(firebaseAuth.getUid()).child("Saved Locations");
//
//      databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//         @Override
//         public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if (snapshot.exists()) {
//               for (DataSnapshot ds : snapshot.getChildren()) {
//                  String placeId = ds.getValue(String.class);
//                  userSavedLocationId.add(placeId);
//
//               }
//            }
//         }
//
//         @Override
//         public void onCancelled(@NonNull DatabaseError error) {
//
//         }
//      });
//   }


}