package com.e.gasserviceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.databinding.ActivityMainGasBinding;
import com.e.gasserviceapp.databinding.NavDrawerLayoutBinding;
import com.e.gasserviceapp.databinding.ToolbarLayoutBinding;
import com.e.gasserviceapp.fragment.MyRequirementsFragment;
import com.e.gasserviceapp.view.FuelDistanceEmployeeListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityGas extends AppCompatActivity {

    Spinner spinner_caseText1, registertype_spinner;


    //    private NavDrawerLayoutBinding navDrawerLayoutBinding;
    public NavDrawerLayoutBinding navDrawerLayoutGasBinding;

    //    private ActivityMainBinding activityMaininding;
    public ActivityMainGasBinding activityMainGasBinding;

    //    private ToolbarLayoutBinding toolbarLayoutBinding;
    public ToolbarLayoutBinding toolbarLayoutGasBinding;


//    private FirebaseAuth firebaseAuth;
//    private CircleImageView imgHeader;
//    private TextView txtName, txtEmail;

    public FirebaseAuth firebaseAuth;
//    public CircleImageView imgHeader;
//    public TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        navDrawerLayoutBinding = NavDrawerLayoutBinding.inflate(getLayoutInflater());
        navDrawerLayoutGasBinding = NavDrawerLayoutBinding.inflate(getLayoutInflater());

//        setContentView(navDrawerLayoutBinding.getRoot());
        setContentView(navDrawerLayoutGasBinding.getRoot());

//        activityMainBinding = navDrawerLayoutBinding.mainActivity;
//        activityMainGasBinding = navDrawerLayoutBinding.mainActivityGas;
        activityMainGasBinding = navDrawerLayoutGasBinding.mainActivityGas;

//        toolbarLayoutGasBinding = activityMainGasBinding.toolbar;
//        toolbarLayoutBinding = activityMainGasBinding.toolbar;
//        toolbarLayoutBinding = activityMainGasBinding.toolbar;

//        setSupportActionBar(toolbarLayoutBinding.toolbar);
//        setSupportActionBar(toolbarLayoutGasBinding.toolbar);


//        firebaseAuth = FirebaseAuth.getInstance();

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this,
////                navDrawerLayoutBinding.navDrawer,
//                navDrawerLayoutGasBinding.navDrawer,
//
////                toolbarLayoutBinding.toolbar,
//                toolbarLayoutGasBinding.toolbar,
//                R.string.open_navigation_drawer,
//                R.string.close_navigation_drawer
//        );

//        navDrawerLayoutBinding.navDrawer.addDrawerListener(toggle);
//        navDrawerLayoutGasBinding.navDrawer.addDrawerListener(toggle);

//        toggle.syncState();

        //        setSupportActionBar(toolbar);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title

        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

//        setContentView(R.layout.activity_main_gas);


//        spinner_caseText1 = (Spinner) view.findViewById(R.id.spinner_caseText1);
        spinner_caseText1 = (Spinner) findViewById(R.id.spinner_caseText1);
        String[] spinnerItems = {"Unlead 91", "Gas", "Petrol", "Diesel", "U95", "U98", "LPG", "Truck DSL", "AdBlue", "E85", "Biodiesel", "Fuel"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivityGas.this, android.R.layout.simple_spinner_item, spinnerItems);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_caseText1.setAdapter(adapter);

//        bottomNavigationView.background = null;
//        bottomNavigationView.menu.getItem(2).isEnabled = false;

//        spinner_caseText1.background = null;
//        spinner_caseText1.menu.getItem(2).isEnabled = false;

//        -----------------------------------------------------------------------------------------------

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.btnMenu);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnGraph);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivityGas.this, OtherFloatingFabActivity.class));
                startActivity(new Intent(MainActivityGas.this, HomeTaksikuActivity.class));

            }
        });


//        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.btnGraph);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.btnHybrid);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivityGas.this, OtherFloatingFabActivity.class));
//                startActivity(new Intent(MainActivityGas.this, GraphActivityGeeks.class));

//                startActivity(new Intent(getApplicationContext(), MyRequirementsFragment.class));
                startActivity(new Intent(getApplicationContext(), PostPropertyDetailsActivity.class));


//                startActivity(new Intent(getApplicationContext(), SpinnerTestTPActivity.class));


            }
        });

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.btnReward);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityGas.this, RewardHomeTaksikuActivity.class));

//                startActivity(new Intent(MainActivityGas.this, OtherFloatingFabActivity.class));
//                startActivity(new Intent(MainActivityGas.this, HomeTaksikuActivity.class));
//                startActivity(new Intent(MainActivityGas.this, OrderTaksikuActivity.class));
//                startActivity(new Intent(MainActivityGas.this, MyTripTaksikuActivity.class));


            }
        });


        FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.btnSetting);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivityGas.this, OtherFloatingFabActivity.class));
//                startActivity(new Intent(MainActivityGas.this, ProfileMainActivity.class));
                startActivity(new Intent(MainActivityGas.this, UserProfileActivity.class));
