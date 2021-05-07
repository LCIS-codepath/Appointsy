package com.codepath.appointsy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.appointsy.MainActivity;
import com.codepath.appointsy.R;
import com.codepath.appointsy.databinding.ActivityRegisterBinding;
import com.codepath.appointsy.databinding.FragmentProfileBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentProfileBinding binding;
    private ImageView ivProfile;
    private TextInputLayout tvName;
    private EditText etName;
    private TextInputLayout tvUsername;
    private EditText etUsername;
    private TextInputLayout tvEmail;
    private EditText etEmail;
    private TextInputLayout tvPhoneNumber;
    private EditText etPhoneNumber;
    private TextInputLayout tvPassword;
    private EditText etPassword;
    private TextInputLayout tvBio;
    private EditText etBio;
    private MaterialButton btUpdate;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent i = getActivity().getIntent();
        ParseUser user = Parcels.unwrap((ParseUser) i.getParcelableExtra("user"));
        ivProfile = binding.ivProfile;
        tvName = binding.tvName;
        tvUsername = binding.tvUsername;
        tvEmail = binding.tvEmail;
        tvPhoneNumber = binding.tvPhoneNumber;
        tvPassword = binding.tvPassword;
        tvBio = binding.tvBio;
        btUpdate = binding.btUpdate;
        etName = binding.etName;
        etUsername = binding.etUsername;
        etEmail = binding.etEmail;
        etPhoneNumber = binding.etPhoneNumber;
        etPassword = binding.etPassword;
        etBio = binding.etBio;

        //Set text for each field
        etName.setText(user.getString("fullName"));
        etUsername.setText(user.getUsername());
        etEmail.setText(user.getEmail());
        etPassword.setText(user.getString("password"));
        etBio.setText(user.getString("userBio"));
        etPhoneNumber.setText(user.getString("phoneNumber"));

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereExists(user.getUsername());
        query.findInBackground((users, e) -> {
            if(users == null){
                ParseUser newUser = new ParseUser();
                newUser.setUsername(binding.tvName.getEditText().getText().toString());
                newUser.setEmail(binding.tvEmail.getEditText().getText().toString());
                newUser.setPassword(binding.tvPassword.getEditText().getText().toString());
                newUser.put("fullName", binding.tvName.getEditText().getText().toString());
                newUser.put("userBio", binding.tvBio.getEditText().getText().toString());
                newUser.put("phoneNumber",binding.tvPhoneNumber.getEditText().getText().toString());

                newUser.signUpInBackground();
            }
        });

        btUpdate.setOnClickListener((e ->{
            // TODO check if inputs are not null
            user.setUsername(binding.tvName.getEditText().getText().toString());
            //Currently not planning for email update, but left in for testing purposes.
            //user.setEmail(binding.tvEmail.getEditText().getText().toString());
            user.setPassword(binding.tvPassword.getEditText().getText().toString());
            user.put("fullName", binding.tvName.getEditText().getText().toString());
            user.put("userBio", binding.tvBio.getEditText().getText().toString());
            user.put("phoneNumber",binding.tvPhoneNumber.getEditText().getText().toString());
            user.saveInBackground();

            Toast.makeText(getContext(), "Profile update successful!", Toast.LENGTH_SHORT).show();
            Intent out = new Intent(getContext(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(out);
        }));
    }
}