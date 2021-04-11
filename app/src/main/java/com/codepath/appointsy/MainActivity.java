package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.codepath.appointsy.databinding.ActivityMainBinding;
import com.codepath.appointsy.fragments.AppointmentFragment;
import com.codepath.appointsy.fragments.BusinessFragment;
import com.codepath.appointsy.fragments.ProfileFragment;
import com.codepath.appointsy.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // For binding, (enabled in build.gradle app)
    private ActivityMainBinding binding;

    // Views references
    BottomNavigationView bottomNavigationView;
    RelativeLayout rlMain;

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

        // create the fragments
        Fragment frag_business = new BusinessFragment();
        Fragment frag_appoint = new AppointmentFragment();
        Fragment frag_profile = new ProfileFragment();
        Fragment frag_settings = new SettingsFragment();

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
}