//                startActivity(new Intent(MainActivityGas.this, HomeTaksikuActivity.class));
//                startActivity(new Intent(MainActivityGas.this, OrderTaksikuActivity.class));
//                startActivity(new Intent(MainActivityGas.this, MyTripTaksikuActivity.class));


            }
        });

//        btnSearchPlus

        FloatingActionButton fab5 = (FloatingActionButton) findViewById(R.id.btnSearchPlus);
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivityGas.this, NearByGasFuelCentersMapActivity.class));
//                startActivity(new Intent(MainActivityGas.this, NearByGasFuelCentersMapActivity.class));


//                startActivity(new Intent(MainActivityGas.this, NearByFuelGasCentersMapActivity.class));
//                startActivity(new Intent(MainActivityGas.this, DirectionBW2LocationFindRoutesTechMirrorsActivity.class));


//                startActivity(new Intent(MainActivityGas.this, DirectionBW2LocationRetrofitGameAppStudioActivity.class));

//                startActivity(new Intent(MainActivityGas.this, DirectionBW2LocationVolleyMapoDirectionCodeWorkedActivity.class));
                startActivity(new Intent(MainActivityGas.this, FuelDistanceEmployeeListActivity.class));


            }
        });


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_geeks);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        //HOME
                        //startActivity(new Intent(getApplicationContext(),DashBoardGeeksActivity.class));
                        //                        overridePendingTransition(0,0);
                        return true;

                    case R.id.dashboard:
                        //SEARCH
//                        startActivity(new Intent(getApplicationContext(),DashBoardGeeksActivity.class));
                        startActivity(new Intent(getApplicationContext(), HomeTaksikuActivity.class));

                        overridePendingTransition(0, 0);
                        return true;
//                    case R.id.home:
//                        return true;
                    case R.id.about:
                        //LOVE
//                        startActivity(new Intent(getApplicationContext(), AboutGeeksActivity.class));
                        startActivity(new Intent(getApplicationContext(), MainPickmanActivity.class));
//                        startActivity(new Intent(getApplicationContext(), DataRecoveryPikmenActivity.class));

                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.profile:
                        //PROFILE
//                        startActivity(new Intent(getApplicationContext(), AboutGeeksActivity.class));
                        startActivity(new Intent(getApplicationContext(), ProfileMainActivity.class));
//                        startActivity(new Intent(getApplicationContext(), DataRecoveryPikmenActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    // case R.id.nav_my_profile:
                    //                        bottom_nav_fragment.setVisibility(View.VISIBLE);
                    //                        nav_host_fragment.setVisibility(View.GONE);
                    //                        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.bottom_nav_fragment);
                    //
                    //                        if (!(currentFragment instanceof ProfileFragment)) {
                    //                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    //                            ft.replace(R.id.bottom_nav_fragment, new ProfileFragment(), "NewFragmentTag");
                    //                            ft.commit();
                    //                        }
                    //                        break;

                        //TProperty
//                    Intent intent = new Intent(HomeActivity.this, ProfileDetailsActivity.class);
                   //Gasaver
//                    Intent intent = new Intent(HomeActivity.this, ProfileMainActivity.class);
//                    intent.putExtra("SELECTED_POS", 1);
//                    startActivity(intent);
//                    break;


                }
                return false;
            }
        });


//        -----------------------------------------------------------------------------------------------


        NavController navController = Navigation.findNavController(this, R.id.fragmentContainer);
        NavigationUI.setupWithNavController(

//                navDrawerLayoutBinding.navigationView,
                navDrawerLayoutGasBinding.navigationView,
                navController
        );

//        View headerLayout = navDrawerLayoutBinding.navigationView.getHeaderView(0);
        View headerLayout = navDrawerLayoutGasBinding.navigationView.getHeaderView(0);

//        imgHeader = headerLayout.findViewById(R.id.imgHeader);
//        txtName = headerLayout.findViewById(R.id.txtHeaderName);
//        txtEmail = headerLayout.findViewById(R.id.txtHeaderEmail);

//        getUserData();


    }

    @Override
    public void onBackPressed() {

//        if (navDrawerLayoutBinding.navDrawer.isDrawerOpen(GravityCompat.START))
//            navDrawerLayoutBinding.navDrawer.closeDrawer(GravityCompat.START);
        if (navDrawerLayoutGasBinding.navDrawer.isDrawerOpen(GravityCompat.START))
            navDrawerLayoutGasBinding.navDrawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

//    private void getUserData() {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//                .child(firebaseAuth.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()) {
//
//                    UserModel userModel = snapshot.getValue(UserModel.class);
//                    Glide.with(MainActivityGas.this).load(userModel.getImage()).into(imgHeader);
//                    txtName.setText(userModel.getUsername());
//                    txtEmail.setText(userModel.getEmail());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

//    public void onBackPressed() {
//        android.os.Process.killProcess(android.os.Process.myPid());
//        super.onBackPressed();
//    }

}