package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityLoginBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    private MaterialButton btnLogin;
    private MaterialButton btnRegister;
    private RelativeLayout rlLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // ref. views
        btnLogin = binding.btnLogin;
        btnRegister = binding.btnRegister;
        rlLogin = binding.rlLogin;

        // user selects to login, check pass & email
        btnLogin.setOnClickListener((e) -> {
            // Perform API call
            // TODO check if inputs are not null
            String username = Objects.requireNonNull(binding.tiUsername).getEditText().getText().toString();
            String pass = Objects.requireNonNull(binding.tiPassword.getEditText()).getText().toString();
            Toast.makeText(this, username + " " + pass, Toast.LENGTH_SHORT).show();
            loginUser(username, pass);
        });

        // User decides to register
        btnRegister.setOnClickListener((e) -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    // handle user login
    private void loginUser(String username, String pass) {
        ParseUser.logInInBackground(username, pass, (user, err) -> {
            // login successful, user returned
            if(user != null){
                // login successful route to Business
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                // remove stack call backs to prevent user from going back to register/login
                finishAffinity();
            } else {
                // login failed
                Snackbar.make(rlLogin, "Failed to login. Try again.", Snackbar.LENGTH_SHORT).show();
                Log.e(TAG, "Error logging", err);
            }
        });
    }
}