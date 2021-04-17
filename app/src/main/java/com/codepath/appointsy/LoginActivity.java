package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.appointsy.databinding.ActivityLoginBinding;
import com.codepath.appointsy.fragments.BusinessFragment;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private MaterialButton btnLogin;
    private MaterialButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        // ref. views
        btnLogin = binding.btnLogin;
        btnRegister = binding.btnRegister;

        // user selects to login, check pass & email
        btnLogin.setOnClickListener((e) -> {
            //TODO Parse login
            // handle error with SnackBar rather than a Toast

            // if login successful route to Business
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            // remove stack call backs to prevent user from going back to register/login
            finishAffinity();
        });
        // User decides to register
        btnRegister.setOnClickListener((e) -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });



    }
}