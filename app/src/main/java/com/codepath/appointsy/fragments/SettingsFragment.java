package com.codepath.appointsy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.codepath.appointsy.LoginActivity;
import com.codepath.appointsy.databinding.FragmentSettingsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.parse.ParseUser;

import org.jetbrains.annotations.NotNull;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private TextView tvDarkMode;
    private TextView tvNotifications;
    private TextView tvCredits;
    private TextView tvVersion;
    private TextView tvCopyright;
    private SwitchMaterial swDarkMode;
    private SwitchMaterial swNotifications;
    private MaterialButton btnRelease;
    private MaterialButton btnContact;
    private MaterialButton btnAbout;
    private MaterialButton btnLogout;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ParseUser user = ParseUser.getCurrentUser();

        tvDarkMode = binding.tvDarkMode;
        tvNotifications = binding.tvNotifications;
        tvCredits = binding.tvCredits;
        tvVersion = binding.tvVersion;
        tvCopyright = binding.tvCopyright;
        swDarkMode = binding.swDarkMode;
        swNotifications = binding.swNotifications;
        btnRelease = binding.btnRelease;
        btnAbout = binding.btnAbout;
        btnContact = binding.btnContact;
        btnLogout = binding.btnLogout;

        //requires new settings in Back4App User object
        //swDarkMode.setChecked(user.getBoolean(""));
        //swNotifications.setChecked(user.getBoolean(""));

        //TODO Determine where Credits and Version come from

        swDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                //Set DarkMode variable to True
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                // The toggle is disabled
                // Set DarkMode variable to False
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        swNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The toggle is enabled
                //Set Notifications variable to True
            } else {
                // The toggle is disabled
                // Set Notifications variable to False
            }
        });

        btnRelease.setOnClickListener(v -> {
            //TODO Determine what Release actually does
        });

        btnAbout.setOnClickListener(v -> {
            //TODO Determine what About actually does
        });

        btnContact.setOnClickListener(v -> {
            //TODO Determine what Contact actually does
        });

        btnLogout.setOnClickListener(v -> {
            ParseUser.logOut();
            Intent i = new Intent(getContext(), LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
    }
}