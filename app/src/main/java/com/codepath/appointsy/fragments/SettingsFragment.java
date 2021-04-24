package com.codepath.appointsy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.codepath.appointsy.LoginActivity;
import com.codepath.appointsy.R;
import com.codepath.appointsy.databinding.FragmentBusinessProfileBinding;
import com.codepath.appointsy.databinding.FragmentSettingsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSettingsBinding binding;
    private TextInputLayout tvDarkMode;
    private TextInputLayout tvNotifications;
    private TextInputLayout tvCredits;
    private TextInputLayout tvVersion;
    private TextInputLayout tvCopyright;
    private SwitchMaterial swDarkMode;
    private SwitchMaterial swNotifications;
    private MaterialButton btnRelease;
    private MaterialButton btnContact;
    private MaterialButton btnAbout;
    private MaterialButton btnLogout;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

        swDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    //Set DarkMode variable to True
                } else {
                    // The toggle is disabled
                    // Set DarkMode variable to False
                }
            }
        });

        swNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    //Set Notifications variable to True
                } else {
                    // The toggle is disabled
                    // Set Notifications variable to False
                }
            }
        });

        btnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Determine what Release actually does
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Determine what About actually does
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Determine what Contact actually does
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i = new Intent(getContext(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}