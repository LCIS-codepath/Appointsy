package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityProfileBinding;
import com.codepath.appointsy.databinding.FragmentProfileBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ImageView ivProfile;
    private TextInputLayout tvName;
    private EditText etName;
    private TextInputLayout tvUsername;
    private EditText etUsername;
    private TextInputLayout tvEmail;
    private EditText etEmail;
    private TextInputLayout tvPassword;
    private EditText etPassword;
    private TextInputLayout tvBio;
    private EditText etBio;
    private MaterialButton btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        Intent i = getIntent();
        ParseUser user = Parcels.unwrap((ParseUser) i.getParcelableExtra("user"));
        ivProfile = binding.ivProfile;
        tvName = binding.tvName;
        tvUsername = binding.tvUsername;
        tvEmail = binding.tvEmail;
        tvPassword = binding.tvPassword;
        tvBio = binding.tvBio;
        btUpdate = binding.btUpdate;
        etName = binding.etName;
        etUsername = binding.etUsername;
        etEmail = binding.etEmail;
        etPassword = binding.etPassword;
        etBio = binding.etBio;

        //Set text for each field
        etName.setText(user.getString("fullName"));
        etUsername.setText(user.getUsername());
        etEmail.setText(user.getEmail());
        etPassword.setText(user.getString("password"));
        etBio.setText(user.getString("userBio"));

        btUpdate.setOnClickListener((e ->{
            // TODO check if inputs are not null
            user.setUsername(binding.tvName.getEditText().getText().toString());
            //Currently not planning for email update, but left in for testing purposes.
            //user.setEmail(binding.tvEmail.getEditText().getText().toString());
            user.setPassword(binding.tvPassword.getEditText().getText().toString());
            user.put("fullName", binding.tvName.getEditText().getText().toString());
            user.put("userBio", binding.tvBio.getEditText().getText().toString());
            user.saveInBackground();

            Toast.makeText(this, "Profile update successful!", Toast.LENGTH_SHORT).show();
        }));
    }


}