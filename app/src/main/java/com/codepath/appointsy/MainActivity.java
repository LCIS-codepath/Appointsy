package com.codepath.appointsy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityMainBinding;
import com.codepath.appointsy.fragments.AppointmentFragment;
import com.codepath.appointsy.sideBarFragments.BusinessFavoritesFragment;
import com.codepath.appointsy.fragments.BusinessFragment;
import com.codepath.appointsy.sideBarFragments.BusinessLocationFragment;
import com.codepath.appointsy.fragments.ProfileFragment;
import com.codepath.appointsy.fragments.SettingsFragment;
import com.codepath.appointsy.sideBarFragments.BusinessTypeFragment;
import com.codepath.appointsy.sideBarFragments.BusinessTypeModuleOverlay;
import com.codepath.appointsy.sideBarFragments.SearchViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener, BusinessTypeModuleOverlay.EditNameDialogListener {

    // For binding, (enabled in build.gradle app)
    private ActivityMainBinding binding;
    public static final String TAG ="MainActivity";
    // Views references
    BottomNavigationView bottomNavigationView;
    RelativeLayout rlMain;
     DrawerLayout drawer;
    Toolbar toolbar;
    private String sideBarKey = "";
    private SearchViewModel searchViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        // Inflate the content view (replaces setContent view) and bind
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // The fragment manager (used to commit and replace the Fragment
        final FragmentManager fragmentManager = getSupportFragmentManager();

        // reference the BottomNavBar
        bottomNavigationView = binding.bottomNavBar;
        rlMain = binding.rlMain;

        //TopNavbar and side bar
        toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        drawer = binding.drawerlayout;
        NavigationView navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener( this);

        //Enable sideBar to open
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // create the fragments
        Fragment frag_business = new BusinessFragment();
        Fragment frag_appoint = new AppointmentFragment();
        Fragment frag_profile = new ProfileFragment();
        Fragment frag_settings = new SettingsFragment();

        // TODO, route to respective profiles (user/business)
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Fragment reference
            Fragment fragment;
            // get the selected item ID & assign the fragment
            int itemId = item.getItemId();
            if (itemId == R.id.action_business) {
                fragment = frag_business;
                // Testing
                // Snackbar.make(rlMain, "Business!", Snackbar.LENGTH_SHORT).show();
            } else if (itemId == R.id.action_appointments) {
                fragment = frag_appoint;
            } else if (itemId == R.id.action_profile) {
                fragment = frag_profile;
            } else if (itemId == R.id.action_settings) {
                fragment = frag_settings;
            } else {
                // default is home
                fragment = frag_business;
            }
            // Swap fragment with the selected using the container
            fragmentManager.beginTransaction().replace(R.id.flFragmentContainer, fragment).commit();
            return true;
        });
        // Default
        bottomNavigationView.setSelectedItemId(R.id.action_business);

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_favorites) {
            Log.i(TAG, "favorites ");
            Toast.makeText(this, "favorites", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragmentContainer, new BusinessFavoritesFragment()).commit();
        }else if(item.getItemId() == R.id.business_type){
//            Log.i(TAG, "favorites ");
//            Toast.makeText(this, "favorites", Toast.LENGTH_LONG).show();
//            getSupportFragmentManager().beginTransaction().replace(R.id.flFragmentContainer, new BusinessLocationFragment()).commit();
            showEditDialog();


            sideBarKey = "business_type";
        } else if(item.getItemId() == R.id.business_location){
            showEditDialog();
            sideBarKey = "location";
        }
        return true;
    }

    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here
    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.setSelected(inputText);

        if(sideBarKey.equals("business_type")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragmentContainer, new BusinessTypeFragment()).commit();
        }else if(sideBarKey.equals("location")){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragmentContainer, new BusinessLocationFragment()).commit();
        }

    }


    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        BusinessTypeModuleOverlay editNameDialogFragment = BusinessTypeModuleOverlay.newInstance("Some Title");
        // SETS the target fragment for use later when sending results
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }
}