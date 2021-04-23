package com.codepath.appointsy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.codepath.appointsy.R;
import com.codepath.appointsy.databinding.FragmentBusinessBinding;
import com.codepath.appointsy.databinding.FragmentBusinessProfileBinding;
import com.codepath.appointsy.databinding.FragmentProfileBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentBusinessProfileBinding binding;
    private ImageView ivProfile;
    private TextInputLayout tvName;
    private EditText etName;
    private TextInputLayout tvOwner;
    private EditText etOwner;
    private TextInputLayout tvEmail;
    private EditText etEmail;
    private TextInputLayout tvPassword;
    private EditText etPassword;
    private TextInputLayout tvBio;
    private EditText etBio;
    private MaterialButton btUpdate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusinessProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessProfileFragment newInstance(String param1, String param2) {
        BusinessProfileFragment fragment = new BusinessProfileFragment();
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
        binding = FragmentBusinessProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ParseUser user = ParseUser.getCurrentUser();
        ivProfile = binding.ivProfile;
        tvName = binding.tvName;
        tvOwner = binding.tvOwner;
        tvEmail = binding.tvEmail;
        tvPassword = binding.tvPassword;
        tvBio = binding.tvBio;
        btUpdate = binding.btUpdate;
        etName = binding.etName;
        etOwner = binding.etOwner;
        etEmail = binding.etEmail;
        etPassword = binding.etPassword;
        etBio = binding.etBio;

        //Set text for each field
        //Ex: etName.setText(user.getBusinessName());

        btUpdate.setOnClickListener((e ->{

        }));
    }
}